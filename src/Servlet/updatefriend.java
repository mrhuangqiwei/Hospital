package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Dao;
import jdbc.Userzyfymx;
/**
 * 跟新常用就诊人信息
 * @author Administrator
 *
 */
public class updatefriend extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String openid = new String(request.getParameter("openid").getBytes("ISO-8859-1"),"UTF-8");
		String sfzh= new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		String sfzhold= new String(request.getParameter("sfzhold").getBytes("ISO-8859-1"),"UTF-8");
		String ylkh = new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		String brxm = new String(request.getParameter("brxm").getBytes("ISO-8859-1"),"UTF-8");
		String brnl = new String(request.getParameter("nl").getBytes("ISO-8859-1"),"UTF-8");
		String brxb = new String(request.getParameter("brxb").getBytes("ISO-8859-1"),"UTF-8");
		String brjtzz = new String(request.getParameter("brjtzz").getBytes("ISO-8859-1"),"UTF-8");
		String brdh = new String(request.getParameter("brdh").getBytes("ISO-8859-1"),"UTF-8");
		
		String sql="update gyb_user_friend set sfzh='"+sfzh+"',brxm='"+brxm+"',brnl='"+brnl+"',brnldw='1',brxb='"+brxb+"',brjtzz='"+brjtzz+"',brdh='"+brdh+"' where ph='owEWzwQKO7G_uy4C0X_Wn2boPVI4' and(sfzh='"+sfzhold+"')and(ylkh='"+ylkh+"')";
		Dao dao = Dao.getInstance();
		boolean ok= dao.update(sql);
		   String yy="false";
			if(ok==true){
				yy="true";
			}
		response.getOutputStream().write(yy.getBytes("UTF-8"));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}

}
