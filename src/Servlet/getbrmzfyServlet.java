package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Brmzfymx;
import jdbc.Userzyfymx;
/**
 * 获取病人门诊费用信息
 * @author Administrator
 *
 */
public class getbrmzfyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String ghxh = new String(request.getParameter("ghxh").getBytes("ISO-8859-1"),"UTF-8");
		Brmzfymx  brmzfymx= new Brmzfymx();
		String json=brmzfymx.Getmzfyxx(ghxh);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}

}
