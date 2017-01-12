package wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.StudentService;
import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

import checkutil.IsWeixinUser;

import com.alibaba.fastjson.JSON;

import bean.WeiXinUserBean;
import po.AccessToken;
import po.TextMessage;
import utils.MessaugeUtil;
import utils.WeiXinUtil;




/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature =request.getParameter("signature");
		String timestamp =request.getParameter("timestamp");
		String nonce =request.getParameter("nonce");
		String echostr =request.getParameter("echostr");
		System.out.print("sig------"+signature+"  ----------time  "+timestamp +"     "+ "----------nonce"+    nonce   + "----------echostr"+echostr);
		String result = "你好，wechat4j";
		PrintWriter out=response.getWriter();
	
		result = "已发送";
		out.print(echostr);
		/**
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
		out.print(echostr);	
		}
		else{System.out.print("校验失败");}
		
		String result = "你好，wechat4j";
		out.println(result);
		System.out.print("doGet方法被调用");**/
		/**
		System.out.print("doGet方法被调用");
		String op = request.getParameter("op");
		if(StringUtils.isNotBlank(op)){
			//主动发送客服消息
			if(op.equals("1")){
				sendMsg();
				result = "已发送";
			}
		}
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		PrintWriter out = response.getWriter();
		out.println(result);**/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.print("doPost方法被调用");
		//doGet(request, response);
		/**
		String result = "你好，wechat4j";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);**/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Map<String,String> map=MessaugeUtil.xmlToMap(request);
			//Map<String,String> map1=MessaugeUtil.xmlToMapLocation(request);
			String FromUserName=map.get("FromUserName");
			String ToUserName=map.get("ToUserName");
			String CreateTime=map.get("CreateTime");
			String MsgType=map.get("MsgType");
			String Content=map.get("Content");
			String MsgId=map.get("MsgId");
			String message=null;
			if(MessaugeUtil.MESSAGE_TEXT.equals(MsgType)){
				if("1".equals(Content)){
					message=MessaugeUtil.initText(ToUserName, FromUserName, MessaugeUtil.firstMenu());
				}
				else if ("2".equals(Content)) {
					message=MessaugeUtil.initNewsMessage(ToUserName, FromUserName);
				}
				else if ("?".equals(Content)||"？".equals(Content)) {
					message=MessaugeUtil.initText(ToUserName, FromUserName, MessaugeUtil.menuText());
				}
				
				/**
				TextMessage text=new TextMessage();
				text.setFromUserName(ToUserName);
				text.setToUserName(FromUserName);
				text.setMsgType("text");
				text.setCreateTime(new Date().toString());
				text.setContent("您发送的消息是："+Content);
				message=MessaugeUtil.textMessageToXml(text);**/
				
			}else if(MessaugeUtil.MESSAGE_EVENT.equals(MsgType)){
				/**点击菜单按钮事件实现推送效果**/
				String eventType=map.get("Event");
				if(MessaugeUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message=MessaugeUtil.initText(ToUserName, FromUserName, MessaugeUtil.menuText());
					AccessToken token=WeiXinUtil.getAccessToken();
					JSONObject jsonObject=WeiXinUtil.getWxuserInfo(token.getToken(), FromUserName);
					String result=jsonObject.toString();
				
				WeiXinUserBean bean= JSON.parseObject(result.toString(), WeiXinUserBean.class);
			boolean k1=	IsWeixinUser.Isweixinuser(bean);
					System.out.print("---"+k1+"----"+result);
				}
				 else if (MessaugeUtil.MESSAGE_CLICK.equals(eventType)) {
					 message=MessaugeUtil.initText(ToUserName, FromUserName, MessaugeUtil.menuText());
				}
				 else if (MessaugeUtil.MESSAGE_VIEW.equals(eventType)) {
					 String url=map.get("Eventkey");
					 message=MessaugeUtil.initText(ToUserName, FromUserName, url);
				}
				
				 else if (MessaugeUtil.MESSAGE_SCANCODE.equals(eventType)) {
					 String key=map.get("Eventkey");
					 message=MessaugeUtil.initText(ToUserName, FromUserName, key);
				}
			}else if(MessaugeUtil.MESSAGE_LOCATION.equals(MsgType)){
		//Map<String,String> map1=MessaugeUtil.xmlToMapLocation(request);
				String label=map.get("Label");
				 message=MessaugeUtil.initText(ToUserName, FromUserName, label);
			}
			
			System.out.print(message);
			
			
			out.print(message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	
	
	/**
	 * 单个发送
	 */
	/**
	private void sendMsg(){
		logger.info("主动发送消息demo");
		//获得关注者列表，发送给第一个人消息
		UserManager userManager = new UserManager();
		List<Object> userList = userManager.subscriberList();
		if(userList.size() > 0){
			String toUserOpenId = userList.get(0).toString();
			String content = "主动发送";
			CustomerMsg customerMsg = new CustomerMsg(toUserOpenId);
			customerMsg.sendText(content);
		}
	}  **/


}
