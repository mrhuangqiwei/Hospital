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
	Dao dao = Dao.getInstance();
	 public String appointment(String yyghrq,String brxm,String brxb,String brnl,String sfzh,String jtzz,String sj,String yyys,String yyks,String yydjrq,String yyyxrq,String mxfyxmbm,
			 String yyjfbz)
     { String yy ="";
     List<String> list = new ArrayList<String>();
     List<String> list2 = new ArrayList<String>();
     list = getywxhidyy();
     list2 = getusertime();
   Long  yyid1 = Long.parseLong(getyyghid().trim());
    		// Double.parseDouble(getyyghid().trim());
     System.out.print("--------yyid1-----'"+yyid1+"'--");
    		 //Integer.parseInt(getyyghid().trim());
     int m = Integer.parseInt(list.get(1).replace("0", "").trim())+1;	 
     String xh1 = "";
     xh1 = getywxuxhyyid(m);
     String s = list2.get(1);
     int k2 = Integer.parseInt(s.replace("0", "").trim())+1;
     String bridxh = getywxuxhyyid(k2);
     String hu = list.get(2);
     int k=getdatedifference(list.get(2));
     String xh = "000001";
//     DateTime dt = DateTime.Now; 
   //  dt.ToString();//2005-11-5 13:21:25 
    // String ssrq = dt.ToString(); 
   String ssrq = getdatetime();
     int k1 = Integer.parseInt(list2.get(1).replace("0", "").trim()) + 1;

    String zcrq = getdatetime();
    if (k >= 1)
    {
        updateYwxhb1(xh, ssrq);
   Long   f = Long.parseLong(getyyghid().trim())+1;
		   //Double.parseDouble(getyyghid().trim());
    		//Integer.parseInt(getyyghid().trim())+1;
        String yyid = String.valueOf(f);;
        Insertyygh(yyid, "01", "0269", yyghrq, brxm, brxb, "", brnl,
            "1", sfzh, jtzz, sj, yyys, yyks, yydjrq, yyyxrq, "0022",mxfyxmbm,yyjfbz);
        int j = getdatedifference(list2.get(2));
        if (j > 1)
        {
            updateYwxhb2("000001", ssrq);
       Long  q = yyid1 + 1;
            String brid = String.valueOf(q);   
              
            Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq);
            updateyyghbrid(brid, yyid);
                }
        else {
            updateYwxhb2(bridxh, ssrq);
        Long f1 = yyid1 + k1;
            String brid = String.valueOf(f1);
            Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq);
            updateyyghbrid(brid, yyid);
            }
        }
    else{
    yy = xh1;
    updateYwxhb1(xh1, ssrq);
Long  f1 = yyid1 + m ;
    String yyid = String.valueOf(f1);
    
    Insertyygh(yyid, "01", "0269", yyghrq, brxm, brxb, "", brnl,
            "1", sfzh, jtzz, sj, yyys, yyks, yydjrq, yyyxrq, "0022",mxfyxmbm,yyjfbz);
   
   int ww = getdatedifference(list2.get(2));
	 
   if (ww > 1)
   {
       updateYwxhb2("000001", ssrq);
  Long q = yyid1 + 1;
       String brid = String.valueOf(q);
       Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq);
       updateyyghbrid(brid, yyid);

   }
   {
       updateYwxhb2(bridxh, ssrq);
     Long f2 = yyid1 + k2;
       String brid = String.valueOf(f2);    
       Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq);
       updateyyghbrid(brid, yyid);
   }}
   return "yy";
    }
	
	
	
	
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
		fString=fString+"000000";
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
    
    /**插入预约挂号表**/
    public boolean Insertyygh( String yyghid,String ywckbm,String czybm,String yyghrq,String brxm,String brxb,String brsr,String brnl,String brnldw,String sfzh,String jtzz,String sj,String yyys, String yyks, String yydjrq, String yyyxrq, String czyks,String mxfyxmbm,String yyjfbz)
    {boolean ok=false;
    	
    	String sql = "INSERT INTO ghb_yygh ( yyghid, ywckbm, czybm, yyghrq, brxm, brxb, brsr, brnl, brnldw, sfzh, jtzz, sj,yyys, yyks, yydjrq, yyyxrq, czyks,mxfyxmbm,yyjfbz ) VALUES ( '" + yyghid + "', '" + ywckbm + "', '" + czybm + "', '" + yyghrq + "', '" + brxm + "', '" + brxb + "', '" + brsr + "'," + brnl + ", '" + brnldw + "', '" + sfzh + "', '" + jtzz + "', '" + sj + "', '" + yyys + "', '" + yyks + "', '" + yydjrq + "', '" + yyyxrq + "', '" + czyks + "', '"+mxfyxmbm+"','"+yyjfbz+"')" ;
    ok=dao.insert(sql);
  return ok;
    }
    
    
    /**跟新业务序号1**/
    public boolean updateYwxhb1(String xh, String ssrq ){
    	boolean ok=false;
    	String sql="BEGIN TRAN update ghb_ywxhb Set xh ='" + xh + "' , ssrq ='" + ssrq + "' Where xhlx ='yyghid' COMMIT TRAN";
    	 ok=dao.insert(sql);
    	 if(ok=false){
    		 System.out.print("更新业务序号表1失败");
    	 }
    	  return ok;
    }
    
    /**跟新业务序号2**/
    public boolean updateYwxhb2(String xh, String ssrq ){
    	boolean ok=false;
    	String sql="update ghb_ywxhb Set xh ='" + xh + "' , ssrq ='" + ssrq + "' Where xhlx ='brid'";
    	 ok=dao.insert(sql);
    	 if(ok=false){
    		 System.out.print("更新业务序号表2失败");
    	 }
    	  return ok;
    }
    
    
    
    /**插入病人注册表**/
    public boolean Insertbrzc(String brid,String brxm, String brxb,  String brnl, String sfzh, String jtzz, String sj, String zcrq)
    {boolean ok=false;
    	
    	String sql = "  INSERT into ghb_zcxx ( brid , czybm , ywckbm , brxm , pydm , brxb , brsr , brnl , brnldw , brxx , sfzh , jtzz , gzdw , dwdz , sj , email , yzbm , lxr , lxrdh , zcrq , czyks )VALUES ( '"+brid+"' , '0000' , '01' , '"+brxm+"' , 'ZRT' , '"+brxb+"' , null , '"+brnl+"' , '1' , null , '"+sfzh+"' ,'"+jtzz+"' , null , null , '"+sj+"' , null , null , null , null , '"+zcrq+"' , '0001' ) " ;
    ok=dao.insert(sql);
    if(ok=false){
    System.out.print("插入注册表失败");	
    
    }
  return ok;
    }   
	
    
    /**更新挂号表信息**/
    public boolean updateyyghbrid(String brid, String yyghid)
    {boolean ok=false;
    	
    	String sql = " update ghb_yygh set brid ='"+brid+"' where yyghid ='"+yyghid+"' " ;
    ok=dao.insert(sql);
    if(ok=false){
    System.out.print("更新挂号表信息失败");	
    
    }
  return ok;
    }
    
    
}
