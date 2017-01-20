package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import bean.FymxBean;
import bean.userzymxfybean;
/**
 * 获取病人费用明细清单
 * @author Administrator
 *
 */
public class Userzyfymx {
	Dao dao = Dao.getInstance();
	
	public String  getuserfymx(String zyh) {
		List<String> list=new ArrayList<String>();
		Map<String, String>map=getmxfcbm();
		list=getbrfymx(zyh);
		List<FymxBean> fymxBeans=new ArrayList<FymxBean>();
		 System.out.print("这是get4"+list.get(4)+"\t");
    	 System.out.print("这是get3"+list.get(3)+"\t");
    	 System.out.print("这是map3"+map.get(list.get(3))+"\t");
    	 if(list.get(4).equals("")){
    		 System.out.print("--------\t");
    	 }
		for(int i=0;i<list.size();i=i+14){
			 String ypmc="";
			
			 
             if (list.get(i+4).equals(""))
             {
            	 ypmc=map.get(list.get(i+3));
              }
             else { ypmc =list.get(i+4);  }
 FymxBean fymxBean=new FymxBean(list.get(i), list.get(i+1), list.get(i+2), list.get(i+3),
		 ypmc,list.get(i+5), list.get(i+6),list.get(i+7),list.get(i+8), list.get(i+9), list.get(i+10),
      list.get(i+11),list.get(i+12), list.get(i+13));
 fymxBeans.add(fymxBean);  

 
		}
		 String jString=ZyUserinfo(zyh);
		 //JSONArray jsonArr=(JSONArray) JSONArray.toJSON(jString);
		 
		//JS   json=JSON.toJSONString(fymxBeans);
		 userzymxfybean userzymxfybean=new userzymxfybean(jString,  fymxBeans);
		 String userfy=JSON.toJSONString(userzymxfybean);
		 return userfy;
	}
	
	/**
	 * 获取明细费用项目名称map
	 * @return
	 */
	public Map<String, String> getmxfcbm(){
			List<String> list=new ArrayList<String>();
			Connection conn = JDBC.getConnection();	
			Statement stmt;
			String sql="select Rtrim(mxfyxmbm) as mxfyxmbm,Rtrim(mxfyxmmc) as mxfyxmmc from gyb_mxfyxm";
			try {
				stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				//循环输出每一条记录
				while(rs.next())
				{
				list.add(rs.getString("mxfyxmbm"));
				list.add(rs.getString("mxfyxmmc"));

				}
				stmt.close();								// 关闭连接状态对象
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, String>map=new HashMap<String, String>();
			for(int i=0;i<list.size();i=i+2){
				map.put(list.get(i), list.get(i+1));
			}
			
			return map;
		}
	/**
	 * 获取住院病人费用基本信息
	 * @param zyh
	 * @return
	 */
	public String ZyUserinfo(String zyh){
		Connection conn = JDBC.getConnection();	
		String sql="select Rtrim(zyh) as zyh,convert(varchar(16),ryrq ,120)as ryrq,convert(varchar(16),cyrq ,120)as cyrq,Rtrim(rycwid) as rycwid,cyks,ryks,ksmc,brxm,cast(v_zyb_zcxx.brnl as int) as brnl,Rtrim(v_zyb_rydj.brnldw) as brnldw, Rtrim(brxb)as brxb ,jtzz,fbmc,(SELECT sum ( fyje ) FROM v_zyb_brfy zyb_brfy WITH( NOLOCK ) WHERE zyh ='"+zyh+"'AND yxbz ='1' ) as fyje ,(SELECT sum ( yjje )FROM v_zyb_yjjl WHERE zyh ='"+zyh+"' ) as yjje from v_zyb_rydj,v_zyb_zcxx,gyb_brfb,gyb_ks  where zyh='"+zyh+"' and v_zyb_rydj.brid=v_zyb_zcxx.brid and v_zyb_rydj.fbbm=gyb_brfb.fbbm and(v_zyb_rydj.ryks=gyb_ks.ksbm)";
		String json="";
		try {
			json=new JSONValidatingWriter().write(
				        new QueryRunner().query(conn, sql, new MapListHandler()));
			//conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json1=json.substring(1, json.length() - 1);
		return json1;
	}
	/**
	 * 获取住院费用明细
	 * @param zyh
	 * @return
	 */
	public static List<String>getbrfymx(String zyh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(zyh)as zyh,Rtrim(xlbm) as xlbm,Rtrim(dlbm) as dlbm,Rtrim(mxfyxmbm)"
				+ " as mxfyxmbm,Rtrim(ypmc)as ypmc,Rtrim(fysl) as fysl,Rtrim(fydj) as fydj,Rtrim(fyje)"
				+ " as fyje,Rtrim(yhje)as yhje, Rtrim(tclb)as tclb,Rtrim(nbtclb)as nbtclb,Rtrim(fygg)as "
				+ "fygg,Rtrim(jldwmc)as jldwmc,Rtrim(sgbm) as sgbm  from view_brfy_mx where zyh='"+zyh+"' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("zyh"));
			list.add(rs.getString("xlbm"));
			list.add(rs.getString("dlbm"));
			list.add(rs.getString("mxfyxmbm"));
			list.add(rs.getString("ypmc"));
			list.add(rs.getString("fysl"));
			list.add(rs.getString("fydj"));
			list.add(rs.getString("fyje"));
			list.add(rs.getString("yhje"));
			list.add(rs.getString("tclb"));
			list.add(rs.getString("nbtclb"));
			list.add(rs.getString("fygg"));
			list.add(rs.getString("jldwmc"));
			list.add(rs.getString("sgbm"));

			
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
	 * @param zyh
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
