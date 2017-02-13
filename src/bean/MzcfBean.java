package bean;

import java.util.List;

/**
 * 门诊处方Bean
 * @author Administrator
 *
 */
public class MzcfBean {
 private String cfh;
 private String ghxh;
 private String cflxbm;
 private String yfbm;
 private String brxm;
 private String fysm;
 private String zz;
 private String zf;
 private String fyts;
 private String cyxm;
 private String ksmc;
 private String cfrq;
 private String cfje;
 private String mzzd;
 private String cflxmc;
 private List<YppfmxBean>yppfmxBeans;



public MzcfBean(String cfh, String ghxh, String cflxbm, String yfbm,
		String brxm, String fysm, String zz, String zf, String fyts,
		String cyxm, String ksmc, String cfrq, String cfje, String mzzd,
		String cflxmc, List<YppfmxBean> yppfmxBeans) {
	super();
	this.cfh = cfh;
	this.ghxh = ghxh;
	this.cflxbm = cflxbm;
	this.yfbm = yfbm;
	this.brxm = brxm;
	this.fysm = fysm;
	this.zz = zz;
	this.zf = zf;
	this.fyts = fyts;
	this.cyxm = cyxm;
	this.ksmc = ksmc;
	this.cfrq = cfrq;
	this.cfje = cfje;
	this.mzzd = mzzd;
	this.cflxmc = cflxmc;
	this.yppfmxBeans = yppfmxBeans;
}
public String getCflxmc() {
	return cflxmc;
}
public void setCflxmc(String cflxmc) {
	this.cflxmc = cflxmc;
}
public List<YppfmxBean> getYppfmxBeans() {
	return yppfmxBeans;
}
public void setYppfmxBeans(List<YppfmxBean> yppfmxBeans) {
	this.yppfmxBeans = yppfmxBeans;
}
public String getCfh() {
	return cfh;
}
public void setCfh(String cfh) {
	this.cfh = cfh;
}
public String getGhxh() {
	return ghxh;
}
public void setGhxh(String ghxh) {
	this.ghxh = ghxh;
}
public String getCflxbm() {
	return cflxbm;
}
public void setCflxbm(String cflxbm) {
	this.cflxbm = cflxbm;
}
public String getYfbm() {
	return yfbm;
}
public void setYfbm(String yfbm) {
	this.yfbm = yfbm;
}
public String getBrxm() {
	return brxm;
}
public void setBrxm(String brxm) {
	this.brxm = brxm;
}
public String getFysm() {
	return fysm;
}
public void setFysm(String fysm) {
	this.fysm = fysm;
}
public String getZz() {
	return zz;
}
public void setZz(String zz) {
	this.zz = zz;
}
public String getZf() {
	return zf;
}
public void setZf(String zf) {
	this.zf = zf;
}
public String getFyts() {
	return fyts;
}
public void setFyts(String fyts) {
	this.fyts = fyts;
}
public String getCyxm() {
	return cyxm;
}
public void setCyxm(String cyxm) {
	this.cyxm = cyxm;
}
public String getKsmc() {
	return ksmc;
}
public void setKsmc(String ksmc) {
	this.ksmc = ksmc;
}
public String getCfrq() {
	return cfrq;
}
public void setCfrq(String cfrq) {
	this.cfrq = cfrq;
}
public String getCfje() {
	return cfje;
}
public void setCfje(String cfje) {
	this.cfje = cfje;
}
public String getMzzd() {
	return mzzd;
}
public void setMzzd(String mzzd) {
	this.mzzd = mzzd;
}

 
 
}
