package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.UserRecordSql;
/**
 * 获取用户记录情况
 * @author Administrator
 *
 */
public class getuserRecord extends HttpServlet {

	
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sfzh =new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		String ylkh =new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		UserRecordSql userRecordSql=new UserRecordSql();
		String json ="";
		if(ylkh.length()>4){
			json=userRecordSql.GetUserRecordByYlkh(ylkh);
		}
		else {
			json=userRecordSql.GetUserRecordBySfzh(sfzh);
		}
		response.getOutputStream().write(json.getBytes("UTF-8"));
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

}
