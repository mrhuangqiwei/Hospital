package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.jycx;
import jdbc.ris_report_sql;
/**
 * 检查结果 比如B超 彩超，DR，核磁，CT
 * @author Administrator
 *
 */
public class RisReport extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String zyh = new String(request.getParameter("zyh").getBytes("ISO-8859-1"),"UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
	    ris_report_sql report_sql=new ris_report_sql();
		String json=report_sql.GetRisReport(zyh);
		// System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);

	}

}
