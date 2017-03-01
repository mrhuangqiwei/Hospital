package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import utils.ConvertTime;
import bean.GhfyxmbmBean;
import bean.MzcfBean;
import bean.YppfmxBean;
import bean.brghxxtojsBean;

public class JdbcUtilSql {
	private static String Tag = "JdbcUtilSql";

	/**
	 * 输入挂号序号获取门诊处方
	 * 
	 * @param ghxh
	 * @return
	 */
	public static String getmzcf(String ghxh) {
		List<String> list = new ArrayList<String>();
		list = GetpycfTT(ghxh);
		if (list.size() == 0) {
			System.out.print(Tag);
			return "";
		} else {
			// 用药方法
			Map<String, String> yyffMap = new HashMap<String, String>();
			// 频次方法
			Map<String, String> pcffMap = new HashMap<String, String>();
			// 剂量单位
			Map<String, String> jldwmMap = new HashMap<String, String>();
			yyffMap = getYyff();
			pcffMap = getPc();
			jldwmMap = getjldwmc();
			List<MzcfBean> mzcfBeans = new ArrayList<MzcfBean>();

			for (int i = 0; i < list.size(); i = i + 15) {
				// List<String>mzcftt=new ArrayList<String>();
				List<String> mzcfmx = new ArrayList<String>();
				// mzcftt=GetpycfTT(list.get(i));
				// System.out.print(mzcftt+"\t");
				mzcfmx = Getpycfmx(list.get(i));
				// System.out.print(mzcfmx);
				String cfrq = ConvertTime.dateToStamp(list.get(i + 11));
				// System.out.print(cfrq);
				List<YppfmxBean> yppfmxBeans = new ArrayList<YppfmxBean>();
				for (int k = 0; k < mzcfmx.size(); k = k + 11) {
					String jldwmc, yyffmc, pcmc, yfdwmc;
					if (jldwmMap.containsKey(mzcfmx.get(k + 3))) {
						jldwmc = jldwmMap.get(mzcfmx.get(k + 3));
					} else {
						jldwmc = "";
					}
					if (jldwmMap.containsKey(mzcfmx.get(k + 10))) {
						yfdwmc = jldwmMap.get(mzcfmx.get(k + 10));
					} else {
						yfdwmc = "";
					}
					if (pcffMap.containsKey(mzcfmx.get(k + 8))) {
						pcmc = pcffMap.get(mzcfmx.get(k + 8));
					} else {
						pcmc = "";
					}
					if (yyffMap.containsKey(mzcfmx.get(k + 6))) {
						yyffmc = yyffMap.get(mzcfmx.get(k + 6));
					} else {
						yyffmc = "";
					}
					YppfmxBean yppfmxBean = new YppfmxBean(mzcfmx.get(k),
							mzcfmx.get(k + 1), mzcfmx.get(k + 2),
							mzcfmx.get(k + 3), jldwmc, mzcfmx.get(k + 4),
							mzcfmx.get(k + 5), mzcfmx.get(k + 6), yyffmc,
							mzcfmx.get(k + 7), mzcfmx.get(k + 8), pcmc,
							mzcfmx.get(k + 9), mzcfmx.get(k + 10), yfdwmc);
					yppfmxBeans.add(yppfmxBean);
				}
				MzcfBean mzcfBean = new MzcfBean(list.get(i), list.get(i + 1),
						list.get(i + 2), list.get(i + 3), list.get(i + 4),
						list.get(i + 5), list.get(i + 6), list.get(i + 7),
						list.get(i + 8), list.get(i + 9), list.get(i + 10),
						cfrq, list.get(i + 12), list.get(i + 13),
						list.get(i + 14), yppfmxBeans);
				mzcfBeans.add(mzcfBean);
			}
			String json = JSON.toJSONString(mzcfBeans,
					SerializerFeature.WriteMapNullValue,
					SerializerFeature.WriteNullStringAsEmpty);
			return json;
		}

	}
	
	public String get

