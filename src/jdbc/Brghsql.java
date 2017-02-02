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

import bean.YyxxBean;
/**
 * 当检测到时间到达挂号时间是自动进行病人挂号程序
 * @author Administrator
 *
 */
public class Brghsql {
	Dao dao = Dao.getInstance();
/**
 * 自动挂号缴费
 */
public void zdgh(){
YyghSql yyghSql=new YyghSql();

List<String> list=new ArrayList<String>();
List<String> list1=new ArrayList<String>();
Map<String, String>map=new HashMap<String, String>();
//诊疗项目编码
map=getghzlbm();
Map<String, String>map1=new HashMap<String, String>();
//获取明细费用单价
map1=getmxxmdj();
//获取上次结算记录时间


String xhmw = "000001";
List<YyxxBean> list3= new ArrayList<YyxxBean>();
//获取病人预约信息
list3=getbuseryyxx();


//序号前段数字
Long id=getyyghid();
String ghxh;
String jsjlid;
for(int i=0;i<list3.size();i++){
	list1=getuserjsjltime();
	//获取上次挂号时间
	list=getusertime();
	String ssrq = yyghSql.getdatetime();
	//获取挂号ssrq和当前日期的差
int 	k=yyghSql.getdatedifference(list.get(2));
////获取结算时间和当前时间的差
	int m=yyghSql.getdatedifference(list1.get(2));
	//获取挂号序号的尾数
	int xh = Integer.parseInt(list.get(1).replace("0", "").trim()) + 1;
	String xh1=getxh(xh);
	int jsxh=Integer.parseInt(list1.get(1).replace("0", "").trim()) + 1;
	String jsxh1=getxh(jsxh);
	 System.out.print(map1.get(list3.get(i).getMxfyxmbm())+"\t");
	 
	 
	 if(k>=1&&m>=1){
		  ghxh= String.valueOf(id+1)   ;
		  jsjlid=String.valueOf(id+1) ;
		 
		 
	      updateYwxhb("000001", ssrq);
	      updatejsjl("000001", ssrq);
	      insertbrgh(ghxh, map.get(list3.get(i).getYyys()), "02", "02", list3.get(i).getBrid(),
	    		  list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(),
	    		  list3.get(i).getYyks(), list3.get(i).getYyys(), "01", "", "", "", "0", "0",  "0022", 
	    		  "0",  list3.get(i).getYyghrq(),  list3.get(i).getBrnl(),  list3.get(i).getBrnldw(),"2", "0");
	      String fyhj=map1.get(list3.get(i).getMxfyxmbm());
	 inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	insertmzbbrfy(list3.get(i).getYwckbm(),list3.get(i).getMxfyxmbm(),list3.get(i).getCzybm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), fyhj, fyhj,list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);

	 }
	 else if (k>=1&&m<1) {
		 ghxh=String.valueOf(id+1)  ;
	      jsjlid=String.valueOf(id+jsxh) ; 
	      
	      updateYwxhb("000001", ssrq);
	      updatejsjl(jsxh1, ssrq);
	      insertbrgh(ghxh, map.get(list3.get(i).getYyys()), "02", "02", list3.get(i).getBrid(),
	    		  list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(),
	    		  list3.get(i).getYyks(), list3.get(i).getYyys(), "01", "", "", "", "0", "0",  "0022", 
	    		  "0",  list3.get(i).getYyghrq(),  list3.get(i).getBrnl(),  list3.get(i).getBrnldw(),"2", "0");
	      String fyhj=map1.get(list3.get(i).getMxfyxmbm());
	 inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	insertmzbbrfy(list3.get(i).getYwckbm(),list3.get(i).getMxfyxmbm(),list3.get(i).getCzybm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), fyhj, fyhj,list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);

	}
	 else if (k<1&&m>=1) {
		 ghxh=String.valueOf(id+1)  ;
	      jsjlid=String.valueOf(id+jsxh) ; 
	      
	      updateYwxhb(xh1, ssrq);
	      updatejsjl("000001", ssrq);
	      insertbrgh(ghxh, map.get(list3.get(i).getYyys()), "02", "02", list3.get(i).getBrid(),
	    		  list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(),
	    		  list3.get(i).getYyks(), list3.get(i).getYyys(), "01", "", "", "", "0", "0",  "0022", 
	    		  "0",  list3.get(i).getYyghrq(),  list3.get(i).getBrnl(),  list3.get(i).getBrnldw(),"2", "0");
	      String fyhj=map1.get(list3.get(i).getMxfyxmbm());
	 inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
	insertmzbbrfy(list3.get(i).getYwckbm(),list3.get(i).getMxfyxmbm(),list3.get(i).getCzybm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), fyhj, fyhj,list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);

	}
	 else {
			ghxh=String.valueOf(id+xh)  ;
		    jsjlid=String.valueOf(id+jsxh) ; 
		    
		    updateYwxhb(xh1, ssrq);
		    updatejsjl(jsxh1, ssrq);
		    
		  
		    insertbrgh(ghxh, map.get(list3.get(i).getYyys()), "02", "02", list3.get(i).getBrid(),
		  		  list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getYyghrq(),
		  		  list3.get(i).getYyks(), list3.get(i).getYyys(), "01", "", "", "", "0", "0",  "0022", 
		  		  "0",  list3.get(i).getYyghrq(),  list3.get(i).getBrnl(),  list3.get(i).getBrnldw(),"2", "0");
		    String fyhj=map1.get(list3.get(i).getMxfyxmbm());
		    
		    
		inserjsjl(jsjlid, ghxh, list3.get(i).getCzybm(), list3.get(i).getYwckbm(), list3.get(i).getBrid(), fyhj, "0", fyhj, "0", "0", "0", "0", list3.get(i).getYyghrq(), list3.get(i).getCzyks());
		insertmzbbrfy(list3.get(i).getYwckbm(),list3.get(i).getMxfyxmbm(),list3.get(i).getCzybm(), ssrq ,list3.get(i).getBrid(),ghxh, list3.get(i).getBrxm(), fyhj, fyhj,list3.get(i).getYyghid(), list3.get(i).getYyks(), list3.get(i).getYyks(), jsjlid);

	}
	 


}


}



	







