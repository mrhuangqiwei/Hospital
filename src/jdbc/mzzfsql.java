package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ConvertTime;
import utils.GlobalConfigUtil;
import utils.JsonHelper;

/**
 * 门诊支付sql
 * @author Administrator
 *
 */
public class mzzfsql {
	public void mzjf(String ghxh,String brid,String fyhj) {
		
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		//获取系统当前时间
		String ssrq=ConvertTime.Getdnowdatexx();
		Statement stmt;
		String json="";
		String jsjlid;
		String sql=" begin tran  SELECT Top 1 *  From ghb_ywxhb  Where xhlx ='jsjlid' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("xhlx"));
			list.add(rs.getString("xh"));
			list.add(rs.getString("ssrq"));
			list.add(rs.getString("cslx"));
			}
			String csxh="000001";
			//获取当前时间与系统时间的差
			int k=ConvertTime.daysBetween(list.get(2));
			int jsxh = Integer.parseInt(list.get(1).trim()) + 1;
			String upxh=getxh(jsxh,stmt);
			
			//序号前段数字如20170213000000
			Long jsid=getyyghid(stmt);
			//药品处方list
			List<String> ypcflList=new ArrayList<String>();
			//检查申请list
			List<String> jcsqlist=new ArrayList<String>();
			//检验申请list 
			List<String> jysqlList=new ArrayList<String>();
			//治疗处置list 
			List<String> zlczList=new ArrayList<String>();
			//门诊手术申请list 
			List<String> mzssList=new ArrayList<String>();
			//医疗医嘱list 
			List<String> ylyzList=new ArrayList<String>();
			//当当前时间与输入时间的差大于0时执行
			String sqlString="begin tran" +"\t";
			if(k>0){
				 updatejsjl(csxh, ssrq, stmt );
				 jsjlid=String.valueOf(jsid+1) ;
				 sqlString=sqlString+ inserjsjl(jsjlid, ghxh, GlobalConfigUtil.getGhybm(),GlobalConfigUtil.getYwckbm(), brid, fyhj, "0.00", fyhj, "0.00", "0", "0","0", ssrq, GlobalConfigUtil.getGhyks());
			   
				 sqlString=sqlString+"\t";
				 sqlString=sqlString+ updatemzbbrfy(GlobalConfigUtil.getYwckbm(),GlobalConfigUtil.getGhybm(), ssrq, "1", "1", jsjlid, "0", ghxh);
				 sqlString=sqlString+"\t";
				 ypcflList=getyfbcfh(ghxh,stmt);
			     jcsqlist=getjcsqxh(ghxh,stmt);
			     jysqlList=getjysqh(ghxh,stmt);
			     zlczList=getzlczh(ghxh,stmt);
			     mzssList=getmzssh(ghxh,stmt);
			     ylyzList=getmzylyzh(ghxh,stmt);
			     String yString="";
			    //药房表药品处方
			    for(int m=0;m<ypcflList.size();m++){
			    	String yfbString=updateyfbypcf(ypcflList.get(m),GlobalConfigUtil.getGhybm(), ssrq);
			    	yString=yString+yfbString+"\t";
			    }
			    sqlString=sqlString+yString+"\t";
			    String jcsString="";
			    //检查申请
			    for(int n=0;n<jcsqlist.size();n++){
			    	String jcsqs=updatejcsq(jcsqlist.get(n), GlobalConfigUtil.getGhybm(), ssrq);
			    	jcsString=jcsString+jcsqs+"\t";
			    	}
			    sqlString=sqlString+jcsString+"\t";
		     String jysqString="";
		     //检验申请
		     for(int o=0;o<jysqlList.size();o++ ){
		    	 String jysq=updatejysq(jysqlList.get(o), GlobalConfigUtil.getGhybm(),ssrq);
		    	 jysqString=jysqString+jysq+"\t";
		     }
			    sqlString=sqlString+jysqString+"\t";
			    String zlczString="";
			    //治疗处置
			    for(int p=0;p<zlczList.size();p++){
			    	String zlcz=updatezlcz(zlczList.get(p),GlobalConfigUtil.getGhybm(), ssrq);
			    	zlczString=zlczString+zlcz+"\t";
			    }
			    sqlString=sqlString+zlczString+"\t";
			    String mzssString="";
			    //门诊手术
			    for(int q=0;q<mzssList.size();q++){
			    	String mzss=updatemzss(mzssList.get(q),GlobalConfigUtil.getGhybm(), ssrq);
			    	mzssString=mzssString+mzss+"\t";
			    }
			    sqlString=sqlString+mzssString+"\t";
			    String ylyzsString="";
			    //医疗医嘱
			    for(int r=0;r<ylyzList.size();r++){
			    	String ylyz=updateylyz(ylyzList.get(r), GlobalConfigUtil.getGhybm(), ssrq);
			    ylyzsString=ylyzsString+ylyz+"\t";
			    }
			    sqlString=sqlString+ylyzsString+"\t";
			    sqlString=sqlString+"commit tran";
			    System.out.print(sqlString);
			    stmt.execute(sqlString);
			    }
			
			
			
