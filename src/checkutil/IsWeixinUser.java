package checkutil;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import jdbc.BaseDao;
import jdbc.StudentService;
import jdbc.weinxinsql;
import bean.Brjbxxbean;
import bean.FriendsBean;
import bean.WeiXinUserBean;

/**
 * 检查是否是微信用户如果不是添加之
 * @author Administrator
 *
 */
public class IsWeixinUser {
public static boolean Isweixinuser(WeiXinUserBean bean){
	boolean ok=false;
	List<String> list=new ArrayList<String>();
	String sql="select Rtrim(userid)as userid from gyb_user";
	weinxinsql sWeinxinsql=new weinxinsql();
	list=sWeinxinsql.getjdbclist(sql);
	System.out.print(list.toString());
	for(int i=0;i<list.size();i++){
		if(bean.getOpenid().equals(list.get(i))){
			ok=true;
			System.out.print("---这是返回成功的---'"+ok+"'-----");
			return ok;
		}
		else {ok=false;
		}
	}
	if(ok==false){
		StudentService service = new StudentService();
		return service.insertweixinuser(bean);
	}
	return ok;
	
}
/**
 * 判断该就诊人是否是常用就诊人
 * @param bean
 * @return
 */
public static boolean IsFriend(String openid,String sfzh,String ylkh){
	boolean ok=true;
	weinxinsql weinxinsql=new weinxinsql();
    String json=weinxinsql.getfriendIsRigster(ylkh, sfzh,openid);
    
    if(json.length()>4){
    	String  json1=json.substring(1, json.length() - 1);
    	FriendsBean friendsBean =JSON.parseObject(json1, FriendsBean.class);
    	if(friendsBean.getPh().equals(openid)&&friendsBean.getSfzh().equals(sfzh)&&friendsBean.getYlkh().equals(ylkh)){
    		ok=false;
    	}
    }

	return ok;
	
}
/**
 * 判断该病人医疗卡是否在
 * @param ylkh
 * @return
 */
public static boolean IsFriendRegster(String ylkh){
	boolean ok=false;
	weinxinsql weinxinsql=new weinxinsql();
    String json=weinxinsql.getfriendinfotocheck(ylkh);
    if(json.length()>5){
    	String  json1=json.substring(1, json.length() - 1);
    	Brjbxxbean bean= JSON.parseObject(json1, Brjbxxbean.class);
        if(bean.getYlkh().equals(ylkh));
        {ok=true;}
       
    }
    
	return ok;
	
}


}