	/**
	 * 获取用药方法
	 * 
	 * @return
	 */
	public static Map<String, String> getYyff() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select Rtrim(tjbm)as tjbm,Rtrim(tjmc) tjmc from gyb_gytj";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("tjbm"));
				list.add(rs.getString("tjmc"));

			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}

	/**
	 * 获取计量单位名称
	 * 
	 * @return
	 */
	public static Map<String, String> getjldwmc() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select RTRIM(jldwid)as jldwid,RTRIM(jldwmc)as jldwmc from ykb_jldwbm ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("jldwid"));
				list.add(rs.getString("jldwmc"));

			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}

	/**
	 * 获取用药频次
	 * 
	 * @return
	 */
	public static Map<String, String> getPc() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select Rtrim(pcbm) as pcbm,Rtrim(pcmc )as pcmc from gyb_pc  ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("pcbm"));
				list.add(rs.getString("pcmc"));

			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}

	/**
	 * 获取药品处方抬头
	 * 
	 * @param zyh
	 * @return
	 */
	public static List<String> GetpycfTT(String ghxh) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select cfh,ghxh,cflxbm,yfbm,brxm,fysm,zz,zf,fyts,cyxm,ksmc,cfrq ,cfje, mzzd,cflxmc from view_yfb_ypcf where ghxh='"
				+ ghxh + "'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("cfh"));
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
				list.add(rs.getString("cflxmc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取药品处方明细
	 * 
	 * @param cfh
	 * @return
	 */
	public static List<String> Getpycfmx(String cfh) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select Rtrim(cfh)as cfh,Rtrim(xssx) as xssx ,Rtrim(ryypbm) as ryypbm,"
				+ "Rtrim(jldw)as jldw ,Rtrim(zl) as zl  ,Rtrim(ypmc)as ypmc ,Rtrim(yyff)as yyff ,"
				+ "Rtrim(ypgg)as ypgg ,Rtrim(pcbm)as pcbm ,Rtrim(fyjl)fyjl,Rtrim(fydw)as fydw from view_yppf where cfh='"
				+ cfh + "'  ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("cfh"));
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
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取医生挂号费用map
	 * 
	 * @param cfh
	 * @return
	 */
	public static Map<String, String> Getczyghfy(Connection conn ,Statement stmt) {
		List<String> list = new ArrayList<String>();
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = " select Rtrim(czybm) czybm,sum(fydj)as fydj from gyb_czy,ghb_hbsfxm ,gyb_mxfyxm  where (gyb_czy.ghzlbm=ghb_hbsfxm.ghzlbm) and  (ghb_hbsfxm.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) group by czybm";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("czybm"));
				list.add(rs.getString("fydj"));

			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}

	/**
	 * 获取医生挂号明细费用项目编码
	 * 
	 * @param cfh
	 * @return
	 */
	public static List<GhfyxmbmBean> Getysghmxfybm(String czybm,Connection conn ,Statement stmt) {
		List<String> list = new ArrayList<String>();
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = "select Rtrim(czybm)czybm,Rtrim(czyxm)czyxm,Rtrim(gyb_mxfyxm.mxfyxmbm)mxfyxmbm,Rtrim(gyb_mxfyxm.mxfyxmmc)mxfyxmmc,gyb_mxfyxm.xlbm,dlbm, fydj from gyb_czy,ghb_hbsfxm ,gyb_mxfyxm,gyb_fyxl where czybm='"
				+ czybm
				+ "'and (gyb_czy.ghzlbm=ghb_hbsfxm.ghzlbm) and  (ghb_hbsfxm.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) and(gyb_mxfyxm.xlbm=gyb_fyxl.xlbm)";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("czybm"));
				list.add(rs.getString("czyxm"));
				list.add(rs.getString("mxfyxmbm"));
				list.add(rs.getString("mxfyxmmc"));
				list.add(rs.getString("xlbm"));
				list.add(rs.getString("dlbm"));
				list.add(rs.getString("fydj"));
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<GhfyxmbmBean> ghfyxmbmBeans = new ArrayList<GhfyxmbmBean>();
		for (int i = 0; i < list.size(); i = i + 7) {
			GhfyxmbmBean gBean = new GhfyxmbmBean(list.get(i), list.get(i + 1),
					list.get(i + 2), list.get(i + 3), list.get(i + 4),
					list.get(i + 5), list.get(i + 6));
			ghfyxmbmBeans.add(gBean);
		}
		return ghfyxmbmBeans;
	}

	/**
	 * 获取明细费用项目名称map
	 * 
	 * @return
	 */
	public static Map<String, String> getmxfcbm() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select Rtrim(mxfyxmbm) as mxfyxmbm,Rtrim(mxfyxmmc) as mxfyxmmc from gyb_mxfyxm";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("mxfyxmbm"));
				list.add(rs.getString("mxfyxmmc"));

			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}

		return map;
	}

	/** 获取和生成挂号序号的尾号如000002 **/
	public static String getywxuxhyyid(int xh1) {

		String fString = "";
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+"
				+ xh1
				+ " set @book_code=''+right('000000'+@book_code,6) select @book_code as id";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				fString = rs.getString("id");
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fString;

	}

	/**
	 * 获取挂号序号时间为挂号做准备
	 * 
	 * @return
	 */
	public static List<String> getuserghxhtime() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='ghxh' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过身份证号和医疗卡号获取病人信息判断用户以前是否注册
	 * 
	 * @param sfzh
	 * @param ylkh
	 * @return
	 */
	public static String getuserghbrxx(String sfzh, String ylkh,Statement stmt) {
		String brid = null;
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = "select top 1  Rtrim(brid) as brid from  v_ghb_zcxx where sfzh='"
				+ sfzh
				+ "' union all select top 1  Rtrim(brid) as brid from  ghb_mzylkxx where ylkh='"
				+ ylkh + "'";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				brid = rs.getString("brid");
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brid;
	}

	/** 跟新业务序号表中挂号序号的xh 和 ssrq **/
	public static boolean updateYwxhbghxh(String xh, String ssrq,Statement stmt) {
		boolean ok = false;
		String sql = "BEGIN TRAN update ghb_ywxhb Set xh ='" + xh
				+ "' , ssrq ='" + ssrq + "' Where xhlx ='ghxh' COMMIT TRAN";
		try {
			ok=stmt.execute(sql);
			 if(ok==false){
				 System.out.print("更新挂号表失败");
			 }
			//stmt1.close();								// 关闭连接状态对象
			//conn1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ok=dao.insert(sql);
		
		  return ok;
	}

	/**
	 * 获取操作员挂号诊疗编码map
	 * 
	 * @return
	 */
	public static Map<String, String> getghzlbm(Statement stmt) {
		List<String> list = new ArrayList<String>();
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = "select Rtrim(gyb_czy.czybm)czybm,Rtrim(gyb_czy.ghzlbm)ghzlbm from gyb_czy,ghb_ghzl where gyb_czy.ghzlbm=ghb_ghzl.ghzlbm";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("czybm"));
				list.add(rs.getString("ghzlbm"));
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}

		return map;
	}

	/**
	 * 通过挂号序号获取病人挂号信息用来插入注册表和门诊病人费用表
	 * 
	 * @param cfh
	 * @return
	 */
	public static brghxxtojsBean Getbrghxxtozf(String ghxh) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select RTRIM(ghxh)ghxh,Rtrim(ghzlbm)ghzlbm,"
				+ "Rtrim(ghfsbm)ghfsbm,Rtrim(v_ghb_brgh.fbbm)fbbm,"
				+ "Rtrim(v_ghb_brgh.brid)brid,Rtrim(v_ghb_brgh.czybm)"
				+ "czybm,Rtrim(v_ghb_brgh.ywckbm)ywckbm,ghrq,"
				+ "Rtrim(ghxq)ghxq,Rtrim(ghks)ghks,"
				+ "Rtrim(jzys)jzys,Rtrim(v_ghb_brgh.czyks)czyks,"
				+ "Rtrim(brxm)brxm  from v_ghb_brgh,v_ghb_zcxx where "
				+ "ghxh='"+ghxh+"' and(v_ghb_brgh.brid=v_ghb_zcxx.brid) ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("ghxh"));
				list.add(rs.getString("ghzlbm"));
				list.add(rs.getString("ghfsbm"));
				list.add(rs.getString("fbbm"));
				list.add(rs.getString("brid"));
				list.add(rs.getString("czybm"));
				list.add(rs.getString("ywckbm"));
				list.add(rs.getString("ghrq"));
				list.add(rs.getString("ghxq"));
				list.add(rs.getString("ghks"));
				list.add(rs.getString("jzys"));
				list.add(rs.getString("czyks"));
				list.add(rs.getString("brxm"));
	
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brghxxtojsBean bean = null;
		for(int k=0;k<list.size();k=k+13 ){
 bean=new brghxxtojsBean(list.get(k),list.get(k+1),
list.get(k+2),list.get(k+3),list.get(k+4),list.get(k+5),list.get(k+6),list.get(k+7),list.get(k+8),
list.get(k+9), list.get(k+10),list.get(k+11), list.get(k+12));
		}
		return bean;
	}
	/**
	 * 获取结算记录的xhlx , xh , ssrq , cslx
	 * @return
	 */

	public  static List<String> getuserjsjltime(){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='jsjlid' ";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{
		list.add(rs.getString("xhlx"));
		list.add(rs.getString("xh"));
		list.add(rs.getString("ssrq"));
		list.add(rs.getString("cslx"));
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
	 * 获取流水号ID
	 * @return
	 */
	public static Long  getyyghid(){ 
	String fString="";
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select CONVERT(varchar(12) , getdate(), 112 ) as yyid";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			fString=rs.getString("yyid");
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fString=fString+"000000";
		
	return Long.parseLong(fString) ;
	}
	/**
	 * 获取序号
	 * @return
	 */

	public  static  String  getxh( int xh1){
	String xh = null;
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql=" declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+'"+xh1+"' set @book_code=''+right('000000'+@book_code,6) select @book_code  as xh ";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{xh=rs.getString("xh");
		
		}
		stmt.close();								// 关闭连接状态对象
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return xh;
	}
	
	/**
	 * 更新业务序号表中结算记录ID
	 * @param xh1
	 * @param ssrq
	 * @return
	 */
	public static boolean updatejsjl(String xh1, String ssrq ){
		boolean ok=false;
		Dao dao=Dao.getInstance();
		String sql="BEGIN TRAN update ghb_ywxhb Set xh ='" + xh1 + "' , ssrq ='" + ssrq + "' Where xhlx ='jsjlid' COMMIT TRAN";
		 ok=dao.insert(sql);
		 if(ok=false){
			 System.out.print("更新结算记录表");
		 }
		  return ok;
	}
	/**
	 * 获取国籍编码
	 * @return
	 */
	public static Map<String, String> gjmap() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select RTRIM(gjbm)gjbm,RTRIM(gjmc) gjmc from gyb_gj where qyzt<>1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("gjbm"));
				list.add(rs.getString("gjmc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}
	/**
	 * 获取名族编码
	 * @return
	 */
	public static Map<String, String> mzmp() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select RTRIM(mzbm)mzbm,RTRIM(mzmc) mzmc  from gyb_mz where qyzt<>1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("mzbm"));
				list.add(rs.getString("mzmc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}
	/**
	 * 获取籍贯编码
	 * @return
	 */
	public static Map<String, String>jgmp() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select RTRIM(jgbm)jgbm,RTRIM(jgmc) jgmc from gyb_jg where qyzt<>1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("jgbm"));
				list.add(rs.getString("jgmc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}
	/**
	 * 获取职务编码
	 * @return
	 */
	public static Map<String, String>zwmp() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " select RTRIM(zwbm)zwbm,RTRIM(zwmc)zwmc  from gyb_zw where qyzt<>1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("zwbm"));
				list.add(rs.getString("zwmc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}
	/**
	 * 获取职业编码
	 * @return
	 */
	public static Map<String, String>zymp() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " select RTRIM(zybm)zybm,RTRIM(zymc) zymc from gyb_zy  where qyzt<>1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("zybm"));
				list.add(rs.getString("zymc"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i = i + 2) {
			map.put(list.get(i), list.get(i + 1));
		}
		return map;
	}
	
}
