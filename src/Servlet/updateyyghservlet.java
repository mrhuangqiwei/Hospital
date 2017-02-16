package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;












import utils.ConvertTime;
import utils.GlobalConfigUtil;
import bean.GhfyxmbmBean;
import bean.brghxxtojsBean;
import jdbc.Dao;
import jdbc.JDBC;
import jdbc.JdbcUtilSql;
import jdbc.Userzyfymx;

public class updateyyghservlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String ghxh = new String(request.getParameter("ghxh").getBytes("ISO-8859-1"),"UTF-8");
		String yyghid=new String(request.getParameter("ghxh").getBytes("ISO-8859-1"),"UTF-8");
		boolean ok=false;
		if(!ghxh.equals("")&&!ghxh.equals("当日挂号失败！"))
			
		{  ok=updateghbbrgh(ghxh);}
		else{
			ok=updateghbyygh(yyghid);
		}
		
		String kString="";
		if(ok==true){
			kString="";	
		}
		else{kString="false";}
		//Userzyfymx userzyfymx= new Userzyfymx();
		
		//String json=userzyfymx.getuserfymx(zyh);
		response.getOutputStream().write(kString.getBytes("UTF-8"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}
	public static  boolean updateghbbrgh(String ghxh){
		Dao dao=Dao.getInstance();
		boolean ok =false;
		String sqlString="";
		brghxxtojsBean bean=JdbcUtilSql.Getbrghxxtozf(ghxh);
		String ghlx=GlobalConfigUtil.getGhfsbm();
		if(bean==null){System.out.print("没有该挂号序号"); return false;  }
		else{
		System.out.print("挂号序号不位空！"+"\t");
		String sql1="update ghb_brgh set yygh='1' where ghxh='"+ghxh+"'";
	    sqlString=sqlString+sql1+"\t";
	
	    Map<String, String>czyghfyMap=new HashMap<String, String>();
	    //操作员挂号费用
	    czyghfyMap=Getczyghfy();
	    String fyhj="";
	    if(czyghfyMap.containsKey(bean.getJzys())){fyhj=czyghfyMap.get(bean.getJzys());}else {fyhj="";}
	    
	    //初始序号
	    String csxhString="000001";
	  //序号前段数字如20170213000000
		Long id=JdbcUtilSql.getyyghid();
	    List<String> list=new ArrayList<String>();
	    //获取结算记录的xhlx , xh , ssrq , cslx
	    list=JdbcUtilSql.getuserjsjltime();
	  //获取挂号序号的尾数
		int xh = Integer.parseInt(list.get(1).trim()) + 1;
		//根据输入的尾号如“23”返回如"000023"的字符串，用来更新业务序号表
		String xh1=JdbcUtilSql.getxh(xh);
		String jsjlid="";
		//获取服务器当前时间
		String ssrq= ConvertTime.Getdnowdatexx();
		int k=ConvertTime.daysBetween(list.get(2));
		//结算参数
		String jscs=GlobalConfigUtil.getJscs();
		 List<GhfyxmbmBean>ghfyxmbmBeans= new ArrayList<GhfyxmbmBean>();
		 //获取挂号费用明细费用项目编码
		 ghfyxmbmBeans=Getysghmxfybm(bean.getJzys());
		 //当当前时间与上次结算时间的差大于0时
	    if(k>0){
	   ok= JdbcUtilSql.updatejsjl(csxhString, ssrq);
	    jsjlid=String.valueOf(id+1) ; 
	    String sql3="insert into  ghb_jsjl (jsjlid,ghxh,jscs,czybm,ywckbm,brid,fyhj,xjzf,jsrq,czyks,bzms)"
	    		+ " values('"+jsjlid+"','"+ghxh+"','"+jscs+"','"+bean.getCzybm()+"','"+bean.getYwckbm()+"','"+bean.getBrid()+"','"+fyhj+"','"+fyhj+"','"+ssrq+"','"+bean.getCzyks()+"','挂号结算')";
		sqlString=sqlString+sql3+"\t";
		String sql7="";
		for(int i=0;i<ghfyxmbmBeans.size();i++){
			String sql4="insert into mzb_brfy (fbbm,ywckbm,xlbm,mxfyxmbm,czybm,dlbm,"
					+ "sfrq,rybrid,ryghxh,brxm,fysl,fydj,fyje,mzys,mzks, sfjs, ryjsjlid)values "
	+ "('"+bean.getFbbm()+"','"+bean.getYwckbm()+"','"+ghfyxmbmBeans.get(i).getXlbm()+"','"+ghfyxmbmBeans.get(i).getMxfyxmbm()+""
			+ "','"+bean.getCzybm()+"','"+ghfyxmbmBeans.get(i).getDlbm()+"','"+ssrq+"','"+bean.getBrid()+"',"
					+ "'"+bean.getGhxh()+"','"+bean.getBrxm()+"','1','"+ghfyxmbmBeans.get(i).getFydj()+"','"+ghfyxmbmBeans.get(i).getFydj()+"','"+bean.getJzys()+"','"+bean.getGhks()+"','1','"+jsjlid+"')";
			sql7=sql7+sql4+"\t";
		}
		sqlString=sqlString+sql7+"\t";
		System.out.print(sqlString);
		ok=dao.insert(sqlString);
		return ok;
		}
	    //当当前时间和上次结算时间的差小于0时
	   else {   
		ok=   JdbcUtilSql.updatejsjl(xh1, ssrq);
		    jsjlid=String.valueOf(id+xh) ; 
		    String sql3="insert into  ghb_jsjl (jsjlid,ghxh,jscs,czybm,ywckbm,brid,fyhj,xjzf,jsrq,czyks,bzms)"
		    		+ " values('"+jsjlid+"','"+ghxh+"','"+jscs+"','"+bean.getCzybm()+"','"+bean.getYwckbm()+"','"+bean.getBrid()+"','"+fyhj+"','"+fyhj+"','"+ssrq+"','"+bean.getCzyks()+"','挂号结算')";
			sqlString=sqlString+sql3+"\t";
			String sql7="";
			for(int i=0;i<ghfyxmbmBeans.size();i++){
				
				String sql4="insert into mzb_brfy (fbbm,ywckbm,xlbm,mxfyxmbm,czybm,dlbm,"
						+ "sfrq,rybrid,ryghxh,brxm,fysl,fydj,fyje,mzys,mzks,sfjs,ryjsjlid)values "
		+ "('"+bean.getFbbm()+"','"+bean.getYwckbm()+"','"+ghfyxmbmBeans.get(i).getXlbm()+"','"+ghfyxmbmBeans.get(i).getMxfyxmbm()+""
				+ "','"+bean.getCzybm()+"','"+ghfyxmbmBeans.get(i).getDlbm()+"','"+ssrq+"','"+bean.getBrid()+"',"
						+ "'"+bean.getGhxh()+"','"+bean.getBrxm()+"','1','"+ghfyxmbmBeans.get(i).getFydj()+"','"+ghfyxmbmBeans.get(i).getFydj()+"','"+bean.getJzys()+"','"+bean.getGhks()+"','1','"+jsjlid+"')";
			sql7=sql7+sql4+"\t";
			
			}
			sqlString=sqlString+sql7+"\t";
			System.out.print(sqlString+"当日时间");
		ok=	dao.insert(sqlString);
		return ok;
	   }
	    
		//else {System.out.print("没有该挂号序号");}
	     
	}
	
	
	
	}

	/**
	 * 获取医生挂号费用map
	 * 
	 * @param cfh
	 * @return
	 */
	public static Map<String, String> Getczyghfy() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " select Rtrim(czybm) czybm,sum(fydj)as fydj from gyb_czy,ghb_hbsfxm ,gyb_mxfyxm  where (gyb_czy.ghzlbm=ghb_hbsfxm.ghzlbm) and  (ghb_hbsfxm.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) group by czybm";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("czybm"));
				list.add(rs.getString("fydj"));

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
	 * 获取医生挂号明细费用项目编码
	 * 
	 * @param cfh
	 * @return
	 */
	public static List<GhfyxmbmBean> Getysghmxfybm(String czybm) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select Rtrim(czybm)czybm,Rtrim(czyxm)czyxm,Rtrim(gyb_mxfyxm.mxfyxmbm)mxfyxmbm,Rtrim(gyb_mxfyxm.mxfyxmmc)mxfyxmmc,gyb_mxfyxm.xlbm,dlbm, fydj from gyb_czy,ghb_hbsfxm ,gyb_mxfyxm,gyb_fyxl where czybm='"
				+ czybm
				+ "'and (gyb_czy.ghzlbm=ghb_hbsfxm.ghzlbm) and  (ghb_hbsfxm.mxfyxmbm=gyb_mxfyxm.mxfyxmbm) and(gyb_mxfyxm.xlbm=gyb_fyxl.xlbm)";
		try {
			stmt = conn.createStatement();
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
			stmt.close(); // 关闭连接状态对象
			conn.commit();
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
	 * 更新预约挂号表
	 * @param yyghid
	 * @return
	 */
	private boolean updateghbyygh(String yyghid){
		boolean ok=false;
		String sql="update ghb_yygh set yyjfbz='1' where yyghid='"+yyghid+"'";
	   Dao dao=Dao.getInstance();
	   ok=dao.update(sql);
	  return ok;
	}
	
	
	
}