			else{
				updatejsjl(upxh, ssrq, stmt);
				jsjlid=String.valueOf(jsid+jsxh);
				 sqlString=sqlString+ inserjsjl(jsjlid, ghxh, GlobalConfigUtil.getGhybm(),GlobalConfigUtil.getYwckbm(), brid, fyhj, "0.00", fyhj, "0.00", "0", "0","0", ssrq, GlobalConfigUtil.getGhyks());
				   
				 sqlString=sqlString+"\t";
				 sqlString=sqlString+ updatemzbbrfy(GlobalConfigUtil.getYwckbm(),GlobalConfigUtil.getGhybm(), ssrq, "1", "1", jsjlid, "0", ghxh);
				 sqlString=sqlString+"\t";
				 ypcflList=getyfbcfh(ghxh,stmt);
			     jcsqlist=getjcsqxh(ghxh,stmt);
			     jysqlList=getjysqh(ghxh,stmt);
			     zlczList=getzlczh(ghxh,stmt);
			     mzssList=getmzssh(ghxh,stmt);
			     ylyzList=getmzylyzh(ghxh,stmt);
			     String yString="";
			    //药房表药品处方
			    for(int m=0;m<ypcflList.size();m++){
			    	String yfbString=updateyfbypcf(ypcflList.get(m),GlobalConfigUtil.getGhybm(), ssrq);
			    	yString=yString+yfbString+"\t";
			    }
			    sqlString=sqlString+yString+"\t";
			    String jcsString="";
			    //检查申请
			    for(int n=0;n<jcsqlist.size();n++){
			    	String jcsqs=updatejcsq(jcsqlist.get(n), GlobalConfigUtil.getGhybm(), ssrq);
			    	jcsString=jcsString+jcsqs+"\t";
			    	}
			    sqlString=sqlString+jcsString+"\t";
		     String jysqString="";
		     //检验申请
		     for(int o=0;o<jysqlList.size();o++ ){
		    	 String jysq=updatejysq(jysqlList.get(o), GlobalConfigUtil.getGhybm(),ssrq);
		    	 jysqString=jysqString+jysq+"\t";
		     }
			    sqlString=sqlString+jysqString+"\t";
			    String zlczString="";
			    //治疗处置
			    for(int p=0;p<zlczList.size();p++){
			    	String zlcz=updatezlcz(zlczList.get(p),GlobalConfigUtil.getGhybm(), ssrq);
			    	zlczString=zlczString+zlcz+"\t";
			    }
			    sqlString=sqlString+zlczString+"\t";
			    String mzssString="";
			    //门诊手术
			    for(int q=0;q<mzssList.size();q++){
			    	String mzss=updatemzss(mzssList.get(q),GlobalConfigUtil.getGhybm(), ssrq);
			    	mzssString=mzssString+mzss+"\t";
			    }
			    sqlString=sqlString+mzssString+"\t";
			    String ylyzsString="";
			    //医疗医嘱
			    for(int r=0;r<ylyzList.size();r++){
			    	String ylyz=updateylyz(ylyzList.get(r), GlobalConfigUtil.getGhybm(), ssrq);
			    ylyzsString=ylyzsString+ylyz+"\t";
			    }
			    sqlString=sqlString+ylyzsString+"\t";
			    sqlString=sqlString+"commit tran";
			    System.out.print(sqlString);
			    stmt.execute(sqlString);
			}
			
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/** 获取结算记录的id前面的段
	 * 
	 * @param stmt
	 * @return
	 */
	public Long  getyyghid(Statement stmt){ 
	String fString="";
		//Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select CONVERT(varchar(12) , getdate(), 112 ) as yyid";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			fString=rs.getString("yyid");
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fString=fString+"000000";
		
	return
			
			
			Long.parseLong(fString) ;
	}
	/**
	 * 获取序号
	 * @return
	 */

