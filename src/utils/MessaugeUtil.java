package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;











import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import po.News;
import po.NewsMessage;
import po.TextMessage;

public class MessaugeUtil {
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_SCANCODE="scancode_push";
	/**
	 * @param request
	 * @return
	 * @throws Throwable
	 * @throws DocumentException
	 */
public static Map<String, String >xmlToMap(HttpServletRequest request) throws Throwable,DocumentException{
	Map<String, String>map=new HashMap<String, String>();
	SAXReader reader=new SAXReader();
	InputStream ins=request.getInputStream();
	Document doc=reader.read(ins);
	Element root=doc.getRootElement();
	List<Element>list=root.elements();
	for(Element e:list){
		map.put(e.getName(), e.getText());
	}
	ins.close();
	return map;
}
public static String textMessageToXml(TextMessage textMessage){
	XStream xStream=new XStream();
	xStream.alias("xml", textMessage.getClass());
	return xStream.toXML(textMessage);	
}
public static String initText(String ToUserName,String FromUserName,String  Content){
	TextMessage text=new TextMessage();
	text.setFromUserName(ToUserName);
	text.setToUserName(FromUserName);
	text.setMsgType(MessaugeUtil.MESSAGE_TEXT);
	text.setCreateTime(new Date().toString());
	text.setContent(Content);
return textMessageToXml(text);
}


/**
 * 主菜单
 * @return
 */
public static String menuText(){
	StringBuffer sb=new StringBuffer();
	sb.append("欢迎您的关注，请按照菜单提示操作：\n\n");
	sb.append("1,课程介绍\n");
	sb.append("2,慕课网介绍\n");
	sb.append("回复？调出此菜单\n");
	return sb.toString();
}
public static String firstMenu(){
	StringBuffer sb=new StringBuffer();
	sb.append("微信平台接口调试中请等待恢复");
	return sb.toString();
}
public static String secondMenu(){
	StringBuffer sb=new StringBuffer();
	sb.append("普定县任命医院是一所综合性医院");
	return sb.toString();
}
/**
 * 图文消息转xml
 * @param textMessage
 * @return
 */
public static String newsMessageToXml(NewsMessage newsMessage){
	XStream xStream=new XStream();
	xStream.alias("xml",newsMessage.getClass());
	xStream.alias("item",new News().getClass());

	return xStream.toXML(newsMessage);	
}
/**
 * 图文消息的组装
 * @param toUserName
 * @param fromUserName
 * @return
 */

public static String initNewsMessage(String toUserName,String fromUserName){
	String message=null;
	List<News> newList=new ArrayList<News>();
	NewsMessage newsMessage=new NewsMessage();
	News news=new News();
	news.setTitle("普定县人民医院介绍");
	news.setDescription("普定县人民医院成立于1938年,经过近80年的建设和发展,历经北门、街心花园、富强路现址的多次建设和搬迁,现已成为全县一所初具规模、功能齐全、技术");
	news.setPicUrl("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%99%AE%E5%AE%9A%E5%8E%BF%E4%BA%BA%E6%B0%91%E5%8C%BB%E9%99%A2&step_word=&hs=0&pn=0&spn=0&di=186662519230&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=4139923923%2C1850692865&os=592011707%2C2906015831&simid=3420633416%2C469839391&adpicid=0&lpn=0&ln=208&fr=&fmq=1484150390237_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Ff9198618367adab43d87b9e28bd4b31c8701e438.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fptjkw_z%26e3Bkwt17_z%26e3Bv54AzdH3FrAzdH3F8c8amal98n&gsm=0&rpstart=0&rpnum=0");
    news.setUrl("http://www.pdxrmyy.com/");
    newList.add(news);
    newsMessage.setToUserName(fromUserName);
    newsMessage.setFromUserName(toUserName);
    newsMessage.setCreateTime(new Date().getTime());
    newsMessage.setMsgType(MESSAGE_NEWS);
    newsMessage.setArticles(newList);
    newsMessage.setArticleCount(newList.size());
    message=newsMessageToXml(newsMessage);
    return message;
}
/**
 * 
 * @param request
 * @return xml To map地理位置
 * @throws Throwable
 * @throws DocumentException
 */

public static Map<String, String >xmlToMapLocation(HttpServletRequest request) throws Throwable,DocumentException{
	String Label="普定县人民医院";
	String  Location_X="26.312077";
	String  Location_Y="105.743336";
	Map<String, String>map=new HashMap<String, String>();
	SAXReader reader=new SAXReader();
	InputStream ins=request.getInputStream();
	Document doc=reader.read(ins);
	Element root=doc.getRootElement();
	List<Element>list=root.elements();
	
	for(Element e:list){
		if(e.getName().endsWith("Label")){
			map.put(e.getName(), Label);
		}
		else if(e.getName().endsWith("Location_X")){
			map.put(e.getName(), Location_X);
		}
		else if(e.getName().endsWith("Location_Y")){
			map.put(e.getName(), Location_Y);
		}
		else {
			map.put(e.getName(), e.getText());
		}
		
	}
	ins.close();
	return map;
}
}
