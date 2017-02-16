package utils;
/**
 * 全局参数配置
 * @author Administrator
 *
 */
public class GlobalConfigUtil {
 private static String ghfsbm="88";
 private static String ghyks="0030";
 private static String ghybm="wxghczy";
 private static String ywckbm="888";
 private static String jdbcUrl="jdbc:sqlserver://192.168.2.253:1433;databaseName=cs";
 private static String jdbcUSERNAME1="sa";
 private static String PASSWORD="ztkj";
 private static String fbbm="1";
 private static String ghxq="3";
 private static  String jscs="1";
public static String getJscs() {
	return jscs;
}
public static void setJscs(String jscs) {
	GlobalConfigUtil.jscs = jscs;
}
public static String getFbbm() {
	return fbbm;
}
public static void setFbbm(String fbbm) {
	GlobalConfigUtil.fbbm = fbbm;
}
public static String getGhxq() {
	return ghxq;
}
public static void setGhxq(String ghxq) {
	GlobalConfigUtil.ghxq = ghxq;
}
public static String getGhfsbm() {
	return ghfsbm;
}
public static void setGhfsbm(String ghfsbm) {
	GlobalConfigUtil.ghfsbm = ghfsbm;
}
public static String getGhyks() {
	return ghyks;
}
public static void setGhyks(String ghyks) {
	GlobalConfigUtil.ghyks = ghyks;
}
public static String getGhybm() {
	return ghybm;
}
public static void setGhybm(String ghybm) {
	GlobalConfigUtil.ghybm = ghybm;
}
public static String getYwckbm() {
	return ywckbm;
}
public static void setYwckbm(String ywckbm) {
	GlobalConfigUtil.ywckbm = ywckbm;
}
public static String getJdbcUrl() {
	return jdbcUrl;
}
public static void setJdbcUrl(String jdbcUrl) {
	GlobalConfigUtil.jdbcUrl = jdbcUrl;
}
public static String getJdbcUSERNAME1() {
	return jdbcUSERNAME1;
}
public static void setJdbcUSERNAME1(String jdbcUSERNAME1) {
	GlobalConfigUtil.jdbcUSERNAME1 = jdbcUSERNAME1;
}
public static String getPASSWORD() {
	return PASSWORD;
}
public static void setPASSWORD(String pASSWORD) {
	PASSWORD = pASSWORD;
}

 
}
