package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class Platformghb {

	
	/**
	 * 获取挂号信息
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	public String getbrghxx(String kssj,String jssj ){
		List<String> list=new ArrayList<String>();
		list=getghxxlist(kssj, jssj);
		String hospitalid=GlobalConfigUtil.getHospitalid();
		String sqlString="";
		for(int i=0;i<list.size();i=i+16){
String sql="insert into ghb_brgh(hospitalid,ghxh,ghzlmc,ghfsmc,yyghid,fbmc,brid,czybm,ywckbm,"
+ "ghrq,ghxq,ghksmc,ghysxm,zyh,czyks)values"
+ "('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"',"
		+ "'"+list.get(i+4)+"','"+list.get(i+5)+"','"+list.get(i+6)+"','"+list.get(i+7)+"',"
+ "'"+list.get(i+8)+"','"+list.get(i+9)+"','"+list.get(i+11)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"')";
	sqlString=sqlString+sql+"\t";	
		}
	return sqlString;	
	}
	
	
	/**
 * 获取门诊病人信息
 * @param ghxh
 * @return
 */
public List<String>getghxxlist(String kssj,String jssj){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="select Rtrim(ghxh)ghxh,Rtrim(ghzlmc)ghzlmc,Rtrim(ghfsmc)ghfsmc,"
+ "isnull(yyghid,'')yyghid,Rtrim(fbmc)fbmc,Rtrim(brid)brid,Rtrim(v_ghb_brgh.czybm)czybm,"
+ "Rtrim(v_ghb_brgh.ywckbm)ywckbm,ghrq,ghxq,Rtrim(ghks)ghks ,Rtrim(ksmc)ksmc,Rtrim(jzys)jzys,"
+ "Rtrim(czyxm)ghysxm,Rtrim(zyh)zyh,Rtrim(czyks)czyks from v_ghb_brgh ,ghb_ghfs ,ghb_ghzl ,gyb_brfb,gyb_ks ,"
+ "gyb_czy where ghrq>='"+kssj+"' and ghrq<='"+jssj+"' and"
+ " v_ghb_brgh.sfjz='1'and(v_ghb_brgh.ghzlbm=ghb_ghzl.ghzlbm)and(v_ghb_brgh.ghfsbm=ghb_ghfs.ghfsbm)"
+ " and(v_ghb_brgh.fbbm=gyb_brfb.fbbm)and(v_ghb_brgh.ghks=gyb_ks.ksbm)and(v_ghb_brgh.jzys=gyb_czy.czybm)"
+ " order by ghxh desc";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{list.add(rs.getString("ghxh"));
		list.add(rs.getString("ghzlmc"));
		list.add(rs.getString("ghfsmc"));
		list.add(rs.getString("yyghid"));
		list.add(rs.getString("fbmc"));
		list.add(rs.getString("brid"));
		list.add(rs.getString("czybm"));
		list.add(rs.getString("ywckbm"));
		list.add(rs.getString("ghrq"));
		list.add(rs.getString("ghxq"));
		list.add(rs.getString("ghks"));
		list.add(rs.getString("ksmc"));
		list.add(rs.getString("jzys"));
		list.add(rs.getString("ghysxm"));
		list.add(rs.getString("zyh"));
		list.add(rs.getString("czyks"));
		}
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
}
