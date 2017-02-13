package bean;

import java.util.List;



public class MzfyxxBean {
private String brxm;
private String brnl;
private String brnldw;
private String ghrq;
private String fyzj;
private String jtzz;
private List<mzfymxBean> mzfymxBeans;

public MzfyxxBean(String brxm, String brnl, String brnldw, String ghrq,
		String fyzj, String jtzz, List<mzfymxBean> mzfymxBeans) {
	super();
	this.brxm = brxm;
	this.brnl = brnl;
	this.brnldw = brnldw;
	this.ghrq = ghrq;
	this.fyzj = fyzj;
	this.jtzz = jtzz;
	this.mzfymxBeans = mzfymxBeans;
}
public List<mzfymxBean> getMzfymxBeans() {
	return mzfymxBeans;
}
public void setMzfymxBeans(List<mzfymxBean> mzfymxBeans) {
	this.mzfymxBeans = mzfymxBeans;
}
public String getBrxm() {
	return brxm;
}
public void setBrxm(String brxm) {
	this.brxm = brxm;
}
public String getBrnl() {
	return brnl;
}
public void setBrnl(String brnl) {
	this.brnl = brnl;
}
public String getBrnldw() {
	return brnldw;
}
public void setBrnldw(String brnldw) {
	this.brnldw = brnldw;
}
public String getGhrq() {
	return ghrq;
}
public void setGhrq(String ghrq) {
	this.ghrq = ghrq;
}
public String getFyzj() {
	return fyzj;
}
public void setFyzj(String fyzj) {
	this.fyzj = fyzj;
}
public String getJtzz() {
	return jtzz;
}
public void setJtzz(String jtzz) {
	this.jtzz = jtzz;
}

}
