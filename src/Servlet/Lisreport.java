package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.jycx;
import jdbc.weinxinsql;
/**
 * 获取检验项目结果 需要传入住院号或者挂号序号 
 * 检验项目包括：血常规，尿常规，生化，免疫。。。。。。。。。等等
 * @author Administrator
 *
 */
public class Lisreport extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String zyh = new String(request.getParameter("zyh").getBytes("ISO-8859-1"),"UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		jycx  lisreport=new jycx();
		String json=lisreport.getjyjg(zyh);
		// System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	} 

}
