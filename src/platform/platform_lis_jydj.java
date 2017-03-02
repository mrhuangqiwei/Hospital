package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platform_lis_jydj {
	public String getplatformjydj(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		 Map<String, String>jgms=new HashMap<String, String>();
		 Map<String, String>czy=new HashMap<String, String>();
		 jgms=getLisXzjg();
		 czy=getIdInfo();
		list=getjydjlist(kssj, jssj);
		String hospitalid=GlobalConfigUtil.getHospitalid();
		String sqlString="";
		for(int i=0;i<list.size();i=i+21){
String sqysxm="";String zxysxm="";String shryxm="";
if(czy.containsKey(list.get(i+9))){sqysxm=czy.get(list.get(i+9));}else{sqysxm="";}
if(czy.containsKey(list.get(i+17))){zxysxm=czy.get(list.get(i+17));}else{zxysxm="";}		
if(czy.containsKey(list.get(i+20))){shryxm=czy.get(list.get(i+20));}else{shryxm="";}	
String sql="insert into platform_lis_jydj(hospitalid,jyxh,brxm,xb,cwh,lx,bah,"
+ "nl,nldw,ksmc,sqysxm,ybmc,lczd,sqrq,mc,bbbh,djrq,zxysxm,zxsbmc,shrq,shryxm)"
+ "values('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"',"+
"'"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+
"','"+list.get(i+6)+"',"
+ "'"+list.get(i+7)+"','"+list.get(i+8)+"','"+sqysxm+"','"+list.get(i+10)+"',"
+ "'"+list.get(i+11)+"','"+list.get(i+12)+"',"
+ "'"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"','"+zxysxm+"',"
+ "'"+list.get(i+18)+"','"+list.get(i+19)+"','"+shryxm+"')";
	sqlString=sqlString+sql+"\t";	
		}
		return sqlString;
	}
	
	public List<String>getjydjlist(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		System.out.print(kssj +"\t"+jssj);
		Statement stmt;
		String sql="select jyxh,brxm,xb,cwh,lx ,bah,nl,nldw,RTRIM(gyb_ks.ksmc)ksmc,RTRIM(sqys)sqys,lis_ybbm.ybmc,Rtrim(isnull(lczd,''))lczd ,sqrq,cyrq,lis_jyxm.mc ,bbbh,VIEW_his_jy.djrq,RTRIM(zxys)zxys,Rtrim(lis_jysb.HOSTNAME)HOSTNAME,shrq,RTRIM(shry)shry from  VIEW_his_jy,lis_jysb ,lis_ybbm,lis_jyxm ,gyb_ks "
				+ "where sqrq>='"+kssj+"' and sqrq<='"+jssj+"'and (VIEW_his_jy.zxsb=lis_jysb.sbbm)and(lis_ybbm.ybbm=VIEW_his_jy.ybbm) and(VIEW_his_jy.jyxm=lis_jyxm.bm)and (VIEW_his_jy.ksbm=gyb_ks.ksbm) ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("jyxh"));
			list.add(rs.getString("brxm"));
			list.add(rs.getString("xb"));
			list.add(rs.getString("cwh"));
			list.add(rs.getString("lx"));
			list.add(rs.getString("bah"));
			list.add(rs.getString("nl"));
			list.add(rs.getString("nldw"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("sqys"));
			list.add(rs.getString("ybmc"));
			list.add(rs.getString("lczd"));
			list.add(rs.getString("sqrq"));
			list.add(rs.getString("cyrq"));
			list.add(rs.getString("mc"));
			list.add(rs.getString("bbbh"));
			list.add(rs.getString("djrq"));
			list.add(rs.getString("zxys"));
			list.add(rs.getString("HOSTNAME"));
			list.add(rs.getString("shrq"));
			list.add(rs.getString("shry"));
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
	/**
	 * 获取lis结果名称
	 * @return
	 */
	public Map<String, String> getLisXzjg(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql=" SELECT RTRIM(lis_xzjg.bm)as bm, RTRIM(lis_xzjg.mc) as mc FROM lis_xzjg     where stop <> '1'";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{
		list.add(rs.getString("bm"));
		list.add(rs.getString("mc"));
		
		}
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Map<String, String>map=new HashMap<String, String>();
	for(int i=0;i<list.size();i=i+2){
		map.put(list.get(i), list.get(i+1));
	}
	return map;
	}
	/**
	 * 获取操作员编码和操作员姓名
	 * @return
	 */
	public Map<String, String>getIdInfo(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="select RTRIM(czybm)czybm , RTRIM (czyxm)czyxm from  gyb_czy";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{
		list.add(rs.getString("czybm"));
		list.add(rs.getString("czyxm"));
		
		}
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Map<String, String>map=new HashMap<String, String>();
	for(int i=0;i<list.size();i=i+2){
		map.put(list.get(i), list.get(i+1));
	}
	return map;
	}
	
	
}
