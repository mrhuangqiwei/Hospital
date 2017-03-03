package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platform_ris_label {
public String getRislabel(String rid){
	List<String> list=new ArrayList<String>();
	list=getrislabellist(rid);
	String hospitalid=GlobalConfigUtil.getHospitalid();
	String sqlString="";
	for(int i=0;i<list.size();i=i+6){
		String sql=" insert into platform_ris_label(hospitalid,id,studyReportID,labelid,labelno,"
	+ "labelname,labelvalue) values('"+hospitalid+"','"+list.get(i)+"',"
	+ "'"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"')";
	sqlString=sqlString+sql+"\t";
	}
	return sqlString;
}
	
	
	
	public List<String>getrislabellist(String rid){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		//System.out.print(kssj +"\t"+jssj);
		Statement stmt;
		String sql="select Rtrim(id)id,Rtrim(studyReportID)studyReportID,"
+ "Rtrim(labelid)labelid,Rtrim(labelno)labelno,Rtrim(labelname)labelname,labelvalue from ris_StudyReportLabel "
				+ "where  id>'"+rid+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("id"));
			list.add(rs.getString("studyReportID"));
			list.add(rs.getString("labelid"));
			list.add(rs.getString("labelno"));
			list.add(rs.getString("labelname"));
			list.add(rs.getString("labelvalue").replace("'", "、"));
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
