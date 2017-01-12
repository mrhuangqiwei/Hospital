package test;

import java.io.IOException;
import java.text.ParseException;

import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class QueryMenu {
	public static void main(String[] args) throws IOException{
		AccessToken token=WeiXinUtil.getAccessToken();
		System.out.print("-----票据"+token.getToken());
		System.out.print("-----有效时间"+token.getExpiresIn());
		JSONObject jsonObject=WeiXinUtil.querMenu(token.getToken());
		System.out.print(jsonObject);
	}
}
