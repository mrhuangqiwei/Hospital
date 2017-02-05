package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

/**
 * 获取常用就诊人预约信息通过openid
 * @author Administrator
 *
 */
public class GetFriendsYyInfo {
	/**
	 * 通过openid获取病人预约信息
	 * @param Openid
	 * @return
	 */
	public String getfriendsYyinfo(String Openid){
		Connection conn = JDBC.getConnection();	
		String json = null;
		String sql="select  ghb_yygh.brxm,ghb_yygh.sfzh, ghb_yygh.ylkh,ghb_yygh.yyys,gyb_czy.czyxm,(select  CONVERT(varchar,ghb_yygh.yyghrq,120))as yyrqmx,ghb_yygh.sj,gyb_ks.ksmc,(select  CONVERT(varchar(10),ghb_yygh.yyghrq,120))as yyrq, ghb_yygh.yyghid,ghb_yygh.brid,gyb_czy.mzsbdd from ghb_yygh,gyb_czy,gyb_ks ,gyb_user where gyb_czy.czybm=ghb_yygh.yyys and gyb_ks.ksbm=ghb_yygh.yyks and((ghb_yygh.sfzh=gyb_user.friendid1 or ghb_yygh.sfzh=gyb_user.friendid2 or ghb_yygh.sfzh=gyb_user.friendid3)or(ghb_yygh.ylkh=gyb_user.ylkh1 or ghb_yygh.ylkh=gyb_user.ylkh2 or ghb_yygh.ylkh=gyb_user.ylkh3))and gyb_user.userid='"+Openid+"'";
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
