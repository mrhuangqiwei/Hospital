package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platformmzfy {
/**
 * 平台获取门诊病人费用
 * @param kssj
 * @param jssj
 * @return
 */
public String getplatformmzbffy(String kssj,String jssj){
	List<String> list=new ArrayList<String>();
	list=getmzfylist(kssj, jssj);
	String hospitalid=GlobalConfigUtil.getHospitalid();
	String sqlString="";
	for(int i=0;i<list.size();i=i+17){
	String sql="insert into platform_mzb_brfy(hospitalid,sfrq,ghxh,brxm,mxfyxmmc,fphm,yhbl,yhje,fysl,ytsl,fydj,fyje,ysxm,ksmc,yzlx,tfid,brid,jsjlid)values"
			+ "('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"','"+list.get(i+6)+"','"+list.get(i+7)+"','"+list.get(i+8)+"','"+list.get(i+9)+"',"
+ "'"+list.get(i+10)+"','"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"')	";	
	sqlString=sqlString+sql+"\t";
	}
	return sqlString;
}
	
	
	/**
 * 获取门诊费用
 * @param kssj
 * @param jssj
 * @return
 */
public List<String>getmzfylist(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select convert(varchar(16),mzb_brfy.sfrq,120)as sfrq ,RTRIM( mzb_brfy.ryghxh)ghxh,RTRIM( mzb_brfy.brxm)brxm,xmbm.mxfyxmmc,RTRIM( mzb_brfy.fphm)fphm, RTRIM(mzb_brfy.yhbl)yhbl,   RTRIM(mzb_brfy.yhje)yhje,  Rtrim(mzb_brfy.fysl)fysl,Rtrim(mzb_brfy.ytsl)ytsl,   Rtrim(mzb_brfy.fydj)fydj,   Rtrim(mzb_brfy.fyje)fyje,Rtrim(gyb_czy.czyxm)czyxm, Rtrim(gyb_ks.ksmc)ksmc,RTRIM(mzb_brfy.yzlx)yzlx,   Rtrim(isnull(CAST(mzb_brfy.tfid as char(20)),''))tfid,Rtrim(mzb_brfy.rybrid)brid, Rtrim(mzb_brfy.ryjsjlid)jsjlid FROM v_mzb_brfy mzb_brfy WITH(NOLOCK) LEFT OUTER JOIN v_ghb_brgh ghb_brgh ON mzb_brfy.ryghxh = ghb_brgh.ghxh LEFT OUTER JOIN gyb_mxfyxm xmbm on mzb_brfy.mxfyxmbm=xmbm.mxfyxmbm LEFT OUTER JOIN gyb_czy gyb_czy on mzb_brfy.mzys=gyb_czy.czybm LEFT OUTER JOIN gyb_ks gyb_ks on mzb_brfy.mzks=gyb_ks.ksbm where "
+ "sfrq >='"+kssj+"'  and sfrq< ='"+jssj+"' and sfjs=1 ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("sfrq"));
			list.add(rs.getString("ghxh"));
			list.add(rs.getString("brxm"));
			list.add(rs.getString("mxfyxmmc"));
			list.add(rs.getString("fphm"));
			list.add(rs.getString("yhbl"));
			list.add(rs.getString("yhje"));
			list.add(rs.getString("fysl"));
			list.add(rs.getString("ytsl"));
			list.add(rs.getString("fydj"));
			list.add(rs.getString("fyje"));
			list.add(rs.getString("czyxm"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("yzlx"));
			list.add(rs.getString("tfid"));
			list.add(rs.getString("brid"));
			list.add(rs.getString("jsjlid"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(list);
		return list;
	}
}
