package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.If;

import utils.ConvertTime;
import utils.GlobalConfigUtil;
import bean.GhfyxmbmBean;
import bean.YyxxBean;
/**
 * 当检测到时间到达挂号时间是自动进行病人挂号程序
 * @author Administrator
 *
 */
public class Brghsql {
	//Dao dao = Dao.getInstance();
/**
 * 自动挂号缴费
 */
/**
public void zdgh(){
YyghSql yyghSql=new YyghSql();

List<String> list=new ArrayList<String>();
List<String> list1=new ArrayList<String>();
Map<String, String>ghzlMap=new HashMap<String, String>();
//诊疗项目编码
ghzlMap=getghzlbm();
//Map<String, String>map1=new HashMap<String, String>();

//map1=getmxxmdj();
//获取明细费用单价
Map<String, String>czyghfyMap=new HashMap<String, String>();
//操作员挂号费用
czyghfyMap=JdbcUtilSql.Getczyghfy();
List<YyxxBean> list3= new ArrayList<YyxxBean>();
//获取需要插入挂号表和结算记录表，门诊费用表的病人信息
list3=getbuseryyxx();

String csxh="000001";

for(int i=0;i<list3.size();i++){
	//序号前段数字如20170213000000
	Long id=getyyghid();
	String ghxh;
	String jsjlid;
	
	//获取上次挂号 xhlx , xh , ssrq , cslx 
	list=getusertime();
	//获取结算记录的xhlx , xh , ssrq , cslx
		list1=getuserjsjltime();
	//获取系统当前日期
	String ssrq = yyghSql.getdatetime();
	//获取挂号时间和当前日期的差
    int k=yyghSql.getdatedifference(list.get(2));
    //获取结算时间和当前时间的差
	int m=yyghSql.getdatedifference(list1.get(2));
	//获取挂号序号的尾数
	int xh = Integer.parseInt(list.get(1).trim()) + 1;
	//根据输入的尾号如“23”返回如"000023"的字符串，用来更新业务序号表
	String xh1=getxh(xh);
	int jsxh=Integer.parseInt(list1.get(1).trim()) + 1;
	//结算记录的 末尾序号表
	String jsxh1=getxh(jsxh);
	// System.out.print(map1.get(list3.get(i).getMxfyxmbm())+"\t");
	 List<GhfyxmbmBean>ghfyxmbmBeans= new ArrayList<GhfyxmbmBean>();
	 //获取挂号费用明细费用项目编码
	 ghfyxmbmBeans=JdbcUtilSql.Getysghmxfybm(list3.get(i).getYyys());
	 //挂号诊疗编码
	 String ghzlbm="";
	 if(ghzlMap.containsKey(list3.get(i).getYyys().trim())){
		 ghzlbm=ghzlMap.get(list3.get(i).getYyys().trim());
	 }else{ghzlbm="";}
	 String ghfsbm= GlobalConfigUtil.getGhfsbm();
	 String fbbm=GlobalConfigUtil.getFbbm();
	 String ghxq=GlobalConfigUtil.getGhxq();
	 String sfjz="0";
	 String jzxe="0.00";
	 //费用合计
	 String fyhj="";
	 String sqlString="";
	 if(czyghfyMap.containsKey(list3.get(i).getYyys().trim())){fyhj=czyghfyMap.get(list3.get(i).getYyys().trim());}else{fyhj="";}
	//情况1 当两个流水号时间都大于等于1时
	 if(k>=1&&m>=1){ 
		 System.out.print("k>=1&&m>=1");
		  ghxh= String.valueOf(id+1)   ;
		  jsjlid=String.valueOf(id+1) ;
	      updateYwxhb(csxh, ssrq);
	      updatejsjl(csxh, ssrq);
	      sqlString=sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	      sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t"; 
	      sqlString =sqlString+inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	      sqlString=sqlString +"\t";
	      String sqlfyString="";
	   for(int y=0;y< ghfyxmbmBeans.size();y++){
	
		   String sql =insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(),ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
	         sqlfyString=sqlfyString+"\t";
	   }
	   sqlString=sqlString+ sqlfyString+"\t";
	   System.out.print(sqlString);
	   dao.insert(sqlString);
	 }
	 //情况2 当挂号时间大于等于1 结算时间小于1时
	 else if (k>=1&&m<1) {
		  System.out.print("k>=1&&m<1");
		  ghxh=String.valueOf(id+1)  ;
	      jsjlid=String.valueOf(id+jsxh) ; 
	      updateYwxhb(csxh, ssrq);
	      updatejsjl(jsxh1, ssrq);
	      
	      sqlString=sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	      sqlString =sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
	     
	   sqlString=sqlString+ inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	 sqlString=sqlString+"\t";
	 String sqlS="";
	   for(int y=0;y< ghfyxmbmBeans.size();y++){
		String sql=insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
		sqlS=sqlS+sql+"\t";
	   }
	   sqlString=sqlString+sqlS+"\t";
	   System.out.print(sqlString);
	   dao.insert(sqlString);
	}
	 //情况3当挂号时间小于1结算时间大于等于1时
	 else if (k<1&&m>=1) {
		 System.out.print("k<1&&m>=1");
		 ghxh=String.valueOf(id+xh)  ;
	      jsjlid=String.valueOf(id+1) ; 
	      
	      updateYwxhb(xh1, ssrq);
	      updatejsjl(csxh, ssrq);
	      sqlString= sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	     sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
	   
	     sqlString=sqlString+ inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	     sqlString=sqlString+"\t";
	     String sqlfyString="";
	    for(int y=0;y< ghfyxmbmBeans.size();y++){
		 String sql= insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
		sqlfyString=sqlfyString+sql+"\t";
	   }
	   sqlString=sqlString +sqlfyString+"\t";
	   System.out.print(sqlString);
	   dao.insert(sqlString);
	}
	 //情况四：当结算时间和挂号时间都小于1时执行
	 else {	 System.out.print("k<1&&m<1");
			ghxh=String.valueOf(id+xh)  ;
		    jsjlid=String.valueOf(id+jsxh) ; 
		    
		    updateYwxhb(xh1, ssrq);
		    updatejsjl(jsxh1, ssrq);
		    sqlString=sqlString+insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
		     sqlString=sqlString+"\t";
		   sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
    
		    sqlString=sqlString+inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	  	    sqlString=sqlString+"\t";
	  	    String sql2="";
		    for(int y=0;y< ghfyxmbmBeans.size();y++){
			 String sql=insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
			sql2=sql2+sql+"\t";
		    }
		    sqlString=sqlString+sql2+"\t";
		    System.out.print(sqlString);
		    dao.insert(sqlString);
	}
	 


}


}**/



	







/**
 * 获取挂号 xhlx , xh , ssrq , cslx 
 * @return
 */
public List<String> getusertime(Connection conn,Statement stmt){
List<String> list=new ArrayList<String>();

String sql=" begin tran  SELECT Top 1 *  From ghb_ywxhb  Where xhlx ='ghxh' ";
try {
	//stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("xhlx"));
	list.add(rs.getString("xh"));
	list.add(rs.getString("ssrq"));
	list.add(rs.getString("cslx"));
	}
///	stmt.close();								// 关闭连接状态对象
	//conn.commit();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return list;
}
/**
 * 获取结算记录的xhlx , xh , ssrq , cslx
 * @return
 */

public List<String> getuserjsjltime(Connection conn ,Statement stmt){
List<String> list=new ArrayList<String>();

String sql="SELECT Top 1 *  From ghb_ywxhb with  (TABLOCK) Where xhlx ='jsjlid'  ";
try {

	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("xhlx"));
	list.add(rs.getString("xh"));
	list.add(rs.getString("ssrq"));
	list.add(rs.getString("cslx"));
	}
	//stmt.close();								// 关闭连接状态对象
	//conn.commit();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return list;
}

