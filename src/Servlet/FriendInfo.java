package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.GetFriendInfosql;
/**
 * 获取病人挂号序号，或者住院号，通过选择时间
 * @author Administrator
 *
 */
public class FriendInfo extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("1111111111");
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String sfzh = new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		String ylkh = new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		GetFriendInfosql getFriendInfosql=new GetFriendInfosql();
		String json=getFriendInfosql.userinfotojison(sfzh, ylkh);
		System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
