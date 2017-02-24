package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.GlobalConfigUtil;
import jdbc.Userzyfymx;

/**
 * 全局参数设置
 */
@WebServlet("/globalconfigservlet")
public class globalconfigservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public globalconfigservlet() {
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
		response.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
		String ghfsbm = new String(request.getParameter("ghfsbm").getBytes("ISO-8859-1"),"UTF-8");
		String ghyks = new String(request.getParameter("ghyks").getBytes("ISO-8859-1"),"UTF-8");
		String ghybm = new String(request.getParameter("ghybm").getBytes("ISO-8859-1"),"UTF-8");
		String ywckbm= new String(request.getParameter("ywckbm").getBytes("ISO-8859-1"),"UTF-8");
		String jdbcUrl= new String(request.getParameter("jdbcUrl").getBytes("ISO-8859-1"),"UTF-8");
		String jdbcUSERNAME1= new String(request.getParameter("jdbcUSERNAME1").getBytes("ISO-8859-1"),"UTF-8");
		String PASSWORD = new String(request.getParameter("PASSWORD").getBytes("ISO-8859-1"),"UTF-8");
		String fbbm = new String(request.getParameter("fbbm").getBytes("ISO-8859-1"),"UTF-8");
		String ghxq= new String(request.getParameter("ghxq").getBytes("ISO-8859-1"),"UTF-8");
		String jscs= new String(request.getParameter("jscs").getBytes("ISO-8859-1"),"UTF-8");
	     GlobalConfigUtil.setGhfsbm(ghfsbm);
	     GlobalConfigUtil.setGhyks(ghyks);
	     GlobalConfigUtil.setGhybm(ghybm);
	     GlobalConfigUtil.setYwckbm(ywckbm);
	     GlobalConfigUtil.setJdbcUrl(jdbcUrl);
	     GlobalConfigUtil.setPASSWORD(PASSWORD);
	     GlobalConfigUtil.setFbbm(fbbm);
	     GlobalConfigUtil.setGhxq(ghxq);
	     GlobalConfigUtil.setJscs(jscs);
	   
		//response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
