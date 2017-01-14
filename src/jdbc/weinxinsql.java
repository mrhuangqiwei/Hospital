package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

import com.alibaba.fastjson.JSON;

import bean.WeiXinUserBean;
import bean.userinfobean;

public class weinxinsql {
	public List<String> getjdbclist(String sql){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("userid"));
		
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String userinfotojison(String Openid){
		Connection conn = JDBC.getConnection();	
		String json = null;
		String sql="select Rtrim(userid)as userid,Rtrim(username)as username,RTRIM(city)as city,RTRIM(province)as province,RTRIM(county)as county, Rtrim(sex)as sex,Rtrim(friendid1) as friend1,Rtrim(friendid2)as friend2,Rtrim(friendid3)as friend3,Rtrim(ylkh1)as ylkh1,Rtrim(ylkh2)as ylkh2,Rtrim(ylkh3)as ylkh3,Rtrim(jtzz)as jtzz ,Rtrim(headimgurl)as headimgurl ,Rtrim(subscribe)as subscribe,Rtrim(subscribe_time) as subscribe_time,Rtrim(isaddbz)as isaddbz,Rtrim(isfriendbz) as isfriendbz from gyb_user where userid='"+Openid+"' ";
	try {
		json=new JSONValidatingWriter().write(
			        new QueryRunner().query(conn, sql, new MapListHandler()));
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
	}
	/**
	 *获取科室信息
	 * @return
	 */
	public String getksjson(){
		Connection conn = JDBC.getConnection();	
		String json = null;
		String sql="select Rtrim(ksbm)as ksbm,Rtrim(ksmc) as ksmc  from gyb_ks  where tybz=0 and sfghks=1";
	try {
		json=new JSONValidatingWriter().write(
			        new QueryRunner().query(conn, sql, new MapListHandler()));
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
	}
	/**
	 * 通过医疗卡号获取常用就诊人是否以前注册
	 * @return
	 */
	public String getfriendinfotocheck(String ylkh){
		Connection conn = JDBC.getConnection();	
		String json = null;
		String sql="select top 1 RTRIM(ylkh) as ylkh,Rtrim(ghxh)ghxh,ghrq ,Rtrim(ylklxbm)as ylklxbm,Rtrim(brid) brid,Rtrim(sfzh)sfzh,Rtrim(brxm)brxm,Rtrim(brnl)brnl,Rtrim(brnldw)brnldw,Rtrim(mzbm)mzbm,Rtrim(brxb)brxb,Rtrim(jtzz)jtzz,Rtrim(sj)sj from v_his_brjbxx where ylkh='"+ylkh+"' order by ghrq desc";
	try {
		json=new JSONValidatingWriter().write(
			        new QueryRunner().query(conn, sql, new MapListHandler()));
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
	}
	
	/**
	 * 获取该账户以前是否绑定此卡
	 * @param ylkh
	 * @param sfzh
	 * @param ph
	 * @return
	 */
	public String getfriendIsRigster(String ylkh,String sfzh,String ph){
		Connection conn = JDBC.getConnection();	
		String json = null;
		String sql="select top 1 Rtrim(sfzh)sfzh,Rtrim(brxm)brxm,Rtrim(brxb)brxb,Rtrim(brnl)brnl,Rtrim(brjtzz)brjtzz,Rtrim(ph)ph,Rtrim(brdh)brdh,Rtrim(ylkh)ylkh,Rtrim(brid)brid,Rtrim(brnldw)brnldw from gyb_user_friend where (sfzh='"+sfzh+"'or ylkh='"+ylkh+"' and (ph='"+ph+"'))";
	try {
		json=new JSONValidatingWriter().write(
			        new QueryRunner().query(conn, sql, new MapListHandler()));
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
	}
	/**
	 * 插入常用就诊人表操作
	 * @param sfzh
	 * @param brxm
	 * @param brnl
	 * @param brxb
	 * @param brjtzz
	 * @param ph
	 * @param brdh
	 * @param ylkh
	 * @param JDSJ
	 * @param brnldw
	 * @return
	 */
	public boolean  insertfriend(String sfzh,String brxm,String brnl,String brxb,String brjtzz,String ph,String brdh,String ylkh,String brnldw) {
		Dao dao = Dao.getInstance();
		boolean ok = false;
		String sql = "insert gyb_user_friend (sfzh,brxm,brnl,"
				+ "brxb,brjtzz,ph,brdh,ylkh,brnldw)values('"+sfzh+"','"+brxm+"','"+brnl+"','"+brxb+"','"+brjtzz+"','"+ph+"','"+brdh+"','"+ylkh+"','"+brnldw+"')" ;
		ok = dao.insert(sql);
		if(ok==true){
			ok= updateuser( sfzh, ph,ylkh) ;
		}
		return ok;
	}
	
	public boolean  updateuser(String sfzh,String ph,String ylkh) {
		Dao dao = Dao.getInstance();
		weinxinsql weinxinsql=new weinxinsql();
		 String json=weinxinsql.userinfotojison(ph);
		 userinfobean ufbean=JSON.parseObject(json, userinfobean.class);
		 String addfrien="0";
				 int k=Integer.parseInt(ufbean.getIsfriendbz())+1;
				 if(k==3){
					 addfrien="1";
				 }
			String	 s=String.valueOf(k);
			String sql=null;
		 if((ufbean.getFriend1().length()<2)&&(ufbean.getYlkh1().length()<2)){
			sql = " update gyb_user set  friend1='" + sfzh + "',ylkh1='"+ylkh+"',isfriendbz='"+s+"',isaddbz='"+addfrien+"' where userid='"+ph+"'";
		 }
		 else if ((ufbean.getFriend2().length()<2)&&(ufbean.getYlkh2().length()<2)) {
				sql = " update gyb_user set  friend2='" + sfzh + "',ylkh2='"+ylkh+"',isfriendbz='"+s+"',isaddbz='"+addfrien+"' where userid='"+ph+"'";
		}
		 else if ((ufbean.getFriend3().length()<2)&&(ufbean.getYlkh3().length()<2)) {
				sql = " update gyb_user set  friend3='" + sfzh + "',ylkh3='"+ylkh+"',isfriendbz='"+s+"',isaddbz='"+addfrien+"' where userid='"+ph+"'";
		}
				
		boolean ok = false;

		ok = dao.insert(sql);
		return ok;
	}
}
