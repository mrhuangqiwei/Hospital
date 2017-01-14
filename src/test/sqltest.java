package test;

import java.io.IOException;
import java.text.ParseException;

import jdbc.StudentService;
import jdbc.weinxinsql;
import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class sqltest {
	public static void main(String[] args){
		/**
		weinxinsql weinxinsql=new weinxinsql();
	    String json=weinxinsql.getfriendIsRigster(null, "513427199309232818","15577616194");
		System.out.print(json);
		
		**/
		
		StudentService service=new StudentService();
		boolean k=service.insertfriend("5123271993", "zhangs", "li", "1","2", "owEWzweXEgfBaf9ZW_XN03slsDlI", "23", "", "1");
		System.out.print("---------"+k);
	}
}
