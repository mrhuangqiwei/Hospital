package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

import checkutil.IsWeixinUser;

import com.alibaba.fastjson.JSON;

import bean.FriendsBean;
import bean.userinfobean;

public class Deletefriend {
	Dao dao = Dao.getInstance();
	
	
	/**
	 * 删除长用就诊人信息
	 * @param sql
	 * @return
	 */
	public boolean deletefriendinfo(String openid,String sfzh,String ylkh){
		boolean ok=false;
		
		weinxinsql weinxinsql=new weinxinsql();
		String friendinfo=Getfriendinfo(openid, sfzh, ylkh);
		String json1=friendinfo.substring(1, friendinfo.length() - 1);
		System.out.print(json1);
		FriendsBean friendsBean=JSON.parseObject(json1, FriendsBean.class);
		String userinfo=weinxinsql.userinfotojison(openid);
		  String json2=userinfo.substring(1,userinfo.length() - 1);
		  userinfobean userinfobea=JSON.parseObject(json2, userinfobean.class);
		  if(updatefriendinfo(openid, sfzh, ylkh, userinfobea, friendsBean));{
			  String sql="delete gyb_user_friend where ph='"+openid+"' and sfzh ='"+sfzh+"' and ylkh=''"+ylkh+"";
		ok=	  dao.delete(sql);		  }
	 
	    return ok;  
	}
	
	public boolean updatefriendinfo(String openid,String sfzh,String ylkh,userinfobean userinfobea,FriendsBean friendsBean){
		 String k=userinfobea.getIsfriendbz().trim();
		 int m=Integer.parseInt(k)-1;
		 int l=0;
		 boolean ok=false;
		 String sql="" ;
		 String mmString=String.valueOf(m);
		 if(userinfobea.getFriend1().equals(friendsBean.getSfzh())&&userinfobea.getYlkh1().equals(friendsBean.getYlkh())){
			 sql=" update gyb_user set  friendid1='"+""+"',ylkh1='"+""+"',isfriendbz='"+m+"',isaddbz='"+0+"' where userid = '"+openid+"' "; 
		 }
		else if (userinfobea.getFriend2().equals(friendsBean.getSfzh())&&userinfobea.getYlkh2().equals(friendsBean.getYlkh())) {
			 sql=" update gyb_user set  friendid2='"+""+"',ylkh2='"+""+"',isfriendbz='"+m+"',isaddbz='"+0+"' where userid = '"+openid+"' ";
		}
		else if (userinfobea.getFriend3().equals(friendsBean.getSfzh())&&userinfobea.getYlkh3().equals(friendsBean.getYlkh())) {
			 sql=" update gyb_user set  friendid3='"+""+"',ylkh3='"+""+"',isfriendbz='"+m+"',isaddbz='"+0+"' where userid = '"+openid+"' ";
		}
		
		ok=dao.update(sql);
		return ok;}
	/**
	 * 获取单个常用就诊人信息
	 * @param openid
	 * @param sfzh
	 * @param ylkh
	 * @return
	 */
	public String Getfriendinfo(String openid,String sfzh,String ylkh){
		String sql="select Rtrim(sfzh)sfzh,Rtrim(ylkh)ylkh,Rtrim(brdh)brdh, Rtrim(brxm)brxm,Rtrim(brnl)brnl,Rtrim(brxb)brxb,Rtrim(brjtzz)jtzz,Rtrim(brnldw)nldw,RTRIM(ph)ph from gyb_user_friend where "
				+ "ph='"+openid+"' and sfzh='"+sfzh+"' and ylkh='"+ylkh+"' " ;
		Connection conn = JDBC.getConnection();	
		String json = null;
		
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
		
	
}
