package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Brjbxxbean;

import com.alibaba.fastjson.JSON;

import jdbc.JDBC;
import jdbc.StudentService;
import jdbc.weinxinsql;
import checkutil.IsWeixinUser;

public class AddFriend extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddFriend() {
		super();
	}



	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String Openid = new String(request.getParameter("openid").getBytes("ISO-8859-1"),"UTF-8");
		String sfzh =new String(request.getParameter("sfzh").getBytes("ISO-8859-1"),"UTF-8");
		String ylkh =new String(request.getParameter("ylkh").getBytes("ISO-8859-1"),"UTF-8");
		String brxm =new String(request.getParameter("brxm").getBytes("ISO-8859-1"),"UTF-8");
		String brxb =new String(request.getParameter("brxb").getBytes("ISO-8859-1"),"UTF-8");
		String brjtzz =new String(request.getParameter("brjtzz").getBytes("ISO-8859-1"),"UTF-8");
		String nl =new String(request.getParameter("nl").getBytes("ISO-8859-1"),"UTF-8");
		String nldw =new String(request.getParameter("nldw").getBytes("ISO-8859-1"),"UTF-8");
		String sj=new String(request.getParameter("lxdh").getBytes("ISO-8859-1"),"UTF-8");
		System.out.print("添加常用就诊人被调用\t");
		System.out .print("----'"+Openid+"'---'"+sfzh+"'------'"+ylkh+"'’-----"
				+ "'"+brxm+"'----'"+brxb+"'-----'"+brjtzz+"'-------'"+nl+"'------"
		
				
				+ "'"+nldw+"'-----'"+sj+"'----");
		List<String> list=new ArrayList<String>();
		list= getuserxx(Openid);
		int y=Integer.parseInt(list.get(1));
		if(y==3){
			response.getOutputStream().write("您的就诊人已超过三人不能添加！".getBytes("UTF-8"));	
		}
		else{
		int k=0;
		weinxinsql weinxinsql=new weinxinsql();
		
		StudentService service=new StudentService();
		if(IsWeixinUser.IsFriend(Openid, sfzh, ylkh)==true){
			if(!ylkh.endsWith("")){
			if(IsWeixinUser.IsFriendRegster(ylkh)==true){
				  String json=weinxinsql.getfriendinfotocheck(ylkh);
				    Brjbxxbean bean= JSON.parseObject(json, Brjbxxbean.class);
				    boolean kk=	weinxinsql.insertfriend(bean.getSfzh(),bean.getBrxm(),bean.getBrnl(), bean.getBrxb(), bean.getJtzz(),Openid,bean.getSj(), ylkh, bean.getBrnldw());
				    if(kk==true){
						//绑卡成功
						k=3;
					}
				
					
					else{
						//绑卡失败
						k=4;
					}
			}
			else {
				//此卡无效，请重新绑定
				k=2;
			}}
			else{
	 	    boolean kk=	weinxinsql.insertfriend(sfzh,brxm,nl, brxb, brjtzz,Openid,sj, ylkh, nldw);
				if(kk==true){
					//绑卡成功
					k=3;
				}
			
				
				else{
					//绑卡失败
					k=4;
				}
			}
		}
		
		else {
			//此卡已绑定无需重复绑卡
			k=1;
		}
		
		String m=String.valueOf(k);
		System.out.print("------'"+k+"'----");
		response.getOutputStream().write(m.getBytes("UTF-8"));
	}}

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

	public List<String> getuserxx(String openid){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select isaddbz ,isfriendbz from gyb_user where userid='"+openid+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("isaddbz"));
			list.add(rs.getString("isfriendbz"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}

}
