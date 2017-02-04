package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Userzyfymx;

/**
 * 根据住院号获取病人住院费用明细情况
 * @author Administrator
 *
 */
public class userzyfymx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public userzyfymx() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String zyh = new String(request.getParameter("zyh").getBytes("ISO-8859-1"),"UTF-8");
		Userzyfymx userzyfymx= new Userzyfymx();
		String json=userzyfymx.getuserfymx(zyh);
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
doGet(request, response);

	}

	

}
