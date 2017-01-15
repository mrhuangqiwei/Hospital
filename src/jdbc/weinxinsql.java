package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UpdateModelException;

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
		String sql="select Rtrim(userid)as userid,Rtrim(username)as username,RTRIM(isnull(city,''))as city,RTRIM(isnull(province,''))as province,RTRIM(isnull(county,''))as county, Rtrim(isnull(sex,''))as sex,Rtrim(isnull(friendid1,'')) as friend1,Rtrim(isnull(friendid2,''))as friend2,Rtrim(isnull(friendid3,''))as friend3,Rtrim(isnull(ylkh1,''))as ylkh1,Rtrim(isnull(ylkh2,''))as ylkh2,Rtrim(isnull(ylkh3,''))as ylkh3,Rtrim(isnull(jtzz,''))as jtzz ,Rtrim(isnull(headimgurl,''))as headimgurl ,Rtrim(isnull(subscribe,''))as subscribe,Rtrim(isnull(subscribe_time,'')) as subscribe_time,Rtrim(isaddbz)as isaddbz,Rtrim(isfriendbz) as isfriendbz from gyb_user where userid='"+Openid+"' ";
	try {
		json=new JSONValidatingWriter().write(
			        new QueryRunner().query(conn, sql, new MapListHandler()));
		//conn.close();
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
		//conn.close();
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
		//conn.close();
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
		//conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
	}
	/** 插入、修改、删除记录 */
	public boolean longHaul(String sql) {
		boolean isLongHaul = true;						// 默认持久化成功
		Connection conn = JDBC.getConnection();			// 获得数据库连接
		try {
			conn.setAutoCommit(false);		// 设置为手动提交
			Statement stmt = conn.createStatement();	// 创建连接状态对象
			stmt.executeUpdate(sql);					// 执行SQL语句
											// 关闭连接状态对象
			conn.commit();	
			
			stmt.close();// 提交持久化
		} catch (SQLException e) {
			isLongHaul = false;							// 持久化失败
			try {
				conn.rollback();						// 回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isLongHaul;								// 返回持久化结果
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
	
		boolean ok = false;
	
		String sql = "insert gyb_user_friend (sfzh,brxm,brnl,brxb,brjtzz,ph,brdh,ylkh,brnldw)values('"+sfzh+"','"+brxm+"','"+brnl+"','"+brxb+"','"+brjtzz+"','"+ph+"','"+brdh+"','"+ylkh+"','"+brnldw+"')" ;
		ok = longHaul(sql);
		if(ok==true){
			ok=updateuser( sfzh, ph,ylkh);
		}
		return ok;
	}
	/**更新用户表**/
	public boolean   updateuser(String sfzh,String ph,String ylkh) {

		weinxinsql weinxinsql=new weinxinsql();
		 String json=weinxinsql.userinfotojison(ph);

		 String json1=json.substring(1,json.length()-1);
		
		 System.out.print(json1);
		 userinfobean ufbean=JSON.parseObject(json1, userinfobean.class);
		int addfrien=0;
				 int k=Integer.parseInt(ufbean.getIsfriendbz())+1;
				 if(k==3){
					 addfrien=1;
				 }
			String	 s=String.valueOf(k);
			String sql=null;
		 if((ufbean.getFriend1().equals(""))&&(ufbean.getYlkh1().equals(""))){
			 System.out.print("uuuuuuuu");
			sql = "update gyb_user set  friendid1='"+sfzh+"',ylkh1='"+ylkh+"',isfriendbz='"+k+"',isaddbz='"+addfrien+"' where userid = '"+ph+"'  ";
		 }
		 else if ((ufbean.getFriend2().equals(""))&&(ufbean.getYlkh2().equals(""))) {
			 System.out.print("kkkkkkkkkk");
			 
			 sql = "update gyb_user set  friendid2='"+sfzh+"',ylkh2='"+ylkh+"',isfriendbz='"+k+"',isaddbz='"+addfrien+"' where userid = '"+ph+"' ";
		}
		 else if ((ufbean.getFriend3().equals(""))&&(ufbean.getYlkh3().equals(""))) {
			 
			 System.out.print("yyyyyyyyyy");
			 sql = "update gyb_user set  friendid3='"+sfzh+"',ylkh3='"+ylkh+"',isfriendbz='"+k+"',isaddbz='"+addfrien+"' where userid = '"+ph+" '";
		}
		 
		 System.out.print("lllllllll\n"+sql+"\n");
		boolean ok=false;
		ok=longHaul(sql);
		return ok;
	}
	
	/**
	 * 更新数据库操作
	 * @param sql
	 * @return
	 */
	public boolean Updatesql(String sql){
		boolean ok=false;
		Connection conn = JDBC.getConnection();	
		PreparedStatement stmt1=null;
		try {
			stmt1=conn.prepareStatement(sql);
			stmt1.executeUpdate();  
		      stmt1.close();
		      //conn.close();
			ok=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//实例化PrepareStatement对象  
	    //  stmt1.setString(1, "%数%");  
	    return ok;  
	}
	
}
