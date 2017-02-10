package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;
import jdbc.JdbcUtilSql;
import jdbc.Userzyfymx;
/**
 * 获取门诊处方
 * @author Administrator
 *
 */
public class Mzzycf extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String ghxh = new String(request.getParameter("ghxh").getBytes("ISO-8859-1"),"UTF-8");
	     List<String> list=new ArrayList<String>();
	    
	     list=Getzycfh(ghxh);
	     System.out.print(list);
		String json=JdbcUtilSql.getmzcf(list);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}
	private  List<String> Getzycfh(String ghxh ){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select  Rtrim(cfh)cfh  from yfb_ypcf_bf where ghxh='" + ghxh + "'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("cfh"));
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
