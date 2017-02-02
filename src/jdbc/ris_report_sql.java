package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import bean.RisBean;
import utils.ConvertTime;

public class ris_report_sql {
	Dao dao = Dao.getInstance();
	/**
	 * 获取检查结果
	 * @param zyh
	 * @return
	 */
	public String  GetRisReport(String zyh) {
		List<String>ristitleList=new ArrayList<String>();
		ristitleList=getristitle(zyh);
		List<RisBean>risBeans=new ArrayList<RisBean>();
		for(int k=0;k<ristitleList.size();k=k+35){
			String sqsj,bgsj;
			if(ristitleList.get(k+11)!=null){
				sqsj=ConvertTime.converttimetoYYMMDDHH(ristitleList.get(k+11));
			}
			else {
				sqsj="";
			}
			if(ristitleList.get(k+32)!=null){
				bgsj=ConvertTime.converttimetoYYMMDDHH(ristitleList.get(k+32));
			}
			else {
				bgsj="";
			}
			List<String>risReport=new ArrayList<String>();
			risReport=getrismx(ristitleList.get(k+6));
			 String laybe1, laybe2;
             laybe1 = risReport.get(0);
             if(!(risReport.get(1)==null)){
            	 laybe2=risReport.get(1);
             }
             else{laybe2=risReport.get(2);}
             RisBean risBean=new RisBean(ristitleList.get(k), ristitleList.get(k+1),ristitleList.get(k+2), ristitleList.get(k+3), ristitleList.get(k+4), ristitleList.get(k+5),
ristitleList.get(k+6),ristitleList.get(k+7), ristitleList.get(k+8), ristitleList.get(k+9), 
ristitleList.get(k+10), sqsj,
ristitleList.get(k+12), ristitleList.get(k+13),ristitleList.get(k+14), ristitleList.get(k+15),ristitleList.get(k+16),ristitleList.get(k+17),
ristitleList.get(k+18), ristitleList.get(k+19), ristitleList.get(k+20),ristitleList.get(k+21),ristitleList.get(k+22), ristitleList.get(k+23),
ristitleList.get(k+24), ristitleList.get(k+25),ristitleList.get(k+26),ristitleList.get(k+27), ristitleList.get(k+28), ristitleList.get(k+29),
ristitleList.get(k+30), ristitleList.get(k+31), bgsj, ristitleList.get(k+33),ristitleList.get(k+34), laybe1, laybe2);
		risBeans.add(risBean);
		}
		String json;
		json=JSONObject.toJSONString(risBeans,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		return json;
	}
	
	
	/**
	 * 获取检查抬头
	 * @param zyh
	 * @return
	 */
	public List<String> getristitle(String zyh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select RTRIM(NAME)as NAME,RTRIM(SEX) as "
				+ "SEX,RTRIM(PINYIN) as PINYIN,RTRIM(CLINICNO)as "
				+ "CLINICNO,RTRIM(INPATIENTNO)as INPATIENTNO,RTRIM(PATIENTID) as PATIENTID,"
				+ "RTRIM(STUDYID)as STUDYID,RTRIM(AGE)as AGE,RTRIM(AGEUNIT) as AGEUNIT ,"
				+ "RTRIM(LODGESECTION) as LODGESECTION,RTRIM(LODGEDOCTOR)as LODGEDOCTOR,"
				+ "LODGEDATE,RTRIM(BEDNO)as BEDNO,RTRIM(applyNO)as applyNO,"
				+ "RTRIM(applySerialNumber)as applySerialNumber,"
				+ "RTRIM(applyitem)as applyitem,"
				+ "RTRIM(applyitemAll)as applyitemAll,RTRIM(applyID)as applyID,"
				+ "RTRIM(CLIISINPAT)as CLIISINPAT,RTRIM(ENROLDOCTOR)as ENROLDOCTOR,"
				+ "ENROLDATE,RTRIM (SURGERYRESULT)as SURGERYRESULT,"
				+ "RTRIM(CHECKPURPOSE)as CHECKPURPOSE,RTRIM(STATUS)as STATUS,"
				+ "RTRIM(CLASSNAME)as CLASSNAME,RTRIM(PHOTONO)as PHOTONO,"
				+ "RTRIM(TOTALFEE)as TOTALFEE,RTRIM(INHOSPITALNO)as INHOSPITALNO,"
				+ "RTRIM(MODALITYNAME)as MODALITYNAME,CHECKDATE,RTRIM(CHECKDOCTOR)as CHECKDOCTOR ,"
				+ "RTRIM(PARTOFCHECK)as PARTOFCHECK,reportDate,RTRIM(reportDoctor)as reportDoctor,"
				+ "RTRIM(accession_num)as accession_num from  view_pacs_id where (CLINICNO='"+zyh+"'OR INPATIENTNO='"+zyh+"') ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("NAME"));
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
			list.add(rs.getString("applyitem"));
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
		return list;
		}
	/**
	 * 获取检查明细
	 * @param zyh
	 * @return
	 */
	public List<String> getrismx(String studyid){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select (select (labelvalue) from ris_StudyReportLabel,ris_StudyReport where (ris_StudyReport.studyReportID=ris_StudyReportLabel.studyReportID)and(labelno='82') and STUDYID='"+studyid+"')as labe1,(select(labelvalue)as labe2 from ris_StudyReportLabel,ris_StudyReport where (ris_StudyReport.studyReportID=ris_StudyReportLabel.studyReportID)and(labelno='83') and STUDYID='"+studyid+"')as labe2,(select (labelvalue)as labe3 from ris_StudyReportLabel,ris_StudyReport where (ris_StudyReport.studyReportID=ris_StudyReportLabel.studyReportID)and(labelno='84') and STUDYID='"+studyid+"')as labe3";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("labe1"));
			list.add(rs.getString("labe2"));
			list.add(rs.getString("labe3"));
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
