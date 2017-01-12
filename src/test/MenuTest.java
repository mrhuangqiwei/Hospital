package test;

import java.io.IOException;
import java.text.ParseException;

import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class MenuTest {
	public static void main(String[] args){
		try {
		AccessToken token=WeiXinUtil.getAccessToken();
		System.out.print("-----票据"+token.getToken());
		System.out.print("-----有效时间"+token.getExpiresIn());
		String menu=JSONObject.fromObject(WeiXinUtil.initMenu1()).toString();
	
			int result=WeiXinUtil.createMenu(token.getToken(), menu);
			if(result==0){
				System.out.print("菜单创建成功");
			}
			else{
				System.out.print("错误码："+result);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
