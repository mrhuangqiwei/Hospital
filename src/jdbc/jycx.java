package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import bean.LisId1;
import bean.tjjgbean;

public class jycx {
	Dao dao = Dao.getInstance();

public String  getjyjg(String bah) {
	 List<String>jytt=new ArrayList<String>();
	
	 jytt=getuserjyxh(bah);
	 Map<String, String>jgms=new HashMap<String, String>();
	 Map<String, String>czy=new HashMap<String, String>();
	 jgms=getLisXzjg();
	 czy=getIdInfo();
	 List<LisId1>lisId1s=new ArrayList<LisId1>();
	 for(int i=0;i<jytt.size();i=i+25){
		 String sqysxm, zxysxm, shryxm;
		 if (czy.containsKey(jytt.get(i+10))) {
			 sqysxm = czy.get(jytt.get(i+10));
		 } else {
			 sqysxm = "";}
		 if (czy.containsKey(jytt.get(i+20))) {
			 zxysxm = czy.get(jytt.get(i+20));
		 }else {
			 zxysxm = "";}
		 if (czy.containsKey(jytt.get(i+24))) {
			 shryxm = czy.get(jytt.get(i+24));
		 }
		 else {
			 shryxm = "";}
		 String sqrq, cyrq, djrq, shrq;
		 if(jytt.get(i+14)!=null){
         sqrq = converttime(jytt.get(i+14)); }else { sqrq="";}
		 if(jytt.get(i+15)!=null){
         cyrq = converttime(jytt.get(i+15));} else {cyrq="";}
		 if(jytt.get(i+19)!=null){
         djrq = converttime(jytt.get(i+19)); }else {djrq="";}
		 if(jytt.get(i+23)!=null){
		 shrq = converttime(jytt.get(i+23));}else{shrq="";}
         List<String> jyxmjg=new ArrayList<String>();
         jyxmjg=getuserjymx(jytt.get(i));
         
         //System.out.print(jyxmjg.toString()+"\t");
         List<tjjgbean> jyjgbeanList=new ArrayList<tjjgbean>();
         for(int k=0;k<jyxmjg.size();k=k+20){
        	 String jg; String zt="2";
        	 if(jyxmjg.get(k+5).equals("0.00")&&(jyxmjg.get(k+6)!=null)){
        		 if(jgms.containsKey(jyxmjg.get(k+6))){
        			 jg=jgms.get(jyxmjg.get(k+6));zt = "2"; } 
                 else { jg = ""; zt = "2"; }
        		 }
        	 else{Double temp, max, min;
        		 jg=jyxmjg.get(k+5);
        		// System.out.print("----------->+'"+jyxmjg.get(k+5)+"'\t");
        		 temp=Double.valueOf(jyxmjg.get(k+5));
        		// System.out.print("----------->+'"+jyxmjg.get(k+5)+"'");
        		 min=Double.valueOf(jyxmjg.get(k+8));
        		 max=Double.valueOf(jyxmjg.get(k+9));
        		 if (temp < min) { zt = "0"; }
        		 else if (min <= temp && temp <= max) 
        		 { zt = "2"; }else if (temp > max) { zt = "1"; }	
        		 
        	 }
        	 tjjgbean tjjg=new tjjgbean(jyxmjg.get(k), jyxmjg.get(k+1),jyxmjg.get(k+2),jyxmjg.get(k+3),jyxmjg.get(k+4),
    				 jyxmjg.get(k+5),jyxmjg.get(k+6), jyxmjg.get(k+7), jg, 
    				 jyxmjg.get(k+8),jyxmjg.get(k+9), zt, jyxmjg.get(k+10),jyxmjg.get(k+11),
    				 jyxmjg.get(k+12),jyxmjg.get(k+13),jyxmjg.get(k+14),jyxmjg.get(k+15),
    				 jyxmjg.get(k+16),jyxmjg.get(k+17),jyxmjg.get(k+18),jyxmjg.get(k+19));
    		 jyjgbeanList.add(tjjg);
    		 
    	
        	 }
         LisId1 lId1=new LisId1(jytt.get(i), jytt.get(i+1),jytt.get(i+2), jytt.get(i+3),jytt.get(i+4),jytt.get(i+5),jytt.get(i+6),
        		 jytt.get(i+7), jytt.get(i+8), jytt.get(i+9), jytt.get(i+10), sqysxm,jytt.get(i+11), jytt.get(i+12), jytt.get(i+13), sqrq, cyrq, 
        		 jytt.get(i+16), jytt.get(i+17), jytt.get(i+18), djrq,
        		 jytt.get(i+20), zxysxm, jytt.get(i+21), jytt.get(i+22), shrq, jytt.get(i+24), shryxm, jyjgbeanList);
        	
         lisId1s.add(lId1);  
         }
	 
	// System.out.print(lisId1s.get(0).getJyjg());
	 String json=JSONObject.toJSONString(lisId1s,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	  return json;
	 }
	 
	
	
	
	
	
	
	
	/**
 * 获取检验抬头
 * @param bah
 * @return
 */
public List<String> getuserjyxh(String bah){
List<String> list=new ArrayList<String>();
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql="select jyxh,brxm,xb,cwh,lx ,bah,nl,nldw,VIEW_his_jy.ksbm,RTRIM(gyb_ks.ksmc)ksmc,RTRIM(sqys)sqys,VIEW_his_jy.ybbm,lis_ybbm.ybmc,lczd ,sqrq,cyrq,jyxm,lis_jyxm.mc ,bbbh,VIEW_his_jy.djrq,RTRIM(zxys)zxys,zxsb,lis_jysb.HOSTNAME,shrq,RTRIM(shry)shry from  VIEW_his_jy,lis_jysb ,lis_ybbm,lis_jyxm ,gyb_ks where bah='"+bah+"' AND(VIEW_his_jy.zxsb=lis_jysb.sbbm)and(lis_ybbm.ybbm=VIEW_his_jy.ybbm) and(VIEW_his_jy.jyxm=lis_jyxm.bm)and (VIEW_his_jy.ksbm=gyb_ks.ksbm)  ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("jyxh"));
	list.add(rs.getString("brxm"));
	list.add(rs.getString("xb"));
	list.add(rs.getString("cwh"));

	list.add(rs.getString("lx"));
	list.add(rs.getString("bah"));
	list.add(rs.getString("nl"));
	list.add(rs.getString("nldw"));
	list.add(rs.getString("ksbm"));
	list.add(rs.getString("ksmc"));
	list.add(rs.getString("sqys"));
	list.add(rs.getString("ybbm"));
	list.add(rs.getString("ybmc"));
	list.add(rs.getString("lczd"));
	list.add(rs.getString("sqrq"));
	list.add(rs.getString("cyrq"));
	list.add(rs.getString("jyxm"));
	list.add(rs.getString("mc"));
	list.add(rs.getString("bbbh"));
	list.add(rs.getString("djrq"));
	list.add(rs.getString("zxys"));
	list.add(rs.getString("zxsb"));
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
return list;
}


/**
 * 获取检验明细
 * @param jyxh
 * @return
 */
public List<String> getuserjymx(String jyxh){
List<String> list=new ArrayList<String>();
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql="select jyxh,lx,bah ,bz ,xh ,Convert(decimal(18,2),RTRIM(value_N))value_N,RTRIM(value_L)value_L,RTRIM(value_T)value_T,RTRIM(n_min)n_min,RTRIM(n_max)n_max,zwmc,ywmc,sjlx ,dw ,cklx ,xsws,value_N_1,tjdw ,yblx,ckz_t from VIEW_his_jymx where jyxh='"+jyxh+"' order by xh  ,zbxm desc  ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("jyxh"));
	list.add(rs.getString("lx"));
	list.add(rs.getString("bah"));
	list.add(rs.getString("bz"));
	list.add(rs.getString("xh"));
	list.add(rs.getString("value_N"));
	list.add(rs.getString("value_L"));
	list.add(rs.getString("value_T"));
	list.add(rs.getString("n_min"));
	list.add(rs.getString("n_max"));
	list.add(rs.getString("zwmc"));
	list.add(rs.getString("ywmc"));
	list.add(rs.getString("sjlx"));
	list.add(rs.getString("dw"));
	list.add(rs.getString("cklx"));
	list.add(rs.getString("xsws"));
	list.add(rs.getString("value_N_1"));
	list.add(rs.getString("tjdw"));
	list.add(rs.getString("yblx"));
	list.add(rs.getString("ckz_t"));
	
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

/**转换时间格式如2017-08-12**/
public String  converttime(String date){
	
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date date1 = null;
	  try {
         date1=sdf.parse(date);
          
      } catch (ParseException ex) {
          System.out.print("jycx"+"时间转换有错误");
      }
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
      String tablename=dateFormat.format(date1);
      return tablename;
}


}
