package bean;

/**
 * 费用明细**/
public class FymxBean {
String zyh,xlbm,dlbm,mxfyxmbm,ypmc,fysl,fydj,fyje,yhje,tclb,nbtclb,fygg,jldwmc,sgbm;

    public String getYpmc() {
        return ypmc;
    }

    public String getFyje() {
        return fyje;
    }

    public String getSgbm() {
        return sgbm;
    }

    public void setSgbm(String sgbm) {
        this.sgbm = sgbm;
    }

    public String getFygg() {
        return fygg;
    }

    public String getJldwmc() {
        return jldwmc;
    }

    public void setJldwmc(String jldwmc) {
        this.jldwmc = jldwmc;
    }

    public void setFygg(String fygg) {

        this.fygg = fygg;
    }

    public String getNbtclb() {
        return nbtclb;
    }

    public void setNbtclb(String nbtclb) {
        this.nbtclb = nbtclb;
    }

    public String getTclb() {
        return tclb;
    }

    public void setTclb(String tclb) {
        this.tclb = tclb;
    }

    public String getYhje() {
        return yhje;
    }

    public void setYhje(String yhje) {
        this.yhje = yhje;
    }

    public void setFyje(String fyje) {
        this.fyje = fyje;
    }

    public String getFydj() {
        return fydj;

    }

    public void setFydj(String fydj) {
        this.fydj = fydj;
    }

    public String getFysl() {
        return fysl;
    }

    public void setFysl(String fysl) {
        this.fysl = fysl;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public String getZyh() {
        return zyh;

    }

    public String getMxfyxmbm() {
        return mxfyxmbm;
    }

    public void setMxfyxmbm(String mxfyxmbm) {
        this.mxfyxmbm = mxfyxmbm;
    }

    public String getDlbm() {
        return dlbm;
    }

    public void setDlbm(String dlbm) {
        this.dlbm = dlbm;
    }

    public String getXlbm() {
        return xlbm;
    }

    public void setXlbm(String xlbm) {
        this.xlbm = xlbm;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public FymxBean(){}
    public FymxBean(String zyh, String xlbm,String dlbm,String mxfyxmbm,String ypmc,String fysl,String fydj,
    		String fyje,String yhje,String tclb,String nbtclb,String fygg,String jldwmc,String sgbm){
this.zyh=zyh;this.xlbm=xlbm;this.dlbm=dlbm;this.mxfyxmbm=mxfyxmbm;this.ypmc=ypmc;this.fysl=fysl;
        this.fydj=fydj;this.fyje=fyje;this.yhje=yhje;this.tclb=tclb;this.nbtclb=nbtclb;this.fygg=fygg;
        this.jldwmc=jldwmc;this.sgbm=sgbm;
    }
}
