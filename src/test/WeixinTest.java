package test;

import po.AccessToken;
import utils.WeiXinUtil;

public class WeixinTest {
public static void main(String[] args){
	AccessToken token=WeiXinUtil.getAccessToken();
	System.out.print("-----票据"+token.getToken());
	System.out.print("-----有效时间"+token.getExpiresIn());
}
}