	public String  getxh( int xh1,Statement stmt){
	String xh = null;
	String sql=" declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+'"+xh1+"' set @book_code=''+right('000000'+@book_code,6) select @book_code  as xh ";
	try {
		//stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{xh=rs.getString("xh");
		
		}
		//stmt.close();								// 关闭连接状态对象
		//conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return xh;
	}
	
	/**
	 * 更新业务序号表中结算记录ID
	 * @param xh1
	 * @param ssrq
	 * @return
	 */
	/**
	public boolean updatejsjl(String xh1, String ssrq,Statement stmt ){
		boolean ok=false;
		String sql=" update ghb_ywxhb Set xh ='" + xh1 + "' , ssrq ='" + ssrq + "' Where xhlx ='jsjlid' ";
		try {
			ok=stmt.execute(sql);
			//stmt2.close();								// 关闭连接状态对象
			//conn2.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(ok=false){
			 System.out.print("更新业务序号表1失败");
		 }
		  return ok;
	}**/
	/**
	 * 插入结算记录表
	 * @param jsjlid
	 * @param ghxh
	 * @param czybm
	 * @param ywckbm
	 * @param brid
	 * @param fyhj
	 * @param ylkzf
	 * @param xjzf
	 * @param ybkzf
	 * @param jsqf
	 * @param qfbj
	 * @param jszt
	 * @param jsrq
	 * @param czyks
	 * @return
	 */
	public String  inserjsjl(String jsjlid,String ghxh,String czybm,String ywckbm,String brid,String fyhj,
			String ylkzf,String xjzf,
			String ybkzf,String jsqf,String qfbj,String jszt,String jsrq,String czyks){

		String sql="INSERT INTO ghb_jsjl ( jsjlid, ghxh, jscs, czybm, ywckbm, brid, fyhj, ylkzf,"
				+ " xjzf, ybkzf, jsqf, qfbj,jszt, jsrq, czyks, bzms ) VALUES ( '"+jsjlid+"',"
				+ " '"+ghxh+"', 1, '"+czybm+"', '"+ywckbm+"', '"+brid+"','"+fyhj+"', 0,"
	+ "'"+xjzf+"', 0, 0, 0, '0', '"+jsrq+"', '"+czyks+"', '收费结算' ) ";

	return sql;
	}
	
