package bean;

import java.util.List;

public class YspbfaBean {
private List<YspbBean>  swpb;
private List<YspbBean> xwpb;


public YspbfaBean(List<YspbBean> swpb, List<YspbBean> xwpb) {
	
	this.swpb = swpb;
	this.xwpb = xwpb;
}
/**
 * @return the swpb
 */
public List<YspbBean> getSwpb() {
	return swpb;
}
/**
 * @param swpb the swpb to set
 */
public void setSwpb(List<YspbBean> swpb) {
	this.swpb = swpb;
}
/**
 * @return the xwpb
 */
public List<YspbBean> getXwpb() {
	return xwpb;
}
/**
 * @param xwpb the xwpb to set
 */
public void setXwpb(List<YspbBean> xwpb) {
	this.xwpb = xwpb;
}

}
