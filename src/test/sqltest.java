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

import javax.servlet.ServletContextEvent;

import Servlet.updateyyghservlet;
import Timer.MyTimerListener;
import bean.YyxxBean;
import jdbc.Brghsql;
import jdbc.Brmzfymx;
import jdbc.Deletefriend;
import jdbc.GetFriendInfosql;
import jdbc.GetFriendsYyInfo;
import jdbc.JDBC;
import jdbc.JdbcUtilSql;
import jdbc.StudentService;
import jdbc.Userzyfymx;
import jdbc.Ysbcsql;
import jdbc.YyghSql;
import jdbc.his_yycxsql;
import jdbc.jycx;
import jdbc.mzyjfjlsql;
import jdbc.ris_report_sql;
import jdbc.weinxinsql;
import net.sf.json.JSONObject;
import po.AccessToken;
import utils.ConvertTime;
import utils.GlobalConfigUtil;
import utils.WeiXinUtil;

public class sqltest {
	 static Connection conn = JDBC.getConnection();	
	 static Statement stmt;
	public static void main(String[] args){
		mzyjfjlsql  mzyjfjlsql=new mzyjfjlsql();
		weinxinsql weinxinsql=new weinxinsql();
		Ysbcsql ysbcsql =new Ysbcsql();
		YyghSql yyghSql=new YyghSql();
		Deletefriend deletefriend =new Deletefriend();
		Userzyfymx userzyfymx =new Userzyfymx();
		final Brghsql brghsql=new Brghsql();
		jycx jycx=new jycx();
		ris_report_sql  ris=new ris_report_sql();
		GetFriendInfosql getFriendInfosql=new GetFriendInfosql();
		GetFriendsYyInfo getFriendsYyInfo=new GetFriendsYyInfo();
		Brmzfymx brmzfymx=new Brmzfymx();
	//String json=yyghSql.getdatetime();
	
	String mxfyxmbnString="130002";
	String yyghid="20161018000002";
	String czybm="0269";
	String ywckbm="888";
	String yydjrq="2016-10-21 12:19:19.327";
	String yyghrq="2017-02-20 12:19:19.327";
	String yyyxrq="2017-11-22 12:19:19.327";
	String brxm="黄奇伟";
	String   brxb="1";
	String brsr="1900-01-01 00:00:00.000";
	String brnl="23";
	String brnldw="1";
	String sfzh="513427196907132813";   
	String sj="15577616194";
	String yyys="00004";
	String yyks="0004";
	String jtzz="宁南县";
	String mxfyxmbm="130002";
	String yyjfbz="1";
	

//自动挂号	/
//brghsql.zdgh();
//String  yf=yyghSql.appointment(yyghrq, brxm, brxb, brnldw, sfzh, jtzz, sj, yyys, yyks, yydjrq, yyyxrq, mxfyxmbm, yyjfbz);
		

//List<YyxxBean> list=brghsql.getbuseryyxx();
	//boolean ok=false;
	//ok=deletefriend.deletefriendinfo("owEWzwQKO7G_uy4C0X_Wn2boPVI4", "558842685569842688", "");
	//Map<String, String> map=userzyfymx.getmxfcbm();
      //  List<String> list= userzyfymx.getmzfymx("20100407000001"); 
		//brghsql.zdgh();;
 //String ssString=ysbcsql.yspb("0004");
	
	//System.out.print(his_yycxsql.getyymx("", "513427196907132813"));
 /**
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
      long lTime = dt2.getTime();**/
	//System.out.print(Integer.parseInt("000005"));
		//List<String> kList=jycx.getuserjymx("161017000194");
	//检验结果
	//String jString=jycx.getjyjg("2016003850");
	//检查结果
	//String jString=ris.GetRisReport("2016003857");
	//病人费用
	//String jString=userzyfymx.getuserfymx("2016003850");
	//获取病人信息
//String jString=getFriendInfosql.userinfotojison("513427199708082028", "00007250 ");
	//获取病人预约信息
	//String jString=getFriendsYyInfo.getfriendsYyinfo("owEWzwQKO7G_uy4C0X_Wn2boPVI4");
	//获取病人处方信息

	//String jString=JdbcUtilSql.getmzcf("20161127000001");
	//String jString=JdbcUtilSql.getmzcf("20161127000001");
	//获取病人门诊费用信息
	//String jString=brmzfymx.Getmzfyxx("20110830000");
	//boolean ok=ConvertTime.datesdiferrent("2017-02-13 08:28:13.013");
	//String jString = null;
//时间格式转化
	//	jString = ConvertTime.dateToStamp("2016-06-30 16:12:29.330");

	//	System.out.print(ConvertTime.converttimetoYYMMDDHH00("2016-07-07 21:28:08.133"));
		
		//List<String> kList=ris.getrismx("987");
	//测试全局参数


	//  String  jString = GlobalConfigUtil.getGhfsbm();
		//System.out.print(jString);
		/**
	    String json=weinxinsql.getfriendIsRigster(null, "513427199309232818","15577616194");
		System.out.print(json);
		
		/**/
//	System.out.print(mzyjfjlsql.getmzyjfy("20170216004865"));
     /**
      * 
      for(int k=0;k<100;k++){
		brghsql.getbuseryyxx();}
      
      new Thread() {
          @Override
          public void run() {
              try {
            	  for(int k=0;k<100;k++){
            			brghsql.getbuseryyxx();}

              } catch (Exception e) {
              }
          }
      }.start();
      **/
      
	
	//System.out.print(updateyyghservlet.updateghbbrgh("20170216004864"));
	
	
		//updateyyghservlet.updateghbbrgh("20170214000050");
	
	//System.out.print(getrq());
	//ky();
//		System.out.print(GlobalConfigUtil.getFbbm());
	//	StudentService service=new StudentService();
		//boolean k= weinxinsql.insertfriend("5123271993", "zhangs", "li", "1","2", "owEWzwQKO7G_uy4C0X_Wn2boPVI4", "23", "", "1");
		//boolean k=weinxinsql.updateuser("9999", "owEWzweXEgfBaf9ZW_XN03slsDlI", "45");
		//System.out.print("---------"+k);
	}
	public static  List<String> getrq(){
		List<String> list=new ArrayList<String>();
		
		String sql="select * from gyb_czy ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("czybm"));

			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void ky(){
		List<String> list=new ArrayList<String>();
		String sql="select czyxm from gyb_czy  ";
	
		try {
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{list.add(rs.getString("czyxm"));

			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("\t"+"这是："+list.toString());
	}
	
}
