package bean;

public class RisBean {
	private String NAME;
	private String SEX;
	private String PINYIN;
	
	private String CLINICNO;
	private String INPATIENTNO;
	private String PATIENTID;
	private String STUDYID;
	private String AGE;
	private String AGEUNIT;
	private String LODGESECTION;
	private String LODGEDOCTOR;
	private String LODGEDATE;
	private String BEDNO;
	private String applyNO;
	private String applySerialNumber;
	private String applyitem;
	private String applyitemAll;
	private String applyID;
	private String CLIISINPAT;
	private String ENROLDOCTOR;
	private String ENROLDATE;
	private String SURGERYRESULT;
	private String CHECKPURPOSE;
	private String STATUS;	private String CLASSNAME;
	private String PHOTONO;
	
	private String TOTALFEE;	private String INHOSPITALNO;
	private String MODALITYNAME;
	private String CHECKDATE;
	private String CHECKDOCTOR;
	private String PARTOFCHECK;
	private String reportDate;
	private String reportDoctor;
	private String accession_num;
	private String laybe1;
	private String laybe2;
	
	
	public RisBean(String nAME, String sEX, String pINYIN, String cLINICNO,
			String iNPATIENTNO, String pATIENTID, String sTUDYID, String aGE,
			String aGEUNIT, String lODGESECTION, String lODGEDOCTOR,
			String lODGEDATE, String bEDNO, String applyNO,
			String applySerialNumber, String applyitem, String applyitemAll,
			String applyID, String cLIISINPAT, String eNROLDOCTOR,
			String eNROLDATE, String sURGERYRESULT, String cHECKPURPOSE,
			String sTATUS, String cLASSNAME, String pHOTONO, String tOTALFEE,
			String iNHOSPITALNO, String mODALITYNAME, String cHECKDATE,
			String cHECKDOCTOR, String pARTOFCHECK, String reportDate,
			String reportDoctor, String accession_num, String laybe1,
			String laybe2) {
		super();
		NAME = nAME;
		SEX = sEX;
		PINYIN = pINYIN;
		CLINICNO = cLINICNO;
		INPATIENTNO = iNPATIENTNO;
		PATIENTID = pATIENTID;
		STUDYID = sTUDYID;
		AGE = aGE;
		AGEUNIT = aGEUNIT;
		LODGESECTION = lODGESECTION;
		LODGEDOCTOR = lODGEDOCTOR;
		LODGEDATE = lODGEDATE;
		BEDNO = bEDNO;
		this.applyNO = applyNO;
		this.applySerialNumber = applySerialNumber;
		this.applyitem = applyitem;
		this.applyitemAll = applyitemAll;
		this.applyID = applyID;
		CLIISINPAT = cLIISINPAT;
		ENROLDOCTOR = eNROLDOCTOR;
		ENROLDATE = eNROLDATE;
		SURGERYRESULT = sURGERYRESULT;
		CHECKPURPOSE = cHECKPURPOSE;
		STATUS = sTATUS;
		CLASSNAME = cLASSNAME;
		PHOTONO = pHOTONO;
		TOTALFEE = tOTALFEE;
		INHOSPITALNO = iNHOSPITALNO;
		MODALITYNAME = mODALITYNAME;
		CHECKDATE = cHECKDATE;
		CHECKDOCTOR = cHECKDOCTOR;
		PARTOFCHECK = pARTOFCHECK;
		this.reportDate = reportDate;
		this.reportDoctor = reportDoctor;
		this.accession_num = accession_num;
		this.laybe1 = laybe1;
		this.laybe2 = laybe2;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getPINYIN() {
		return PINYIN;
	}
	public void setPINYIN(String pINYIN) {
		PINYIN = pINYIN;
	}
	public String getCLINICNO() {
		return CLINICNO;
	}
	public void setCLINICNO(String cLINICNO) {
		CLINICNO = cLINICNO;
	}
	public String getINPATIENTNO() {
		return INPATIENTNO;
	}
	public void setINPATIENTNO(String iNPATIENTNO) {
		INPATIENTNO = iNPATIENTNO;
	}
	public String getPATIENTID() {
		return PATIENTID;
	}
	public void setPATIENTID(String pATIENTID) {
		PATIENTID = pATIENTID;
	}
	public String getSTUDYID() {
		return STUDYID;
	}
	public void setSTUDYID(String sTUDYID) {
		STUDYID = sTUDYID;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getAGEUNIT() {
		return AGEUNIT;
	}
	public void setAGEUNIT(String aGEUNIT) {
		AGEUNIT = aGEUNIT;
	}
	public String getLODGESECTION() {
		return LODGESECTION;
	}
	public void setLODGESECTION(String lODGESECTION) {
		LODGESECTION = lODGESECTION;
	}
	public String getLODGEDOCTOR() {
		return LODGEDOCTOR;
	}
	public void setLODGEDOCTOR(String lODGEDOCTOR) {
		LODGEDOCTOR = lODGEDOCTOR;
	}
	public String getLODGEDATE() {
		return LODGEDATE;
	}
	public void setLODGEDATE(String lODGEDATE) {
		LODGEDATE = lODGEDATE;
	}
	public String getBEDNO() {
		return BEDNO;
	}
	public void setBEDNO(String bEDNO) {
		BEDNO = bEDNO;
	}
	public String getApplyNO() {
		return applyNO;
	}
	public void setApplyNO(String applyNO) {
		this.applyNO = applyNO;
	}
	public String getApplySerialNumber() {
		return applySerialNumber;
	}
	public void setApplySerialNumber(String applySerialNumber) {
		this.applySerialNumber = applySerialNumber;
	}
	public String getApplyitem() {
		return applyitem;
	}
	public void setApplyitem(String applyitem) {
		this.applyitem = applyitem;
	}
	public String getApplyitemAll() {
		return applyitemAll;
	}
	public void setApplyitemAll(String applyitemAll) {
		this.applyitemAll = applyitemAll;
	}
	public String getApplyID() {
		return applyID;
	}
	public void setApplyID(String applyID) {
		this.applyID = applyID;
	}
	public String getCLIISINPAT() {
		return CLIISINPAT;
	}
	public void setCLIISINPAT(String cLIISINPAT) {
		CLIISINPAT = cLIISINPAT;
	}
	public String getENROLDOCTOR() {
		return ENROLDOCTOR;
	}
	public void setENROLDOCTOR(String eNROLDOCTOR) {
		ENROLDOCTOR = eNROLDOCTOR;
	}
	public String getENROLDATE() {
		return ENROLDATE;
	}
	public void setENROLDATE(String eNROLDATE) {
		ENROLDATE = eNROLDATE;
	}
	public String getSURGERYRESULT() {
		return SURGERYRESULT;
	}
	public void setSURGERYRESULT(String sURGERYRESULT) {
		SURGERYRESULT = sURGERYRESULT;
	}
	public String getCHECKPURPOSE() {
		return CHECKPURPOSE;
	}
	public void setCHECKPURPOSE(String cHECKPURPOSE) {
		CHECKPURPOSE = cHECKPURPOSE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getCLASSNAME() {
		return CLASSNAME;
	}
	public void setCLASSNAME(String cLASSNAME) {
		CLASSNAME = cLASSNAME;
	}
	public String getPHOTONO() {
		return PHOTONO;
	}
	public void setPHOTONO(String pHOTONO) {
		PHOTONO = pHOTONO;
	}
	public String getTOTALFEE() {
		return TOTALFEE;
	}
	public void setTOTALFEE(String tOTALFEE) {
		TOTALFEE = tOTALFEE;
	}
	public String getINHOSPITALNO() {
		return INHOSPITALNO;
	}
	public void setINHOSPITALNO(String iNHOSPITALNO) {
		INHOSPITALNO = iNHOSPITALNO;
	}
	public String getMODALITYNAME() {
		return MODALITYNAME;
	}
	public void setMODALITYNAME(String mODALITYNAME) {
		MODALITYNAME = mODALITYNAME;
	}
	public String getCHECKDATE() {
		return CHECKDATE;
	}
	public void setCHECKDATE(String cHECKDATE) {
		CHECKDATE = cHECKDATE;
	}
	public String getCHECKDOCTOR() {
		return CHECKDOCTOR;
	}
	public void setCHECKDOCTOR(String cHECKDOCTOR) {
		CHECKDOCTOR = cHECKDOCTOR;
	}
	public String getPARTOFCHECK() {
		return PARTOFCHECK;
	}
	public void setPARTOFCHECK(String pARTOFCHECK) {
		PARTOFCHECK = pARTOFCHECK;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportDoctor() {
		return reportDoctor;
	}
	public void setReportDoctor(String reportDoctor) {
		this.reportDoctor = reportDoctor;
	}
	public String getAccession_num() {
		return accession_num;
	}
	public void setAccession_num(String accession_num) {
		this.accession_num = accession_num;
	}
	public String getLaybe1() {
		return laybe1;
	}
	public void setLaybe1(String laybe1) {
		this.laybe1 = laybe1;
	}
	public String getLaybe2() {
		return laybe2;
	}
	public void setLaybe2(String laybe2) {
		this.laybe2 = laybe2;
	}
	

}
