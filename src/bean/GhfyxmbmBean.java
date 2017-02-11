package bean;
/**
 * 挂号表，费用明细项目编码 
 * @author Administrator
 *
 */
public class GhfyxmbmBean {
private String czybm;
private String czyxm;
private String mxfyxmbm;
private String mxfyxmmc;
private String xlbm;
private String dlbm;
private String fydj;
public GhfyxmbmBean(String czybm, String czyxm, String mxfyxmbm,
		String mxfyxmmc, String xlbm, String dlbm, String fydj) {
	super();
	this.czybm = czybm;
	this.czyxm = czyxm;
	this.mxfyxmbm = mxfyxmbm;
	this.mxfyxmmc = mxfyxmmc;
	this.xlbm = xlbm;
	this.dlbm = dlbm;
	this.fydj = fydj;
}
public String getCzybm() {
	return czybm;
}
public void setCzybm(String czybm) {
	this.czybm = czybm;
}
public String getCzyxm() {
	return czyxm;
}
public void setCzyxm(String czyxm) {
	this.czyxm = czyxm;
}
public String getMxfyxmbm() {
	return mxfyxmbm;
}
public void setMxfyxmbm(String mxfyxmbm) {
	this.mxfyxmbm = mxfyxmbm;
}
public String getMxfyxmmc() {
	return mxfyxmmc;
}
public void setMxfyxmmc(String mxfyxmmc) {
	this.mxfyxmmc = mxfyxmmc;
}
public String getXlbm() {
	return xlbm;
}
public void setXlbm(String xlbm) {
	this.xlbm = xlbm;
}
public String getDlbm() {
	return dlbm;
}
public void setDlbm(String dlbm) {
	this.dlbm = dlbm;
}
public String getFydj() {
	return fydj;
}
public void setFydj(String fydj) {
	this.fydj = fydj;
}

}
