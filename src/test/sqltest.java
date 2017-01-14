package test;

import java.io.IOException;
import java.text.ParseException;

import jdbc.weinxinsql;
import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class sqltest {
	public static void main(String[] args){
		weinxinsql weinxinsql=new weinxinsql();
	    String json=weinxinsql.getfriendIsRigster(null, "513427199309232818","15577616194");
		System.out.print(json);
	}
}
