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
import bean.MzcfBean;
import bean.YppfmxBean;

public  class JdbcUtilSql {
	private static String Tag="JdbcUtilSql";
	
	public static String getmzcf(List<String> listm){
		List<String>list=new ArrayList<String>();
		list=listm;
		if(list.size()==0){
			System.out.print(Tag);
			return "";
		}
		else{
			//用药方法
			Map<String, String>yyffMap=new HashMap<String, String>();
			//频次方法
			Map<String, String>pcffMap=new HashMap<String, String>();
			//剂量单位
			Map<String, String>jldwmMap=new HashMap<String, String>();
		yyffMap=getYyff();
		pcffMap=getPc();
		jldwmMap=getjldwmc();
		List<MzcfBean>mzcfBeans= new ArrayList<MzcfBean>();

		for(int i=0;i<list.size();i++){
			List<String>mzcftt=new ArrayList<String>();
			List<String>mzcfmx=new ArrayList<String>();
			mzcftt=GetpycfTT(list.get(i));
			//System.out.print(mzcftt+"\t");
			mzcfmx=Getpycfmx(list.get(i));
			//System.out.print(mzcfmx);
			String cfrq=ConvertTime.dateToStamp(mzcftt.get(11));
			//System.out.print(cfrq);
			List<YppfmxBean>yppfmxBeans=new ArrayList<YppfmxBean>();
			for(int k=0;k<mzcfmx.size();k=k+11){
				String jldwmc,yyffmc,pcmc;
				if(jldwmMap.containsKey(mzcfmx.get(k+3))){
					jldwmc=jldwmMap.get(mzcfmx.get(k+3));
				}
				else{jldwmc="";}
				if(pcffMap.containsKey(mzcfmx.get(k+8))){
					pcmc=pcffMap.get(mzcfmx.get(k+8));
				}
				else{pcmc="";}
				if(yyffMap.containsKey(mzcfmx.get(k+6))){
					yyffmc=yyffMap.get(mzcfmx.get(k+6));
				}
				else{yyffmc="";}
				YppfmxBean yppfmxBean=new YppfmxBean(mzcfmx.get(k), mzcfmx.get(k+1), mzcfmx.get(k+2),
						mzcfmx.get(k+3), jldwmc,
						mzcfmx.get(k+4), mzcfmx.get(k+5),mzcfmx.get(k+6), yyffmc, mzcfmx.get(k+7), mzcfmx.get(k+8), pcmc, mzcfmx.get(k+9), mzcfmx.get(k+10));
				yppfmxBeans.add(yppfmxBean);
			}
			MzcfBean mzcfBean=new MzcfBean(mzcftt.get(0), mzcftt.get(1), mzcftt.get(2),mzcftt.get(3),
					mzcftt.get(4), mzcftt.get(5), mzcftt.get(6),mzcftt.get(7), mzcftt.get(8), 
					mzcftt.get(9), mzcftt.get(10), cfrq, mzcftt.get(12), mzcftt.get(13), yppfmxBeans);
			mzcfBeans.add(mzcfBean);
		}
		 String json=JSON.toJSONString(mzcfBeans,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		return json;
		}
		
	}
	
	/**
	 * 获取用药方法
	 * @return
	 */
	public static  Map<String, String> getYyff(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(tjbm)as tjbm,Rtrim(tjmc) tjmc from gyb_gytj";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("tjbm"));
			list.add(rs.getString("tjmc"));
			
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
	 * 获取计量单位名称
	 * @return
	 */
	public static  Map<String, String> getjldwmc(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select RTRIM(jldwid)as jldwid,RTRIM(jldwmc)as jldwmc from ykb_jldwbm ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("jldwid"));
			list.add(rs.getString("jldwmc"));
			
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
	 * 获取用药频次
	 * @return
	 */
	public static  Map<String, String> getPc(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(pcbm) as pcbm,Rtrim(pcmc )as pcmc from gyb_pc  ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("pcbm"));
			list.add(rs.getString("pcmc"));
			
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
	 * 获取药品处方抬头
	 * @param zyh
	 * @return
	 */
	public static List<String> GetpycfTT(String cfh ){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select cfh,ghxh,cflxbm,yfbm,brxm,fysm,zz,zf,fyts,cyxm,ksmc,cfrq ,cfje, mzzd from view_yfb_ypcf where cfh='"+cfh+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("cfh"));
			list.add(rs.getString("ghxh"));
			list.add(rs.getString("cflxbm"));
			list.add(rs.getString("yfbm"));
			list.add(rs.getString("brxm"));
			list.add(rs.getString("fysm"));
			list.add(rs.getString("zz"));
			list.add(rs.getString("zf"));
			list.add(rs.getString("fyts"));
			list.add(rs.getString("cyxm"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("cfrq"));
			list.add(rs.getString("cfje"));
			list.add(rs.getString("mzzd"));
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
	 * 获取药品处方明细
	 * @param cfh
	 * @return
	 */
	public static List<String> Getpycfmx(String cfh ){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(cfh)as cfh,Rtrim(xssx) as xssx ,Rtrim(ryypbm) as ryypbm,"
				+ "Rtrim(jldw)as jldw ,Rtrim(zl) as zl  ,Rtrim(ypmc)as ypmc ,Rtrim(yyff)as yyff ,"
				+ "Rtrim(ypgg)as ypgg ,Rtrim(pcbm)as pcbm ,Rtrim(fyjl)fyjl,Rtrim(yfdw)as fydw from view_yppf where cfh='"+cfh+"' and ypbz='1' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("cfh"));
			list.add(rs.getString("xssx"));
			list.add(rs.getString("ryypbm"));
			list.add(rs.getString("jldw"));
			list.add(rs.getString("zl"));
			list.add(rs.getString("ypmc"));
			list.add(rs.getString("yyff"));
			list.add(rs.getString("ypgg"));
			list.add(rs.getString("pcbm"));
			list.add(rs.getString("fyjl"));
			list.add(rs.getString("fydw"));

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
	 * 获取医生挂号费用map
	 * @param cfh
	 * @return
	 */
	public static  Map<String, String>  Getczyghfy(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql=" select Rtrim(czybm) czybm,sum(fydj)as fydj from gyb_czy,ghb_hbsfxm ,gyb_mxfyxm  where (gyb_czy.ghzlbm=ghb_hbsfxm.ghzlbm) and  (ghb_hbsfxm.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) group by czybm";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("czybm"));
			list.add(rs.getString("fydj"));
			

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
}
