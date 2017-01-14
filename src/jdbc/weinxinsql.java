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
	
}
