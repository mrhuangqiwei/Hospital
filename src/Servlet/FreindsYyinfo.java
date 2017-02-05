package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.GetFriendInfosql;
import jdbc.GetFriendsYyInfo;
//获取预约记录
public class FreindsYyinfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String openid = new String(request.getParameter("openid").getBytes("ISO-8859-1"),"UTF-8");
		//String ylkh = new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		GetFriendsYyInfo getFriendsYyInfo=new GetFriendsYyInfo();
		String json=getFriendsYyInfo.getfriendsYyinfo(openid);
		//System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}

}
