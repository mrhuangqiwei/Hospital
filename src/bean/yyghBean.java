package bean;
/**
 * 预约挂号bean
 * @author Administrator
 *
 */
public class yyghBean {
private String ghxh;
private String yyghid;
public yyghBean(String ghxh, String yyghid) {
	super();
	this.ghxh = ghxh;
	this.yyghid = yyghid;
}
public String getGhxh() {
	return ghxh;
}
public void setGhxh(String ghxh) {
	this.ghxh = ghxh;
}
public String getYyghid() {
	return yyghid;
}
public void setYyghid(String yyghid) {
	this.yyghid = yyghid;
}

}
