package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platform_yfb_yppcf {
public String getypcf(String kssj,String jssj){
	List<String> list = new ArrayList<String>();
	list=getmzcflist(kssj, jssj);
	String hospitalid=GlobalConfigUtil.getHospitalid();
	String sqlString="";
	for(int i=0;i<list.size();i=i+24){
 String sql="insert into platform_ypcf(hospitalid,brid,brxm,cfh,cfje,cflxbm,cfmc,cfrq,dybz,hlbz,"
 		+ "dzf,dzs,fybz,fyrq,fysm,fyts,ghxh,mzzd,sfdzcf,sjje,czyxm,ksmc,zf,zz,zycflb)"
+ "values('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"','"+list.get(i+6)+"','"+list.get(i+7)+"','"+list.get(i+8)+"','"+list.get(i+9)+"',"
		+ "'"+list.get(i+10)+"','"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"','"+list.get(i+17)+"','"+list.get(i+18)+"',"
+ "'"+list.get(i+19)+"','"+list.get(i+20)+"','"+list.get(i+21)+"','"+list.get(i+22)+"','"+list.get(i+23)+"')";
 sqlString=sqlString+sql+"\t";
	}
	return sqlString;
}
	
	
	
	/**
	 * 药品处方
	 * 
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	public List<String> getmzcflist(String kssj, String jssj) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();

		Statement stmt;
String sql = "select Rtrim(brid)brid,Rtrim(brxm)brxm,Rtrim(cfh)cfh ,cfje,cflxbm,Rtrim(isnull(cfmc,''))cfmc,"
+ "isnull(cfrq,'')cfrq,dybz,hlbz,dzf,dzs,fybz,isnull(fyrq,'')fyrq,Rtrim(isnull(fysm,''))fysm,fyts,Rtrim(ghxh)"
+ "ghxh,Rtrim(isnull(mzzd,''))mzzd,sfdzcf,sjje,Rtrim(czyxm)czyxm,Rtrim(ksmc)ksmc,Rtrim(isnull(zf,''))zf,"
+ "Rtrim(isnull(zz,''))zz,zycflb from v_yfb_ypcf,gyb_czy,gyb_ks where "
+ "cfrq>='"+kssj+"'and cfrq<='"+jssj+"'and v_yfb_ypcf.ysbm=gyb_czy.czybm and v_yfb_ypcf.ysks=gyb_ks.ksbm";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("brid"));
				list.add(rs.getString("brxm"));
				list.add(rs.getString("cfh"));
				list.add(rs.getString("cfje"));
				list.add(rs.getString("cflxbm"));
				list.add(rs.getString("cfmc"));
				list.add(rs.getString("cfrq"));
				list.add(rs.getString("dybz"));
				list.add(rs.getString("hlbz"));
				list.add(rs.getString("dzf"));
				list.add(rs.getString("dzs"));
				list.add(rs.getString("fybz"));
				list.add(rs.getString("fyrq"));
				list.add(rs.getString("fysm"));
				list.add(rs.getString("fyts"));
				list.add(rs.getString("ghxh"));
				list.add(rs.getString("mzzd"));
				list.add(rs.getString("sfdzcf"));
				list.add(rs.getString("sjje"));
				list.add(rs.getString("czyxm"));
				list.add(rs.getString("ksmc"));
				list.add(rs.getString("zf"));
				list.add(rs.getString("zz"));
				list.add(rs.getString("zycflb"));
				
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(list);
		return list;
	}
}
