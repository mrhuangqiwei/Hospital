package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Brjbxxbean;

import com.alibaba.fastjson.JSON;

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
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		String Openid =request.getParameter("openid");
		String sfzh =request.getParameter("sfzh");
		String ylkh =request.getParameter("ylkh");
		String brxm =request.getParameter("brxm");
		String brxb =request.getParameter("brxb");
		String brjtzz =request.getParameter("brjtzz");
		String nl =request.getParameter("nl");
		String nldw =request.getParameter("nldw");
		String sj=request.getParameter("lxdh");
		String JDSJ=request.getParameter("JDSJ");
		
		System.out .print("----'"+Openid+"'---'"+sfzh+"'------'"+ylkh+"'’-----"
				+ "'"+brxm+"'----'"+brxb+"'-----'"+brjtzz+"'-------'"+nl+"'------"
								+ "'"+nldw+"'-----'"+sj+"'---'"+JDSJ+"'---");
		int k=0;
		weinxinsql weinxinsql=new weinxinsql();
		if(IsWeixinUser.IsFriend(Openid, sfzh, ylkh)==true){
			if(!ylkh.endsWith("")){
			if(IsWeixinUser.IsFriendRegster(ylkh)==true){
				  String json=weinxinsql.getfriendinfotocheck(ylkh);
				    Brjbxxbean bean= JSON.parseObject(json, Brjbxxbean.class);
				    boolean kk=	weinxinsql.insertfriend(bean.getSfzh(),bean.getBrxm(),bean.getBrnl(), bean.getBrxb(), bean.getJtzz(),Openid,bean.getSj(), ylkh, JDSJ, bean.getBrnldw());
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
	 	    boolean kk=	weinxinsql.insertfriend(sfzh,brxm,nl, brxb, brjtzz,Openid,sj, ylkh, JDSJ, nldw);
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
