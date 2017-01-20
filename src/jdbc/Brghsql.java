package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;
/**
 * 当检测到时间到达挂号时间是自动进行病人挂号程序
 * @author Administrator
 *
 */
public class Brghsql {
	Dao dao = Dao.getInstance();
	
YyghSql yyghSql=new YyghSql();
String ssrq = yyghSql.getdatetime();
List<String> list=new ArrayList<String>();
int k=yyghSql.getdatedifference(list.get(2));
String xhmw = "000001";

String ghxh;
/*
 * 
 * 
 */
//获取挂号序号的尾数
int xh = Integer.parseInt(list.get(1).replace("0", "").trim()) + 1;
if(k>0){ ghxh=getyyghid()+1;
updateYwxhb(xh, ssrq);

	
}






//为病人祖册，准备
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


/**跟新业务序号**/
public boolean updateYwxhb(int xh2, String ssrq ){
	boolean ok=false;
	String sql="BEGIN TRAN update ghb_ywxhb Set xh ='" + xh2 + "' , ssrq ='" + ssrq + "' Where xhlx ='ghxh' COMMIT TRAN";
	 ok=dao.insert(sql);
	 if(ok=false){
		 System.out.print("更新业务序号表1失败");
	 }
	  return ok;
}



//通过时间获取挂号id
public String  getyyghid(){
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
return fString;
}

	
}