	/**
	 * 更新业务序号表中结算记录ID
	 * @param xh1
	 * @param ssrq
	 * @return
	 */
	public boolean updatejsjl(String xh1, String ssrq,Statement stmt ){
		boolean ok=false;
		String sql=" update ghb_ywxhb Set xh ='" + xh1 + "' , ssrq ='" + ssrq + "' Where xhlx ='jsjlid' commit tran ";
		try {
			ok=stmt.execute(sql);
			//stmt2.close();								// 关闭连接状态对象
			//conn2.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(ok=false){
			 System.out.print("更新业务序号表1失败");
		 }
		  return ok;
	}
	/**
	 * 更新门诊病人费用表
	 * @param ywckbm
	 * @param czybm
	 * @param sfrq
	 * @param sfjs
	 * @param jscs
	 * @param ryjsjlid
	 * @param sflx
	 * @param ryghxh
	 * @return
	 */
	private  String   updatemzbbrfy(String ywckbm,String czybm,String sfrq,String sfjs,String jscs,String ryjsjlid,String sflx,String ryghxh ) {
		String sql="UPDATE mzb_brfy SET ywckbm = '"+ywckbm+"',  czybm = '"+czybm+"', sfrq = '"+sfrq+"', sfjs = '"+sfjs+"', jscs ='"+jscs+"', ryjsjlid = '"+ryjsjlid+"',sflx = '"+sflx+"' WHERE  ryghxh = '"+ryghxh+"' and sfjs = 0 AND jscs = 0 AND ryjsjlid IS NULL";
	return sql;
	}
	/*更新yfb_ypcf
	 *  @param cfh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
	private String updateyfbypcf(String cfh,String kfry,String kfrq){
		String sql="update yfb_ypcf Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where cfh ='"+cfh+"' And kfbz ='0' And zfbz =0 ";
		return sql;
	}
	/**
	 * 更新检查申请表
	 * @param jcsqh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
   
	private String updatejcsq(String jcsqh,String kfry,String kfrq){
		String sql="update mzys_jcsq Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where jcsqh ='"+jcsqh+"' And kfbz ='0' ";
		return sql;
	}
	/**
	 * 更新检验申请表
	 * @param jcsqh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
	private String updatejysq(String jysqxh,String kfry,String kfrq){
		String sql="update mzys_jysq Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where jysqh ='"+jysqxh+"' And kfbz ='0' ";
		return sql;
	}
	/**
	 * 更新治疗处置号
	 * @param zlczh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
	private String updatezlcz(String zlczh,String kfry,String kfrq){
		String sql="update mzys_zlcz Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where zlczh ='"+zlczh+"' And kfbz ='0' And zfbz =0 ";
		return sql;
	}
	/**
	 * 更新门诊手术
	 * @param mzssh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
	private String updatemzss(String mzssh,String kfry,String kfrq){
		String sql="update mzys_mzss Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where mzssh ='"+mzssh+"' And kfbz ='0' And zfbz =0 ";
		return sql;
	}
	/**
	 * 更新医疗医嘱号
	 * @param ylyzh
	 * @param kfry
	 * @param kfrq
	 * @return
	 */
	private String updateylyz(String ylyzh,String kfry,String kfrq){
		String sql="update mzys_ylyz Set kfbz ='1' , kfry ='"+kfry+"' , kfrq ='"+kfrq+"' Where ylyzh ='"+ylyzh+"' And kfbz ='0' And zfbz =0 ";
		return sql;
	}
	/**
	 * 通过 挂号序号获取yfb_ypcf中未交费未作废的处方号
	 * @param ghxh
	 * @return
	 */
	public List<String> getyfbcfh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
		///Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select RTRIM(cfh)as cfh  from yfb_ypcf where  ghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
		//	stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("cfh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}
	/**
	 * 通过挂号序号获取未交费未作废的检查申请号
	 * @param ghxh
	 * @return
	 */
	private  List<String> getjcsqxh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
		
		String sql="select RTRIM(jcsqh)as jcsqh  from mzys_jcsq where  ryghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
	
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("jcsqh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}
	/**
	 * 通过挂号序号获取未交费未作废的检验申请序号
	 * @param ghxh
	 * @return
	 */
	private  List<String> getjysqh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
		////Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select RTRIM(jysqh)as jysqh  from mzys_jysq where  ryghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("jysqh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}
	/**
	 * 通过挂号序号获取未交费未作废的治疗处置号
	 * @param ghxh
	 * @return
	 */
	private  List<String> getzlczh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
	//	Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select RTRIM(zlczh)as zlczh  from mzys_zlcz where  ryghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("zlczh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}
	/**
	 * 通过挂号序号获取未交费未作废的门诊手术号
	 * @param ghxh
	 * @return
	 */
	private  List<String> getmzssh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
		//Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select RTRIM(mzssh)as mzssh  from mzys_mzss where  ryghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("mzssh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}	
	/**
	 * 通过挂号序号获取未交费未作废的医疗医嘱序号
	 * @param ghxh
	 * @return
	 */
	private  List<String> getmzylyzh(String ghxh,Statement stmt){
		List<String> list=new ArrayList<String>();
		//Connection conn = JDBC.getConnection();	
		//Statement stmt;
		String sql="select RTRIM(ylyzh)as ylyzh from mzys_ylyz where  ryghxh='"+ghxh+"' and kfbz='0' and zfbz='0'";
		try {
			//stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("ylyzh"));
			}
			//stmt.close();								// 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		}	
	
	
}
