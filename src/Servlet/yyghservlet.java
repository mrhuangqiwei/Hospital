package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.StudentService;
import jdbc.YyghSql;
import jdbc.weinxinsql;
import bean.Brjbxxbean;
import checkutil.IsWeixinUser;

import com.alibaba.fastjson.JSON;

public class yyghservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public yyghservlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String yyghrq = new String(request.getParameter("yyghrq").getBytes("ISO-8859-1"),"UTF-8");
		String brxm =new String(request.getParameter("brxm").getBytes("ISO-8859-1"),"UTF-8");
		String brxb =new String(request.getParameter("brxb").getBytes("ISO-8859-1"),"UTF-8");
		String brnldw =new String(request.getParameter("brnldw").getBytes("ISO-8859-1"),"UTF-8");
		String sfzh =new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		String jtzz =new String(request.getParameter("jtzz").getBytes("ISO-8859-1"),"UTF-8");
		String nl =new String(request.getParameter("nl").getBytes("ISO-8859-1"),"UTF-8");
		String sj =new String(request.getParameter("sj").getBytes("ISO-8859-1"),"UTF-8");
		String yyys=new String(request.getParameter("yyys").getBytes("ISO-8859-1"),"UTF-8");
		String yyks=new String(request.getParameter("yyks").getBytes("ISO-8859-1"),"UTF-8");
		String yydjrq=new String(request.getParameter("yydjrq").getBytes("ISO-8859-1"),"UTF-8");
		String yyyxrq=new String(request.getParameter("yyyxrq").getBytes("ISO-8859-1"),"UTF-8");
		String  mxfyxmbm=new String(request.getParameter("mxfyxmbm").getBytes("ISO-8859-1"),"UTF-8");
		String yyjfbz=new String(request.getParameter("yyjfbz").getBytes("ISO-8859-1"),"UTF-8");
		String ylkh=new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		YyghSql yyghSql=new YyghSql();
		String  yf=yyghSql.appointment(yyghrq, brxm, brxb, nl, sfzh, jtzz, sj, 
				yyys, yyks, yydjrq, yyyxrq, mxfyxmbm, yyjfbz);
		System.out.print("预约挂号\t");
		System.out .print("----'"+yf);
	
		
		
		//String m=String.valueOf(k);
		//System.out.print("------'"+k+"'----");
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
