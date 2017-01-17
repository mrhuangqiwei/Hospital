package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

public class UserRecordSql {

	public String GetUserRecordByYlkh(String ylkh){
		Connection conn = JDBC.getConnection();	
		String sql="select RTRIM(ylkh)ylkh,RTRIM(ylklxbm)ylklxbm ,Rtrim(ghxh)ghxh,Rtrim(brid)brid ,convert(varchar(10),ghrq ,120)as ghrq,Rtrim(sfzh)sfzh ,Rtrim(brxm)brxm ,Rtrim(brnl)brnl ,Rtrim(brnldw)brnldw ,Rtrim(brxb)brxb ,Rtrim(jtzz)jtzz  ,Rtrim(sj)sj from v_his_brjbxx where ylkh='"+ylkh+"'";
		String json="";
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
	
	public String GetUserRecordBySfzh(String sfzh){
		Connection conn = JDBC.getConnection();	
		String sql="select RTRIM(ylkh)ylkh,RTRIM(ylklxbm)ylklxbm ,Rtrim(ghxh)ghxh,Rtrim(brid)brid ,convert(varchar(10),ghrq ,120)as ghrq,Rtrim(sfzh)sfzh ,Rtrim(brxm)brxm ,Rtrim(brnl)brnl ,Rtrim(brnldw)brnldw ,Rtrim(brxb)brxb ,Rtrim(jtzz)jtzz  ,Rtrim(sj)sj from v_his_brjbxx1 where sfzh='"+sfzh+"'";
		String json="";
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
