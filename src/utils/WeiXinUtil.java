package utils;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

import po.AccessToken;
import menu.Button;
import menu.ClickButton;
import menu.Menu;
import menu.ViewButton;
import net.sf.json.JSONObject;

public class WeiXinUtil {
 private static final String APPID="wx45f898f7029b393c";
 private static final String APPSECRET="94dc653d186326bc63b5dfa359c68d08";
 private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
 private static final String Create_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
 private static final String QUER_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
 private static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
 private static final String GET_WXUSER_INFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
 /**
  * 获取accesstokendoget方法封装
  * get请求
  * @param url
  * @return
  */
 public static JSONObject doGetStr(String url ){
	 DefaultHttpClient httpClient =new DefaultHttpClient();
	 HttpGet httpGet=new HttpGet(url);
	 JSONObject jsonObject=null;
	 try {
		HttpResponse response=httpClient.execute(httpGet);
		HttpEntity entity=response.getEntity();
		if(entity!=null){
			String result=EntityUtils.toString(entity,"UTF-8");
			jsonObject=JSONObject.fromObject(result);
		}
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return jsonObject;
 }
 /**
  * post请求
  * @param url
  * @param outStr
  * @return
  */
 public static JSONObject doPostStr(String url,String outStr){
	 DefaultHttpClient httpClient=new DefaultHttpClient();
	 HttpPost httpPost=new HttpPost(url);
	 JSONObject jsonObject=null;
	 try {
		httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 try {
		HttpResponse response=httpClient.execute(httpPost);
		String result=EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject=JSONObject.fromObject(result);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return jsonObject;
 }
 /**
  * 获取accessToken
  * @return
  */
 public static AccessToken getAccessToken(){
	 AccessToken token=new AccessToken();
	 String url=ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
	 System.out.print(url);
	 JSONObject jsonObject=doGetStr(url);
	 if(jsonObject!=null){
		 token.setToken(jsonObject.getString("access_token"));
		 token.setExpiresIn(jsonObject.getInt("expires_in"));
	 }
	 return token;
 }
 /**
  * 组装菜单
  * @return
  */
 public static Menu initMenu(){
	 Menu menu=new Menu();
	 ClickButton button11=new ClickButton();
	 button11.setName("Click菜单");
	 button11.setType("click");
	 button11.setKey("11");
	 ViewButton button21=new ViewButton();
	 button21.setName("View菜单");
	 button21.setType("view");
	 button21.setUrl("http://mrhuangqiwei.6655.la/Hospital");
	 ClickButton button31=new ClickButton();
	 button31.setName("扫码事件");
	 button31.setType("scancode_push");
	 button31.setKey("31");
	 ClickButton button32=new ClickButton();
	 button32.setName("地理位置");
	 button32.setType("location_select");
	 button32.setKey("32");
	 Button button=new Button();
	 button.setName("菜单");
	 button.setSub_button(new Button[]{button31,button32});
	 menu.setButton(new Button[]{button11,button21,button});
	 return menu;
 }
 /**
 * 组装菜单
 * @return
 */
public static Menu initMenu2(){
	 Menu menu=new Menu();
	 ClickButton button11=new ClickButton();
	 button11.setName("Click菜单");
	 button11.setType("click");
	 button11.setKey("11");
	 ClickButton button12=new ClickButton();
	 button11.setName("Click菜单");
	 button11.setType("click");
	 button11.setKey("12");
	 ViewButton button21=new ViewButton();
	 button21.setName("View菜单");
	 button21.setType("view");
	 button21.setUrl("http://mrhuangqiwei.6655.la/Hospital");
	 ClickButton button31=new ClickButton();
	 button31.setName("扫码事件");
	 button31.setType("scancode_push");
	 button31.setKey("31");
	 ClickButton button32=new ClickButton();
	 button32.setName("地理位置");
	 button32.setType("location_select");
	 button32.setKey("32");
	 Button button=new Button();
	 button.setName("菜单");
	 button.setSub_button(new Button[]{button31,button32});
	 menu.setButton(new Button[]{button11,button21,button});
	 return menu;
}

 
 /**
  * 组装菜单
  * @return
  */
 public static Menu initMenu1(){
	 Menu menu=new Menu();
	 ViewButton button11=new ViewButton();
	 button11.setName("首页");
	 button11.setType("view");
	 button11.setUrl("http://mrhuangqiwei.6655.la/Hospital/UserInfoServlet");
	 ClickButton button21=new  ClickButton();
	 button21.setName("联系医院");
	 button21.setType("click");
	 button21.setKey("121");
	 ClickButton button31=new ClickButton();
	 button31.setName("扫码");
	 button31.setType("scancode_push");
	 button31.setKey("131");
	 ClickButton button32=new ClickButton();
	 button32.setName("定位");
	 button32.setType("location_select");
	 button32.setKey("132");
	 ViewButton button41=new ViewButton();
	 button41.setName("医院官网");
	 button41.setType("view");
	 button41.setUrl("http://www.pdxrmyy.com");
	 
	 Button button=new Button();
	 button.setName("关于医院");
	 button.setSub_button(new Button[]{button21,button31,button32});
	 menu.setButton(new Button[]{button11,button,button41});
	 return menu;
 }
 /**
  * 创建菜单
  * @param token
  * @param menu
  * @return
  * @throws ParseException
  * @throws IOException
  */
 public static int createMenu(String token,String menu)throws ParseException,IOException{
	 int result=0;
	 String url=Create_MENU_URL.replace("ACCESS_TOKEN", token);
	 JSONObject jsonObject=doPostStr(url, menu);
	 if(jsonObject!=null){
		 result=jsonObject.getInt("errcode");
		 
	 }
	 return result;
 }
 
 /**
  * 查询菜单
 **/
 
 public static  JSONObject querMenu(String token) {
	 String url=QUER_MENU_URL.replace("ACCESS_TOKEN", token);
	 JSONObject jsonObject=doGetStr(url);
	 return jsonObject;
}
 /**
  * 删除菜单
  * @param token
  * @return
  */
 public static int deleteMenu(String token) {
	 String url=DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
	 JSONObject jsonObject=doGetStr(url);
	 int result=0;
	 if(jsonObject!=null){
		 result=jsonObject.getInt("errcode");
	 }
	 return result;
}
 public static JSONObject  getWxuserInfo(String token,String FromUserName) {
	 String url=GET_WXUSER_INFO.replace("ACCESS_TOKEN", token).replace("OPENID", FromUserName);
			 
	 JSONObject jsonObject=doGetStr(url);
	 return jsonObject;
}
 
 
}
