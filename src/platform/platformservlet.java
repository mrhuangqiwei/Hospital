package platform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import utils.ConvertTime;
import utils.JsonHelper;
import bean.platbridsj;

/**
 * Servlet implementation class platformservlet
 */
@WebServlet("/platformservlet")
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
		case "1":
			platformbrxx pla=new platformbrxx();
		 platbridsj platbridsj=JSON.parseObject(sql, platbridsj.class);
		 String res=pla.brxx(ConvertTime.stampToDate(platbridsj.getKssj()),ConvertTime.stampToDate(platbridsj.getJssj()));
		 response.getOutputStream().write(res.getBytes("UTF-8"));
		 break;
		case "2":
			Platformghb platformghb=new Platformghb();
		 platbridsj platghsj=JSON.parseObject(sql, platbridsj.class);
		 String resgxh=platformghb.getbrghxx(ConvertTime.stampToDate(platghsj.getKssj()),ConvertTime.stampToDate(platghsj.getJssj()));
		 response.getOutputStream().write(resgxh.getBytes("UTF-8"));
		 break;
		 case "3":
			 platformmzzd zd=new platformmzzd();
			 platbridsj plzd=JSON.parseObject(sql, platbridsj.class);
			 String resmzzd=zd.getmzzd(ConvertTime.stampToDate(plzd.getKssj()),ConvertTime.stampToDate(plzd.getJssj()));
			 response.getOutputStream().write(resmzzd.getBytes("UTF-8"));
			 System.out.print(resmzzd);
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

}
