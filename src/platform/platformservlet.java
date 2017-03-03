package platform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;
import jdbc.JdbcUtilSql;

import com.alibaba.fastjson.JSON;

import utils.ConvertTime;
import utils.JsonHelper;
import bean.platbridsj;

/**
 * Servlet implementation class platformservlet
 */
public class platformservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public platformservlet() {
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
		String cs = new String(request.getParameter("cs").getBytes("ISO-8859-1"),"UTF-8");
		System.out.print(sql+"\t"+cs);
		switch(cs.trim()){
		//注册
		case "1":
			platformbrxx pla=new platformbrxx();
		 platbridsj platbridsj=JSON.parseObject(sql, platbridsj.class);
		 String res=pla.brxx(ConvertTime.stampToDate(platbridsj.getKssj()),ConvertTime.stampToDate(platbridsj.getJssj()));
		 response.getOutputStream().write(res.getBytes("UTF-8"));
		 break;
		 //挂号
		case "2":
			Platformghb platformghb=new Platformghb();
		 platbridsj platghsj=JSON.parseObject(sql, platbridsj.class);
		 String resgxh=platformghb.getbrghxx(ConvertTime.stampToDate(platghsj.getKssj()),ConvertTime.stampToDate(platghsj.getJssj()));
		 response.getOutputStream().write(resgxh.getBytes("UTF-8"));
		 break;
		 //诊断
		 case "3":
			 platformmzzd zd=new platformmzzd();
			 platbridsj plzd=JSON.parseObject(sql, platbridsj.class);
			 String resmzzd=zd.getmzzd(ConvertTime.stampToDate(plzd.getKssj()),ConvertTime.stampToDate(plzd.getJssj()));
			 response.getOutputStream().write(resmzzd.getBytes("UTF-8"));
			// System.out.print(resmzzd);
			 break;
		//处方
		 case "4":
			 platform_yfb_yppcf ypcf=new platform_yfb_yppcf();
			 platbridsj plyp=JSON.parseObject(sql, platbridsj.class);
			 String ypcfstring=ypcf.getypcf(ConvertTime.stampToDate(plyp.getKssj()),ConvertTime.stampToDate(plyp.getJssj()));
			 response.getOutputStream().write(ypcfstring.getBytes("UTF-8"));
			 break;
		//配方
		 case "5":
			 platbridsj plpf=JSON.parseObject(sql, platbridsj.class);
			 String pfsj=getrqid(ConvertTime.stampToDateyyddmm(plpf.getKssj()))+"%";
			String sjsj= pfsj.replace("-","");
			 String respf=JdbcUtilSql.platformcfmx(sjsj);
			 response.getOutputStream().write(respf.getBytes("UTF-8"));
			 break;
		 //门诊表，病人费用
		 case "6":
			 platformmzfy mzbrfy=new platformmzfy();
			 platbridsj mbrfy=JSON.parseObject(sql, platbridsj.class);
			 String mzfy=mzbrfy.getplatformmzbffy(ConvertTime.stampToDate(mbrfy.getKssj()),ConvertTime.stampToDate(mbrfy.getJssj()));
			 response.getOutputStream().write(mzfy.getBytes("UTF-8"));
			 break;
			 //检验明细
		 case "7":
			 platform_lis_jymx lis_jymx=new platform_lis_jymx();
			 platbridsj platjymx=JSON.parseObject(sql, platbridsj.class);
			 String jymx=lis_jymx.platformjymx(ConvertTime.stampToDate(platjymx.getKssj()),ConvertTime.stampToDate(platjymx.getJssj()));
			System.out.print(jymx);
			 response.getOutputStream().write(jymx.getBytes("UTF-8")); 
			 break;
		 case "8":
			platform_lis_jydj jydj=new platform_lis_jydj();
			 platbridsj platjydj=JSON.parseObject(sql, platbridsj.class);
			 String jydjs=jydj.getplatformjydj(ConvertTime.stampToDate( platjydj.getKssj()),ConvertTime.stampToDate( platjydj.getJssj()));
			System.out.print(jydjs);
			 response.getOutputStream().write(jydjs.getBytes("UTF-8")); 
			 break;
		 default:break; 
		}
		// String json=getresultjson(sql);
		// response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}
	//获取时间id如
	private String  getrqid(String rq){ 
		String fString="";
			Connection conn = JDBC.getConnection();	
			Statement stmt;
			String sql="select CONVERT(varchar(12) , '"+rq+"', 112 ) as yyid";
			try {
				stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				//循环输出每一条记录
				while(rs.next())
				{
				fString=rs.getString("yyid");
				}
				stmt.close();								// 关闭连接状态对象
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return fString ;
		}
	

}
