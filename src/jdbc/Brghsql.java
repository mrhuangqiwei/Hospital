package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Brghsql {
YyghSql yyghSql=new YyghSql();
String ssrq = yyghSql.getdatetime();
List<String> list=new ArrayList<String>();
int k=yyghSql.getdatedifference(list.get(2));

/*
 * 
 * 
 */
//获取挂号序号的尾数
int xh = Integer.parseInt(list.get(1).replace("0", "").trim()) + 1;






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
	
}
