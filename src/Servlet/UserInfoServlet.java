package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.weinxinsql;

public class UserInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String Openid = new String(req.getParameter("openid").getBytes("ISO-8859-1"),"UTF-8");
		 weinxinsql weinxinsql=new weinxinsql();
		 String json=weinxinsql.userinfotojison(Openid);
		 resp.getOutputStream().write(json.getBytes("UTF-8"));
	//	System.out.print("方法被调用");
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(req, resp);
	}

}
