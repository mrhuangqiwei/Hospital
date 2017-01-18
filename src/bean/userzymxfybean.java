package bean;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class userzymxfybean {
	String userinfo;
	List<FymxBean> userfymx;
	
	
	public userzymxfybean(String userinfo,List<FymxBean>  userfymx) {
		this.userinfo = userinfo;
		this.userfymx = userfymx;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public List<FymxBean> getUserfymx() {
		return userfymx;
	}
	public void setUserfymx(List<FymxBean> userfymx) {
		this.userfymx = userfymx;
	}

}