/**
 * 获取序号
 * @return
 */

public String  getxh( int xh1,Connection conn ,Statement stmt){
String xh = null;
String sql=" declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+'"+xh1+"' set @book_code=''+right('000000'+@book_code,6) select @book_code  as xh ";
try {
	//stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{xh=rs.getString("xh");
	
	}
	//stmt.close();								// 关闭连接状态对象
	//conn.commit();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return xh;
}
/**跟新业务序号中挂号序号**/
public boolean updateYwxhb(String  xh2, String ssrq,Connection conn ,Statement stmt ){
	boolean ok=false;
	String sql=" update ghb_ywxhb Set xh ='" + xh2 + "' , ssrq ='" + ssrq + "' Where xhlx ='ghxh'  ";
	try {
		ok=stmt.execute(sql);
		//stmt1.close();								// 关闭连接状态对象
		//conn1.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// ok=dao.insert(sql);
	 if(ok=false){
		 System.out.print("更新业务序号表1失败");
	 }
	  return ok;
}
/**
 * 更新业务序号表中结算记录ID
 * @param xh1
 * @param ssrq
 * @return
 */
public boolean updatejsjl(String xh1, String ssrq,Connection conn ,Statement stmt ){
	boolean ok=false;
	String sql=" update ghb_ywxhb Set xh ='" + xh1 + "' , ssrq ='" + ssrq + "' Where xhlx ='jsjlid' ";
	try {
		ok=stmt.execute(sql);
		//stmt2.close();								// 关闭连接状态对象
		//conn2.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 if(ok=false){
		 System.out.print("更新业务序号表1失败");
	 }
	  return ok;
}



//通过时间获取挂号id
public Long  getyyghid(Connection conn,Statement stmt){ 
String fString="";
	//Connection conn = JDBC.getConnection();	
	//Statement stmt;
	String sql="select CONVERT(varchar(12) , getdate(), 112 ) as yyid";
	try {
		//stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{
		fString=rs.getString("yyid");
		}
		//stmt.close();								// 关闭连接状态对象
		//conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	fString=fString+"000000";
	
return Long.parseLong(fString) ;
}
/**
 * 获取需要插入挂号表，结算记录表，门诊费用表中的预约信息。
 * @return
 */
public void  getbuseryyxx(){

	Connection conn = JDBC.getConnection();	
	Statement stmt;
	List<String> list=new ArrayList<String>();

	String befodate=ConvertTime.GetdatebefoYYMMDDHH00();
	String afterdate=ConvertTime.GetdateafterYYMMDDHH00();
	String sql="select Rtrim(ghb_yygh.yyghid)yyghid,Rtrim(ghb_yygh.ywckbm)ywckbm,Rtrim(ghb_yygh.brid)brid,Rtrim(ghb_yygh.czybm)czybm,yyghrq,Rtrim(ghb_yygh.czyks)czyks,Rtrim(ghb_yygh.brxm)brxm,Rtrim(yyks)yyks,Rtrim(yyys)yyys ,Rtrim(yyjfbz)yyjfbz,Rtrim(mxfyxmbm)mxfyxmbm ,Rtrim(ghb_zcxx.brnl)brnl ,Rtrim(ghb_zcxx.brnldw)brnldw from ghb_yygh,ghb_zcxx  where yyghrq>='"+befodate+"'and (yyghrq<='"+afterdate+"') and  (ghb_yygh.brid=ghb_zcxx.brid) ";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{list.add(rs.getString("yyghid"));
		list.add(rs.getString("ywckbm"));
		list.add(rs.getString("brid"));
		list.add(rs.getString("czybm"));
		list.add(rs.getString("yyghrq"));
		list.add(rs.getString("brxm"));
		list.add(rs.getString("yyks"));
		list.add(rs.getString("yyys"));
		list.add(rs.getString("yyjfbz"));
		list.add(rs.getString("mxfyxmbm"));
		list.add(rs.getString("brnl"));
		list.add(rs.getString("brnldw"));
		list.add(rs.getString("czyks"));
		
		}
		List<YyxxBean> lYyxxBeans=new ArrayList<YyxxBean>();
		for(int i=0;i<list.size();i=i+13){
			YyxxBean yyxxBean=new YyxxBean(list.get(i), list.get(i+1), list.get(i+2), list.get(i+3), list.get(i+4),
					list.get(i+5), list.get(i+6), list.get(i+7), list.get(i+8), list.get(i+9), list.get(i+10), list.get(i+11),list.get(i+12));
			lYyxxBeans.add(yyxxBean);
		}
		List<YyxxBean> list3=new ArrayList<YyxxBean>();
		list3=lYyxxBeans;
		Map<String, String>ghzlMap=new HashMap<String, String>();
		//诊疗项目编码
		ghzlMap=getghzlbm(conn,stmt);
		//Map<String, String>map1=new HashMap<String, String>();

		//map1=getmxxmdj();
		//获取明细费用单价
		Map<String, String>czyghfyMap=new HashMap<String, String>();
		//操作员挂号费用
		czyghfyMap=JdbcUtilSql.Getczyghfy(conn,stmt);

for(int i=0;i<list3.size();i++){
	//序号前段数字如20170213000000
	Long id=getyyghid(conn,stmt);
	String ghxh;
	String jsjlid;
	List<String> list5=new ArrayList<String>();
	//获取上次挂号 xhlx , xh , ssrq , cslx 
	list5=getusertime(conn,stmt);
	//获取结算记录的xhlx , xh , ssrq , cslx
	List<String> list1=new ArrayList<String>();
		list1=getuserjsjltime(conn,stmt);
	//获取系统当前日期
	String ssrq = ConvertTime.Getdnowdatexx();
	//获取挂号时间和当前日期的差
    int k=ConvertTime.daysBetween(list5.get(2));
    //获取结算时间和当前时间的差
	int m=ConvertTime.daysBetween(list1.get(2));
	//获取挂号序号的尾数
	int xh = Integer.parseInt(list5.get(1).trim()) + 1;
	//根据输入的尾号如“23”返回如"000023"的字符串，用来更新业务序号表
	String xh1=getxh(xh,conn,stmt);
	int jsxh=Integer.parseInt(list1.get(1).trim()) + 1;
	//结算记录的 末尾序号表
	String jsxh1=getxh(jsxh,conn,stmt);
	// System.out.print(map1.get(list3.get(i).getMxfyxmbm())+"\t");
	 List<GhfyxmbmBean>ghfyxmbmBeans= new ArrayList<GhfyxmbmBean>();
	 //获取挂号费用明细费用项目编码
	 ghfyxmbmBeans=JdbcUtilSql.Getysghmxfybm(list3.get(i).getYyys(),conn,stmt);
	 //挂号诊疗编码
	 String ghzlbm="";
	 if(ghzlMap.containsKey(list3.get(i).getYyys().trim())){
		 ghzlbm=ghzlMap.get(list3.get(i).getYyys().trim());
	 }else{ghzlbm="";}
	 String ghfsbm= GlobalConfigUtil.getGhfsbm();
	 String fbbm=GlobalConfigUtil.getFbbm();
	 String ghxq=GlobalConfigUtil.getGhxq();
	 String sfjz="0";
	 String jzxe="0.00";
	 //费用合计
	 String fyhj="";
	 String sqlString="";
	 String csxh="000001";
	 if(czyghfyMap.containsKey(list3.get(i).getYyys().trim())){fyhj=czyghfyMap.get(list3.get(i).getYyys().trim());}else{fyhj="";}
	//情况1 当两个流水号时间都大于等于1时
	 if(k>=1&&m>=1){ 
		 System.out.print("k>=1&&m>=1");
		  ghxh= String.valueOf(id+1)   ;
		  jsjlid=String.valueOf(id+1) ;
	      updateYwxhb(csxh, ssrq,conn,stmt);
	      updatejsjl(csxh, ssrq,conn,stmt);
	      sqlString=sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	      sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t"; 
	      sqlString =sqlString+inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	      sqlString=sqlString +"\t";
	      String sqlfyString="";
	   for(int y=0;y< ghfyxmbmBeans.size();y++){
	
		   String sql2 =insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(),ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
	         sqlfyString=sqlfyString+sql2+"\t";
	   }
	   sqlString=sqlString+ sqlfyString+"\t"+"commit tran";
	   System.out.print(sqlString);
	  // dao.insert(sqlString);
	   stmt.execute(sqlString);
	 }
	 //情况2 当挂号时间大于等于1 结算时间小于1时
	 else if (k>=1&&m<1) {
		  System.out.print("k>=1&&m<1");
		  ghxh=String.valueOf(id+1)  ;
	      jsjlid=String.valueOf(id+jsxh) ; 
	      updateYwxhb(csxh, ssrq,conn,stmt);
	      updatejsjl(jsxh1, ssrq,conn,stmt);
	      
	      sqlString=sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	      sqlString =sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
	     
	   sqlString=sqlString+ inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	 sqlString=sqlString+"\t";
	 String sqlS="";
	   for(int y=0;y< ghfyxmbmBeans.size();y++){
		String sql2=insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
		sqlS=sqlS+sql2+"\t";
	   }
	   sqlString=sqlString+sqlS+"\t"+"commit tran";
	   System.out.print(sqlString);
	   //dao.insert(sqlString);
	   stmt.execute(sqlString);
	}
	 //情况3当挂号时间小于1结算时间大于等于1时
	 else if (k<1&&m>=1) {
		 System.out.print("k<1&&m>=1");
		 ghxh=String.valueOf(id+xh)  ;
	      jsjlid=String.valueOf(id+1) ; 
	      
	      updateYwxhb(xh1, ssrq,conn,stmt);
	      updatejsjl(csxh, ssrq,conn,stmt);
	      sqlString= sqlString+ insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
	      sqlString=sqlString+"\t";
	      sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
	   
	     sqlString=sqlString+ inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	     sqlString=sqlString+"\t";
	     String sqlfyString="";
	    for(int y=0;y< ghfyxmbmBeans.size();y++){
		 String sql3= insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
		sqlfyString=sqlfyString+sql3+"\t";
	   }
	   sqlString=sqlString +sqlfyString+"\t"+"commit tran";
	   System.out.print(sqlString);
	   //dao.insert(sqlString);
	   stmt.execute(sqlString);
	}
	 //情况四：当结算时间和挂号时间都小于1时执行
	 else {	 System.out.print("k<1&&m<1");
			ghxh=String.valueOf(id+xh)  ;
		    jsjlid=String.valueOf(id+jsxh) ; 
		    
		    updateYwxhb(xh1, ssrq,conn,stmt);
		    updatejsjl(jsxh1, ssrq,conn,stmt);
		    sqlString=sqlString+insertbrgh(ghxh, ghzlbm, ghfsbm, fbbm,list3.get(i).getBrid(), list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(), ghxq, list3.get(i).getYyks(), list3.get(i).getYyys(), sfjz, list3.get(i).getCzyks(), jzxe, list3.get(i).getBrnl(), list3.get(i).getBrnldw());
		     sqlString=sqlString+"\t";
		   sqlString=sqlString+updateYyghb(list3.get(i).getYyghid())+"\t";
    
		    sqlString=sqlString+inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	  	    sqlString=sqlString+"\t";
	  	    String sql2="";
		    for(int y=0;y< ghfyxmbmBeans.size();y++){
			 String sql4=insertmzbbrfy(fbbm,list3.get(i).getYwckbm(),ghfyxmbmBeans.get(y).getXlbm(),ghfyxmbmBeans.get(y).getMxfyxmbm(),list3.get(i).getCzybm(),ghfyxmbmBeans.get(y).getDlbm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), ghfyxmbmBeans.get(y).getFydj(), ghfyxmbmBeans.get(y).getFydj(),list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);
			sql2=sql2+sql4+"\t";
		    }
		    sqlString=sqlString+sql2+"\t"+"commit tran";
		    System.out.print(sqlString);
		    //dao.insert(sqlString);
		    stmt.execute(sqlString);
	}
	 


}
		
		
		
		
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


/**
 * 获取挂号诊疗项目编码sql
 * @return
 */
public Map<String, String> getghzlbm(Connection conn ,Statement stmt){
		List<String> list=new ArrayList<String>();
		
		String sql="select Rtrim(czybm)czybm,Rtrim(gyb_czy.ghzlbm)ghzlbm from gyb_czy,ghb_ghzl where ghb_ghzl.ghzlbm=gyb_czy.ghzlbm and gyb_czy.tybz=0";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("czybm"));
			list.add(rs.getString("ghzlbm"));

			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
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



public String insertbrgh(String ghxh,String ghzlbm,String ghfsbm,String 
		fbbm,String brid,String czybm,String ywckbm,String ghrq, String ghxq,
		String ghks,String jzys,String sfjz,String czyks,
		String jzxe,String brnl,String brnldw){

	String sql="INSERT INTO ghb_brgh ( ghxh, ghzlbm, ghfsbm, fbbm, brid, "
			+ "czybm, ywckbm, ghrq,"
			+ " ghxq, ghks,jzys,"
			+ " sfjz, czyks, jzxe, brnl, brnldw)"
			+ " VALUES ( '"+ghxh+"', '"+ghzlbm+"',"
			+ " '"+ghfsbm+"', '"+fbbm+"', '"+brid+"', '"+czybm+"', '"+ywckbm+"',"
			+ "  '"+ghrq+"', "+ghxq+", '"+ghks+"', '"+jzys+"', "+sfjz+", "
					+ "'"+czyks+"', 0.00, '"+brnl+"', '"+brnldw+"')";
return sql;

}
/**
 * 插入结算记录表
 * @param jsjlid
 * @param ghxh
 * @param czybm
 * @param ywckbm
 * @param brid
 * @param fyhj
 * @param ylkzf
 * @param xjzf
 * @param ybkzf
 * @param jsqf
 * @param qfbj
 * @param jszt
 * @param jsrq
 * @param czyks
 * @return
 */
public String  inserjsjl(String jsjlid,String ghxh,String czybm,String ywckbm,String brid,String fyhj,
		String ylkzf,String xjzf,
		String ybkzf,String jsqf,String qfbj,String jszt,String jsrq,String czyks){

	String sql="INSERT INTO ghb_jsjl ( jsjlid, ghxh, jscs, czybm, ywckbm, brid, fyhj, ylkzf,"
			+ " xjzf, ybkzf, jsqf, qfbj,jszt, jsrq, czyks, bzms ) VALUES ( '"+jsjlid+"',"
			+ " '"+ghxh+"', 1, '"+czybm+"', '"+ywckbm+"', '"+brid+"','"+fyhj+"', 0,"
+ "'"+xjzf+"', 0, 0, 0, '0', '"+jsrq+"', '"+czyks+"', '挂号结算' ) ";

return sql;
}
/**
 * 
插入门诊表--病人费用
 * @return
 */
public String insertmzbbrfy(String fbbm, String ywckbm,String xlbm, String mxfyxmbm,String czybm,String dlbm, String sfrq,
		String rybrid,String ryghxh ,String brxm,String fydj,String fyje,String mzys,String mzks,
		String hsks,String ryjsjlid){

	String sql="INSERT INTO mzb_brfy ( fbbm, ywckbm, xlbm, mxfyxmbm, czybm, dlbm, "
			+ "sfrq, rybrid, ryghxh, brxm, fysl, fydj, fyje, yhbl, yhje, mzys, mzks,"
			+ " hsks, yzlx, sfjs, jscs, ryjsjlid, sflx, fzxh ) "
			+ "VALUES ( '"+fbbm+"', '"+ywckbm+"', '"+xlbm+"', '"+mxfyxmbm+"', '"+czybm+"', '"+dlbm+"',"
			+ " '"+sfrq+"', '"+rybrid+"', '"+ryghxh+"',"
			+ " '"+brxm+"', 1, '"+fydj+"', '"+fyje+"', 0.00, 0.00,  '"+mzys+"', '"+mzks+"', "
			+ "'"+hsks+"', '1', 1, 1, '"+ryjsjlid+"', '0', 2 )";

return sql;

}



/**
 * 获取明细费用项目单价
 * @return
 */
public Map<String, String> getmxxmdj(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(mxfyxmbm)mxfyxmbm,Rtrim(fydj) fydj from gyb_mxfyxm where qyzt=0";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("mxfyxmbm"));
			list.add(rs.getString("fydj"));

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
 * 
 * @return
 */
public List<String> getuserczyfphm(String czybm){
List<String> list=new ArrayList<String>();
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql="SELECT qsh , jsh , dqfplx , lpfs , fpjlid FROM gyb_mzdqpj WHERE dqczy ='"+czybm+"' and dqfplx ='2' and lpfs ='0' and fplx ='0' ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("qsh"));
	list.add(rs.getString("jsh"));
	list.add(rs.getString("dqfplx "));
	list.add(rs.getString("lpfs"));
	list.add(rs.getString("fpjlid"));
	}
	stmt.close();								// 关闭连接状态对象
	conn.commit();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return list;
}
/**更新预约挂号表yyclbz**/
public String updateYyghb(String yyghid ){
	String sql="update ghb_yygh set yyclbz='1' where yyghid='"+yyghid+"'";
 return sql;
}

}