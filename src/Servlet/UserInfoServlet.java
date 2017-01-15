package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String info="";
		Cookie[] cookies=req.getCookies();
		if(cookies!=null &&cookies.length>0){
			for(Cookie c:cookies){
				if(c.getName().equals("userinfo"));
				info=c.getValue();
				System.out.print(info+"ooooooooooooooo");
			}
		}
		System.out.print("方法被调用");
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(req, resp);
	}

}
