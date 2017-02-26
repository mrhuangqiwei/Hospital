package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.jycx;
import jdbc.mzzfsql;
/**
 * his门诊支付用于当医生接诊病人后病人支付成功后回调更新his数据接口
 * @author Administrator
 *
 */
public class hismzzfsql extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String ghxh = new String(request.getParameter("ghxh").getBytes("ISO-8859-1"),"UTF-8");
		String brid = new String(request.getParameter("brid").getBytes("ISO-8859-1"),"UTF-8");
		String fyhj = new String(request.getParameter("fyhj").getBytes("ISO-8859-1"),"UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		mzzfsql mzzfsql=new mzzfsql();
		String json=mzzfsql.mzjf(ghxh, brid, fyhj);
		// System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}

}
