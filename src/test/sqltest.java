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
		String Openid="owEWzwQKO7G_uy4C0X_Wn2boPVI4";
		String json=weinxinsql.userinfotojison(Openid);
		System.out.print(json);
	}
}
