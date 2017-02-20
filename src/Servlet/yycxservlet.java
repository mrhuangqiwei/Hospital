package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.his_yycxsql;
import jdbc.weinxinsql;

/**
 * 预约挂号查询
 */
@WebServlet("/yycxservlet")
public class yycxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public yycxservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");//服务器编码
		String ylkh = new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		String sfzh= new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		his_yycxsql his_yycxsql=new his_yycxsql();
		String json=his_yycxsql.getyymx(ylkh, sfzh);
		 System.out.print(json);
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}
