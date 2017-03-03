package bean;
/**
 * 科室简介Bean
 * @author Administrator
 *
 */
public class ksmsBean {
private String ksbm;
private String ksmc;
private String ksms;
private String ksjj;


public ksmsBean(String ksbm, String ksmc, String ksms, String ksjj) {
	super();
	this.ksbm = ksbm;
	this.ksmc = ksmc;
	this.ksms = ksms;
	this.ksjj = ksjj;
}
public String getKsbm() {
	return ksbm;
}
public void setKsbm(String ksbm) {
	this.ksbm = ksbm;
}
public String getKsmc() {
	return ksmc;
}
public void setKsmc(String ksmc) {
	this.ksmc = ksmc;
}
public String getKsms() {
	return ksms;
}
public void setKsms(String ksms) {
	this.ksms = ksms;
}
public String getKsjj() {
	return ksjj;
}
public void setKsjj(String ksjj) {
	this.ksjj = ksjj;
}

}
