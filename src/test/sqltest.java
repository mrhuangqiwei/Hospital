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
	String json=yyghSql.getdatetime();
		//List<String> kList=yyghSql.getusertime();
		System.out.print("----'"+json+"'--\t ");
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
