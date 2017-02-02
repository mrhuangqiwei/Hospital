package bean;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class userzymxfybean {
     private String zyh;
     private String ryrq;
     private String cyrq;
     private String rycwid;
     private String cyks;
     private String ryks;
     private String ksmc;
     private String brxm;
     private String brnl;
     private String brnldw;
     private String brxb;
     private String jtzz;
     private String fbmc; 
     private String fyje;
     private String yjje; 
	List<FymxBean> userfymx;
	
	

	public userzymxfybean(String zyh, String ryrq, String cyrq, String rycwid,
			String cyks, String ryks, String ksmc, String brxm, String brnl,
			String brnldw, String brxb, String jtzz, String fbmc, String fyje,
			String yjje, List<FymxBean> userfymx) {
		super();
		this.zyh = zyh;
		this.ryrq = ryrq;
		this.cyrq = cyrq;
		this.rycwid = rycwid;
		this.cyks = cyks;
		this.ryks = ryks;
		this.ksmc = ksmc;
		this.brxm = brxm;
		this.brnl = brnl;
		this.brnldw = brnldw;
		this.brxb = brxb;
		this.jtzz = jtzz;
		this.fbmc = fbmc;
		this.fyje = fyje;
		this.yjje = yjje;
		this.userfymx = userfymx;
	}
	public String getZyh() {
		return zyh;
	}
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}
	public String getRyrq() {
		return ryrq;
	}
	public void setRyrq(String ryrq) {
		this.ryrq = ryrq;
	}
	public String getCyrq() {
		return cyrq;
	}
	public void setCyrq(String cyrq) {
		this.cyrq = cyrq;
	}
	public String getRycwid() {
		return rycwid;
	}
	public void setRycwid(String rycwid) {
		this.rycwid = rycwid;
	}
	public String getCyks() {
		return cyks;
	}
	public void setCyks(String cyks) {
		this.cyks = cyks;
	}
	public String getRyks() {
		return ryks;
	}
	public void setRyks(String ryks) {
		this.ryks = ryks;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
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
	public String getBrxb() {
		return brxb;
	}
	public void setBrxb(String brxb) {
		this.brxb = brxb;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getFbmc() {
		return fbmc;
	}
	public void setFbmc(String fbmc) {
		this.fbmc = fbmc;
	}
	public String getFyje() {
		return fyje;
	}
	public void setFyje(String fyje) {
		this.fyje = fyje;
	}
	public String getYjje() {
		return yjje;
	}
	public void setYjje(String yjje) {
		this.yjje = yjje;
	}
	public List<FymxBean> getUserfymx() {
		return userfymx;
	}
	public void setUserfymx(List<FymxBean> userfymx) {
		this.userfymx = userfymx;
	}

}
