package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.JsonHelper;

//门诊应该叫的费用查询
public class mzyjfjlsql {
/**
 * 通过，挂号序号获取门诊应交费用
 * @param ghxh
 */
	public  String  getmzyjfy(String ghxh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String json="";
		String sql="  SELECT  mzb_brfy.id , mzb_brfy.mxfyxmbm , Rtrim(gyb_mxfyxm.mxfyxmmc)mxfyxmmc,  Rtrim(brxm)brxm,         mzb_brfy.fysl ,  mzb_brfy.fydj ,   mzb_brfy.fyje ,   (select SUM(fyje)from mzb_brfy where ryghxh='"+ghxh+"'and  mzb_brfy.sfjs = 0 )AS fyhj,  mzb_brfy.fbbm ,           mzb_brfy.ywckbm ,      mzb_brfy.czybm ,   Rtrim(mzb_brfy.rybrid)brid ,        RTRIM(   mzb_brfy.ryghxh ) ghxh,mzb_brfy.mzys ,Rtrim(gyb_czy.czyxm)mzysxm,mzb_brfy.mzks "
				+ "FROM mzb_brfy ,gyb_mxfyxm ,gyb_czy  WHERE (mzb_brfy.ryghxh = '"+ghxh+"') and(mzb_brfy.mzys=gyb_czy.czybm)  and(mzb_brfy.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) and( mzb_brfy.sfjs = 0 )";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			json=JsonHelper.resultSetToJson(rs);
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	
}