/**
 * 获取挂号序号的ssrq
 * @return
 */
public List<String> getusertime(){
List<String> list=new ArrayList<String>();
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql="SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='ghxh' ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("xhlx"));
	list.add(rs.getString("xh"));
	list.add(rs.getString("ssrq"));
	list.add(rs.getString("cslx"));
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
 * 获取jsjl的ssrq
 * @return
 */

public List<String> getuserjsjltime(){
List<String> list=new ArrayList<String>();
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql="SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='jsjlid' ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
	list.add(rs.getString("xhlx"));
	list.add(rs.getString("xh"));
	list.add(rs.getString("ssrq"));
	list.add(rs.getString("cslx"));
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
 * 获取jsjl的ssrq
 * @return
 */

public String  getxh( int xh1){
String xh = null;
Connection conn = JDBC.getConnection();	
Statement stmt;
String sql=" declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+'"+xh1+"' set @book_code=''+right('000000'+@book_code,6) select @book_code  as xh ";
try {
	stmt = conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	//循环输出每一条记录
	while(rs.next())
	{
		xh=rs.getString("xh");
	
	}
	stmt.close();								// 关闭连接状态对象
	conn.commit();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return xh;
}
/**跟新业务序号中挂号序号**/
public boolean updateYwxhb(String  xh2, String ssrq ){
	boolean ok=false;
	String sql="BEGIN TRAN update ghb_ywxhb Set xh ='" + xh2 + "' , ssrq ='" + ssrq + "' Where xhlx ='ghxh' COMMIT TRAN";
	 ok=dao.insert(sql);
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
public boolean updatejsjl(String xh1, String ssrq ){
	boolean ok=false;
	String sql="BEGIN TRAN update ghb_ywxhb Set xh ='" + xh1 + "' , ssrq ='" + ssrq + "' Where xhlx ='jsjlid' COMMIT TRAN";
	 ok=dao.insert(sql);
	 if(ok=false){
		 System.out.print("更新业务序号表1失败");
	 }
	  return ok;
}



//通过时间获取挂号id
public Long  getyyghid(){ 
String fString="";
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="select CONVERT(varchar(12) , getdate(), 112 ) as yyid";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{
		fString=rs.getString("yyid");
		}
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	fString=fString+"000000";
	
return Long.parseLong(fString) ;
}
/**
 * 获取住院费用明细
 * @param zyh
 * @return
 */
public List<YyxxBean>getbuseryyxx(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="select Rtrim(ghb_yygh.yyghid)yyghid,Rtrim(ghb_yygh.ywckbm)ywckbm,Rtrim(ghb_yygh.brid)brid,Rtrim(ghb_yygh.czybm)czybm,yyghrq,Rtrim(ghb_yygh.czyks)czyks,Rtrim(ghb_yygh.brxm)brxm,Rtrim(yyks)yyks,Rtrim(yyys)yyys ,Rtrim(yyjfbz)yyjfbz,Rtrim(mxfyxmbm)mxfyxmbm ,Rtrim(ghb_zcxx.brnl)brnl ,Rtrim(ghb_zcxx.brnldw)brnldw from ghb_yygh,ghb_zcxx  where ghb_yygh.brid=ghb_zcxx.brid and yydjrq>='2017-01-21 00:00:00.000'";
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
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<YyxxBean> lYyxxBeans=new ArrayList<YyxxBean>();
	for(int i=0;i<list.size();i=i+13){
		YyxxBean yyxxBean=new YyxxBean(list.get(i), list.get(i+1), list.get(i+2), list.get(i+3), list.get(i+4),
				list.get(i+5), list.get(i+6), list.get(i+7), list.get(i+8), list.get(i+9), list.get(i+10), list.get(i+11),list.get(i+12));
		lYyxxBeans.add(yyxxBean);
	
	}
	return lYyxxBeans;
}


/**
 * 获取挂号诊疗项目编码sql
 * @return
 */
public Map<String, String> getghzlbm(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(czybm)czybm,Rtrim(gyb_czy.ghzlbm)ghzlbm from gyb_czy,ghb_ghzl where ghb_ghzl.ghzlbm=gyb_czy.ghzlbm and gyb_czy.tybz=0";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("czybm"));
			list.add(rs.getString("ghzlbm"));

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



public boolean insertbrgh(String ghxh,String ghzlbm,String ghfsbm,String fbbm,String brid,String czybm,String ywckbm,String ghrq,
		String ghks,String jzys,String jzck,String jmyw,String rytszl,String sfysghf,String sfjz,String fzbz,String czyks,
		String jzxe,String jzrq,String brnl,String brnldw,String fzxh,String  mxbghbz ){

	String sql="INSERT INTO ghb_brgh ( ghxh, ghzlbm, ghfsbm, fbbm, brid, czybm, ywckbm, ghrq, ghxq, ghks,jzys, jzck, gmyw, rytsgz, rytszl, sfysghf, sfjz, fzbz, czyks, jzxe, jzrq, brnl, brnldw, fzxh, mxbghbz ) VALUES ( '"+ghxh+"', '"+ghzlbm+"',"
			+ " '02', '02', '"+brid+"', '0269', '01',"
			+ "  '"+ghrq+"', 2, '"+ghks+"', '"+jzys+"', '01', '', '', '', 0, 0, 0, '"+czyks+"', 0.00, '"+jzrq+"', '"+brnl+"', '"+brnldw+"', 2, '0' )";
boolean ok=false;
 ok=dao.insert(sql);
return ok;

}
public boolean inserjsjl(String jsjlid,String ghxh,String czybm,String ywckbm,String brid,String fyhj,
		String ylkzf,String xjzf,
		String ybkzf,String jsqf,String qfbj,String jszt,String jsrq,String czyks){

	String sql="INSERT INTO ghb_jsjl ( jsjlid, ghxh, jscs, czybm, ywckbm, brid, fyhj, ylkzf,"
			+ " xjzf, ybkzf, jsqf, qfbj,jszt, jsrq, czyks, bzms ) VALUES ( '"+jsjlid+"',"
			+ " '"+ghxh+"', 1, '"+czybm+"', '"+ywckbm+"', '"+brid+"','"+fyhj+"', 0,"
+ "'"+xjzf+"', 0, 0, 0, '0', '"+jsrq+"', '"+czyks+"', '挂号结算' ) ";
boolean ok=false;
 ok=dao.insert(sql);
return ok;
}
/**
 * 
插入门诊表--病人费用
 * @return
 */
public boolean insertmzbbrfy(String ywckbm,String mxfyxmbm,String czybm,String sfrq,
		String rybrid,String ryghxh ,String brxm,String fydj,String fyje,String mzys,String mzks,
		String hsks,String ryjsjlid){

	String sql="INSERT INTO mzb_brfy ( fbbm, ywckbm, xlbm, mxfyxmbm, czybm, dlbm, "
			+ "sfrq, rybrid, ryghxh, brxm, fysl, fydj, fyje, yhbl, yhje, mzys, mzks,"
			+ " hsks, yzlx, sfjs, jscs, ryjsjlid, sflx, fzxh ) "
			+ "VALUES ( '02', '"+ywckbm+"', '10', '"+mxfyxmbm+"', '"+czybm+"', '10',"
			+ " '"+sfrq+"', '"+rybrid+"', '"+ryghxh+"',"
			+ " '"+brxm+"', 1, '"+fydj+"', '"+fyje+"', 0.00, 0.00,  '"+mzys+"', '"+mzks+"', "
			+ "'"+hsks+"', '1', 1, 1, '"+ryjsjlid+"', '0', 2 )";
boolean ok=false;
 ok=dao.insert(sql);
return ok;

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


}