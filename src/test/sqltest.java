package test;

import java.io.IOException; 
import java.text.ParseException;
import java.util.List;

import jdbc.StudentService;
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
	String  yf=yyghSql.appointment(yyghrq, brxm, brxb, brnldw, sfzh, jtzz, sj, yyys, yyks, yydjrq, yyyxrq, mxfyxmbm, yyjfbz);
			
			
			
		//List<String> kList=yyghSql.getusertime();
		System.out.print("----'"+yf+"'--\t ");
		/**
	    String json=weinxinsql.getfriendIsRigster(null, "513427199309232818","15577616194");
		System.out.print(json);
		
		**/
		
	//	StudentService service=new StudentService();
		//boolean k= weinxinsql.insertfriend("5123271993", "zhangs", "li", "1","2", "owEWzwQKO7G_uy4C0X_Wn2boPVI4", "23", "", "1");
		//boolean k=weinxinsql.updateuser("9999", "owEWzweXEgfBaf9ZW_XN03slsDlI", "45");
		//System.out.print("---------"+k);
	}
}
