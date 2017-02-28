package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;
/**
 * 平台门诊诊断
 * @author Administrator
 *
 */
public class platformmzzd {
	public String getmzzd(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		list=getmzzdlist(kssj, jssj);
		String hospitalid=GlobalConfigUtil.getHospitalid();
		String sqlString="";
		for(int i=0;i<list.size();i=i+17 ){
			String sql="insert into platform_mzzd(hospitalid,ghxh,jbbm,mzysxm,ksmc,zdrq,"
					+ "sfzzd,zdmc,fbrq,lczdjtz,clyz,T,P,R,BP,SPO2,zyzdmc,zyfc)"
				+ "values('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"',"
+ "'"+list.get(i+6)+"','"+list.get(i+7)+"','"+list.get(i+8)+"','"+list.get(i+9)+"','"+list.get(i+10)+"',"
		+ "'"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"')";
		sqlString=sqlString+sql+"\t";
		}
		return sqlString;
	}
	
	public List<String>getmzzdlist(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		System.out.print(kssj +"\t"+jssj);
		Statement stmt;
		String sql="select * from view_platform_mzzd where zdrq>='"+kssj+"' and zdrq<='"+jssj+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("ghxh"));
			list.add(rs.getString("jbbm"));
			list.add(rs.getString("czyxm"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("zdrq"));
			list.add(rs.getString("sfzzd"));
			list.add(rs.getString("zdmc"));
			list.add(rs.getString("fbrq"));
			list.add(rs.getString("lczdjtz"));
			list.add(rs.getString("clyz"));
			list.add(rs.getString("T"));
			list.add(rs.getString("P"));
			list.add(rs.getString("R"));
			list.add(rs.getString("BP"));
			list.add(rs.getString("SPO2"));
			list.add(rs.getString("zyzdmc"));
			list.add(rs.getString("zyfc"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(list);
		return list;
	}
}
