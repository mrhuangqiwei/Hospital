package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sword.lang.DateTime;

public class YyghSql {
/**
	 public String appointment(String yyghrq,String brxm,String brxb,String brnl,String sfzh,String jtzz,String sj,String yyys,String yyks,String yydjrq,String yyyxrq)
     { String yy ="";
     List<String> list = new ArrayList<String>();
     List<String> list2 = new ArrayList<String>();
     list = getywxhidyy();
     list2 = getusertime();
     double yyid1 =Double.parseDouble(getyyghid());
     int m = Integer.parseInt(list.get(1))+1;	 
     String xh1 = "";
     xh1 = getywxuxhyyid(m);
     String s = list2.get(1);
     int k2 = Integer.parseInt(s)+1;
     String bridxh = getywxuxhyyid(k2);
     String hu = list.get(2);
     int k=getdatedifference(list.get(2));
     String xh = "000001";
//     DateTime dt = DateTime.Now; 
   //  dt.ToString();//2005-11-5 13:21:25 
    // String ssrq = dt.ToString(); 
   String ssrq = getdatetime();
     int k1 = int.Parse(list2[1]) + 1;

    String zcrq = getdatetime();
        
	
	)  **/
	
	
	
	
	
	
	//为获取预约挂号id做准备
	public List<String> getywxhidyy(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='yyghid'";
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
	

	//为病人祖册，准备
	public List<String> getusertime(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='brid' ";
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
		
	//获取时间
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
	return fString;
	}
	
	/**获取和生成业务序号**/
    public String getywxuxhyyid(int  xh1){
    	
    	String fString="";
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql=" declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+"+xh1+" set @book_code=''+right('000000'+@book_code,6) select @book_code as id";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			fString=rs.getString("id");
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return fString;
    
    }
	
    /**获取当前时间和系统时间的差**/
    public int  getdatedifference(String date){
    	
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  Date date1 = null;
    	  try {
             date1=sdf.parse(date);
              
          } catch (ParseException ex) {
              System.out.print("YyghSql"+"时间转换有错误");
          }
    	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String tablename=dateFormat.format(date1);
    	
    	int k=0;
        String s="";
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql=" SELECT DATEDIFF(day, '"+tablename+"', getdate()) as df ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			s=rs.getString("df");
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return Integer.parseInt(s);
    
    }
	
    /**获取当前日期**/
    public String getdatetime(){
    	
    	String fString="";
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql=" select (SELECT DISTINCT GetDate ( ) FROM sysfilegroups )  as time ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			fString=rs.getString("time");
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	  Date date1 = null;
  	  try {
           date1=sdf.parse(fString);
            
        } catch (ParseException ex) {
            System.out.print("YyghSql"+"时间转换有错误");
        }
  	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tablename=dateFormat.format(date1);
		
	return tablename;
    
    }
	
		
}
