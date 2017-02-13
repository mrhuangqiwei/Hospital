package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import utils.ConvertTime;
import bean.MzfyxxBean;
import bean.mzfymxBean;

/**
 * 病人门诊费用明细Sql
 * @author Administrator
 *
 */
public class Brmzfymx {

	/**
	 * 获取门诊费用信息
	 * @param ghxh
	 * @return
	 */
	public String Getmzfyxx(String ghxh){
		List<String> list2=new ArrayList<String>();
		if(list2.size()<2){
			return "您提交的挂号序号不正确！请查证";
		}
		else{
		List<String> mzfymxList=new ArrayList<String>();
		list2=mzfymxtt(ghxh);
		mzfymxList=getmzfymx(ghxh);
		Map<String, String>map=new HashMap<String, String>();
		map=JdbcUtilSql.getmxfcbm();
		List<mzfymxBean>mzfymxBeans=new ArrayList<mzfymxBean>();
		for(int k=0;k<mzfymxList.size();k=k+17){
			String ypmc;
			//System.out.print(mzfymxList.get(k+3)+"\t");
			if(map.containsKey(mzfymxList.get(k+3))){
				ypmc=map.get(mzfymxList.get(k+3));
				
			}else {ypmc="";}
			mzfymxBean mBean=new mzfymxBean(mzfymxList.get(k), 
mzfymxList.get(k+1), mzfymxList.get(k+2),mzfymxList.get(k+3), ypmc, mzfymxList.get(k+4),mzfymxList.get(k+5),mzfymxList.get(k+6),mzfymxList.get(k+7),mzfymxList.get(k+8),
mzfymxList.get(k+9),mzfymxList.get(k+10), mzfymxList.get(k+11), mzfymxList.get(k+12),mzfymxList.get(k+13),mzfymxList.get(k+14), mzfymxList.get(k+15), mzfymxList.get(k+6));
		mzfymxBeans.add(mBean);
		}String ghrq;
		if(list2.get(3)==null){ghrq="";
		} else{ghrq=ConvertTime.dateToStamp(list2.get(3));}
		MzfyxxBean mzfyxxBean=new MzfyxxBean(list2.get(0), list2.get(1), list2.get(2),ghrq, list2.get(4), list2.get(5), mzfymxBeans);
		 String userfy=JSON.toJSONString(mzfyxxBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		 return userfy;
	}}
	
	/**
	 * 获取门诊费用抬头
	 * @param ghxh
	 * @return
	 */
	public  List<String>mzfymxtt(String ghxh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select  brxm,brnl,brnldw,ghrq ,(select SUM(fyje)from v_mzb_brfy where ryghxh='"+ghxh+"')as fyzj,jtzz from v_his_brjbxx2 where ghxh='"+ghxh+"' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("brxm"));
			list.add(rs.getString("brnl"));
			list.add(rs.getString("brnldw"));
			list.add(rs.getString("ghrq"));
			list.add(rs.getString("fyzj"));
			list.add(rs.getString("jtzz"));
		
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取门诊费用明细
	 * @param ghxh
	 * @return
	 */
	public static List<String>getmzfymx(String ghxh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select convert(varchar(16),mzb_brfy.sfrq,120)as sfrq , RTRIM( mzb_brfy.ryghxh)ghxh,RTRIM( mzb_brfy.brxm)brxm,RTRIM( mzb_brfy.mxfyxmbm)mxfyxmbm,RTRIM( mzb_brfy.fphm)fphm,    RTRIM(mzb_brfy.yhbl)yhbl,   RTRIM(mzb_brfy.yhje)yhje,  Rtrim(mzb_brfy.fysl)fysl,   Rtrim(mzb_brfy.ytsl)ytsl,   Rtrim(mzb_brfy.fydj)fydj,   Rtrim(mzb_brfy.fyje)fyje,  RTRIM(mzb_brfy.mzys)mzys,   RTRIM( mzb_brfy.mzks)mzks,    RTRIM(mzb_brfy.hsks)hsks,   RTRIM( mzb_brfy.zxks)zxks,    RTRIM(mzb_brfy.yzlx)yzlx, mzb_brfy.tfid   FROM v_mzb_brfy mzb_brfy WITH(NOLOCK) LEFT OUTER JOIN v_ghb_brgh ghb_brgh ON mzb_brfy.ryghxh = ghb_brgh.ghxh where ghxh='"+ghxh+"'  and sfjs=1 ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("sfrq"));
			list.add(rs.getString("ghxh"));
			list.add(rs.getString("brxm"));
			list.add(rs.getString("mxfyxmbm"));
			list.add(rs.getString("fphm"));
			list.add(rs.getString("yhbl"));
			list.add(rs.getString("yhje"));
			list.add(rs.getString("fysl"));
			list.add(rs.getString("ytsl"));
			list.add(rs.getString("fydj"));
			list.add(rs.getString("fyje"));
			list.add(rs.getString("mzys"));
			list.add(rs.getString("mzks"));
			list.add(rs.getString("hsks"));
			list.add(rs.getString("zxks"));
			list.add(rs.getString("yzlx"));
			list.add(rs.getString("tfid"));

			
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
