package test;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.Deletefriend;
import jdbc.JDBC;
import jdbc.StudentService;
import jdbc.Userzyfymx;
import jdbc.Ysbcsql;
import jdbc.YyghSql;
import jdbc.weinxinsql;
import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class sqltest {
	public static void main(String[] args){
		
		weinxinsql weinxinsql=new weinxinsql();
		Ysbcsql ysbcsql =new Ysbcsql();
		YyghSql yyghSql=new YyghSql();
		Deletefriend deletefriend =new Deletefriend();
		Userzyfymx userzyfymx =new Userzyfymx();
		
	//String json=yyghSql.getdatetime();
	
	String mxfyxmbnString="130002";
	String yyghid="20161018000002";
	String czybm="0269";
	String ywckbm="01";
	String yydjrq="2016-10-21 12:19:19.327";
	String yyghrq="2016-10-21 12:19:19.327";
	String yyyxrq="2016-11-21 12:19:19.327";
	String brxm="张三";
	String   brxb="1";
	String brsr="1900-01-01 00:00:00.000";
	String brnl="23";
	String brnldw="1";
	String sfzh="513427196907132813";   
	String sj="15577616194";
	String yyys="0004";
	String yyks="0004";
	String jtzz="宁南县";
	String mxfyxmbm="130002";
	String yyjfbz="1";
	//String  yf=yyghSql.appointment(yyghrq, brxm, brxb, brnldw, sfzh, jtzz, sj, yyys, yyks, yydjrq, yyyxrq, mxfyxmbm, yyjfbz);
		
	//boolean ok=false;
	//ok=deletefriend.deletefriendinfo("owEWzwQKO7G_uy4C0X_Wn2boPVI4", "558842685569842688", "");
	//Map<String, String> map=userzyfymx.getmxfcbm();
      //  List<String> list= userzyfymx.getmzfymx("20100407000001");
        List<String> list=getrq();
     
        SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
      
        
        java.util.Date dt2 = null;
		try {
			dt2 = sdf.parse(list.get(0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //继续转换得到秒数的long型
      long lTime = dt2.getTime();
		//List<String> kList=yyghSql.getusertime();
		System.out.print("----'"+list.get(0)+"'--\t "+lTime);
		/**
	    String json=weinxinsql.getfriendIsRigster(null, "513427199309232818","15577616194");
		System.out.print(json);
		
		**/
		
	//	StudentService service=new StudentService();
		//boolean k= weinxinsql.insertfriend("5123271993", "zhangs", "li", "1","2", "owEWzwQKO7G_uy4C0X_Wn2boPVI4", "23", "", "1");
		//boolean k=weinxinsql.updateuser("9999", "owEWzweXEgfBaf9ZW_XN03slsDlI", "45");
		//System.out.print("---------"+k);
	}
	public static  List<String> getrq(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select yyghrq from ghb_yygh where yyghid='20160701000001            ' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("yyghrq"));
		
			
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
