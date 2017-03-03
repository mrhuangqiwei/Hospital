package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platform_ris_dj {
/**
 * 通过开始时间和结束时间获取ris登记ID
 * @param kssj
 * @param jssj
 * @return
 */
public String getPartformrisdj(String kssj,String jssj){
	List<String> list=new ArrayList<String>();
	list=getjrisdjlist(kssj, jssj);
	String hospitalid=GlobalConfigUtil.getHospitalid();
	String sqlString="";
	for(int i=0;i<list.size();i=i+35)
{String sql="insert into platform_pacx_id(hospitalid,NAME,SEX,PINYIN,"
+ "CLINICNO,INPATIENTNO,PATIENTID,STUDYID,AGE,AGEUNIT,LODGESECTION,LODGEDOCTOR,"
+ "LODGEDATE,BEDNO,applyNO,applySerialNumber,applyItem,applyitemAll,applyID,"
+ "CLIISINPAT,ENROLDOCTOR,ENROLDATE,SURGERYRESULT,CHECKPURPOSE,STATUS,"
+ "CLASSNAME,PHOTONO,TOTALFEE,INHOSPITALNO,MODALITYNAME,CHECKDATE,CHECKDOCTOR,"
+ "PARTOFCHECK,reportDate,reportDoctor,accession_num)values"
+ "('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"','"+list.get(i+6)+"','"+list.get(i+7)+"','"+list.get(i+8)+"','"+list.get(i+9)+"',"
		+ "'"+list.get(i+10)+"','"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"','"+list.get(i+17)+"','"+list.get(i+18)+"','"+list.get(i+19)+"',"
	+ "'"+list.get(i+20)+"','"+list.get(i+21)+"','"+list.get(i+22)+"','"+list.get(i+23)+"','"+list.get(i+24)+"','"+list.get(i+25)+"','"+list.get(i+26)+"',"
			+ "'"+list.get(i+27)+"','"+list.get(i+28)+"','"+list.get(i+29)+"','"+list.get(i+30)+"','"+list.get(i+31)+"','"+list.get(i+32)+"','"+list.get(i+33)+"','"+list.get(i+34)+"')";
		sqlString=sqlString+sql+"\t";
	}
	return sqlString;
}
	
	
	
	/**
	 * 获取ris登记信息
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	public List<String>getjrisdjlist(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		System.out.print(kssj +"\t"+jssj);
		Statement stmt;
		String sql="select RTRIM(NAME)as NAME,RTRIM(SEX) as SEX,RTRIM(PINYIN) as PINYIN,RTRIM(isnull(CLINICNO,''))as CLINICNO,RTRIM(isnull(INPATIENTNO,''))as INPATIENTNO,RTRIM(PATIENTID) as PATIENTID,RTRIM(STUDYID)as STUDYID,RTRIM(AGE)as AGE,RTRIM(AGEUNIT) as AGEUNIT ,RTRIM(isnull(LODGESECTION,'')) as LODGESECTION,RTRIM(LODGEDOCTOR)as LODGEDOCTOR,LODGEDATE, RTRIM(isnull (BEDNO,''))as BEDNO,RTRIM(isnull(applyNO,''))as applyNO,RTRIM(applySerialNumber)as applySerialNumber,RTRIM(isnull(applyitem,''))as applyitem,RTRIM(isnull(applyitemAll,''))as applyitemAll,RTRIM( isnull((cast(applyID as varchar(20))),''))as applyID,RTRIM(CLIISINPAT)as CLIISINPAT,RTRIM(ENROLDOCTOR)as ENROLDOCTOR, ENROLDATE,RTRIM (isnull(SURGERYRESULT,''))as SURGERYRESULT,RTRIM(isnull(CHECKPURPOSE,''))as CHECKPURPOSE, RTRIM(STATUS)as STATUS ,RTRIM(CLASSNAME)as CLASSNAME, RTRIM(PHOTONO)as PHOTONO,RTRIM(isnull(TOTALFEE,''))as TOTALFEE,RTRIM(isnull(INHOSPITALNO,''))as INHOSPITALNO, RTRIM(isnull(MODALITYNAME,''))as MODALITYNAME,CHECKDATE,RTRIM(CHECKDOCTOR)as CHECKDOCTOR ,RTRIM(isnull(PARTOFCHECK,''))as PARTOFCHECK,isnull(reportDate,'')reportDate, RTRIM(isnull(reportDoctor,''))as reportDoctor,RTRIM(isnull(accession_num,''))as accession_num from view_pacs_id  "
				+ "where LODGEDATE>='"+kssj+"' and LODGEDATE<='"+jssj+"' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("NAME"));
			list.add(rs.getString("SEX"));
			list.add(rs.getString("PINYIN"));
			list.add(rs.getString("CLINICNO"));
			list.add(rs.getString("INPATIENTNO"));
			list.add(rs.getString("PATIENTID"));
			list.add(rs.getString("STUDYID"));
			list.add(rs.getString("AGE"));
			list.add(rs.getString("AGEUNIT"));
			list.add(rs.getString("LODGESECTION"));
			list.add(rs.getString("LODGEDOCTOR"));
			list.add(rs.getString("LODGEDATE"));
			list.add(rs.getString("BEDNO"));
			list.add(rs.getString("applyNO"));
			list.add(rs.getString("applySerialNumber"));
			list.add(rs.getString("applyItem"));
			list.add(rs.getString("applyitemAll"));
			list.add(rs.getString("applyID"));
			list.add(rs.getString("CLIISINPAT"));
			list.add(rs.getString("ENROLDOCTOR"));
			list.add(rs.getString("ENROLDATE"));
			list.add(rs.getString("SURGERYRESULT"));
			list.add(rs.getString("CHECKPURPOSE"));
			list.add(rs.getString("STATUS"));
			list.add(rs.getString("CLASSNAME"));
			list.add(rs.getString("PHOTONO"));
			list.add(rs.getString("TOTALFEE"));	
			list.add(rs.getString("INHOSPITALNO"));
			list.add(rs.getString("MODALITYNAME"));
			list.add(rs.getString("CHECKDATE"));
			list.add(rs.getString("CHECKDOCTOR"));
			list.add(rs.getString("PARTOFCHECK"));
			list.add(rs.getString("reportDate"));
			list.add(rs.getString("reportDoctor"));
			list.add(rs.getString("accession_num"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(list);
		return list;
	}
	
}
