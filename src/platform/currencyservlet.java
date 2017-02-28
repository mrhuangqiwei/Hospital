package platform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JsonHelper;
import jdbc.GetFriendInfosql;
import jdbc.JDBC;

/**
 * Servlet implementation class currencyservlet
 */
//@WebServlet("/currencyservlet")
//平台通用servlet用来接收查询语句返回json
public class currencyservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public currencyservlet() {
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
		String sql = new String(request.getParameter("sql").getBytes("ISO-8859-1"),"UTF-8");
		 String json=getresultjson(sql);
		 response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/**
	 * 通过传入的json执行数据操作返回的是查询结果得json
	 * 
	 * @param 
	 * @return
	 */
	public String getresultjson(String json){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql=json;
		String jString = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			jString=JsonHelper.resultSetToJson(rs);
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jString;
	}
	

}
