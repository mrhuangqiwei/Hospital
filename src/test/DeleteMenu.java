package test;

import java.io.IOException;

import net.sf.json.JSONObject;
import po.AccessToken;
import utils.WeiXinUtil;

public class DeleteMenu {
	public static void main(String[] args) throws IOException{
		AccessToken token=WeiXinUtil.getAccessToken();
		System.out.print("-----票据"+token.getToken());
		System.out.print("-----有效时间"+token.getExpiresIn());
		int result=WeiXinUtil.deleteMenu(token.getToken());
		if(result==0){
			System.out.print("删除成功");
			
		}
		else {
			System.out.print(result);
		}
	}
}
