package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.GlobalConfigUtil;
import jdbc.JDBC;
import jdbc.JdbcUtilSql;

public class platformbrxx {


	
	
	/**
	 * 获取门诊病人信息返回json
	 */
public   String brxx(String ksrq,String jsrq){
	List<String> list=new ArrayList<String>();
	list=getmzfymx(ksrq,jsrq);
	Map<String, String> gjmap = new HashMap<String, String>();
	Map<String, String> mzmap = new HashMap<String, String>();
	Map<String, String> jgmap = new HashMap<String, String>();
	Map<String, String> zwmap = new HashMap<String, String>();
	Map<String, String> zymap = new HashMap<String, String>();
	gjmap=JdbcUtilSql.gjmap();
	mzmap=JdbcUtilSql.mzmp();
	jgmap=JdbcUtilSql.jgmp();
	zwmap=JdbcUtilSql.zwmp();
	zymap=JdbcUtilSql.zymp();
	String hospitalid=GlobalConfigUtil.getHospitalid();
	String sqlString="";
	for(int i=0;i<list.size();i=i+21){
		String gjmc="",mzmc="",jgmc="",zwmc="",zymc="";
		if(gjmap.containsKey(list.get(i+7))){gjmc=gjmap.get(list.get(i+7));
		}else{gjmc="";}
		if(mzmap.containsKey(list.get(i+2))){mzmc=mzmap.get(list.get(i+2));
		}else{mzmc="";}
		if(jgmap.containsKey(list.get(i+3))){jgmc=jgmap.get(list.get(i+3));
		}else{jgmc="";}
		if(zwmap.containsKey(list.get(i+4))){zwmc=zwmap.get(list.get(i+4));
		}else{zwmc="";}
		if(zymap.containsKey(list.get(i+6))){zymc=zymap.get(list.get(i+6));
		}else{zymc="";}
		String sql="insert into zcb_zcxx (hospitalid,brid,mzmc,jgmc,zwmc,ywckbm,zymc,brxm,"
				+ "pydm,brsr,brnl,brnldw,brxx,sfzh,ylkh,jtzz,gzdw,dwdh,sj,zcrq)"
				+ "values('"+hospitalid+"','"+list.get(i+1)+"','"+mzmc+"','"+jgmc+"','"+zwmc+"','"+list.get(i)+5+"','"+zymc+"','"+list.get(i+8)+"',"
		+ "'"+list.get(i+9)+"','"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"'"
				+ ",'"+list.get(i)+"','"+list.get(i+16)+"','"+list.get(i+17)+"','"+list.get(i+18)+"','"+list.get(i+19)+"','"+list.get(i+20)+"')";
	sqlString=sqlString+sql+"\t";
			
	}
	
	return sqlString;
}	
	
	/**
 * 获取门诊病人信息
 * @param ghxh
 * @return
 */
public List<String>getmzfymx(String kssj,String jssj){
	List<String> list=new ArrayList<String>();
	Connection conn = JDBC.getConnection();	
	Statement stmt;
	String sql="select ylkh, brid ,Rtrim(mzbm)mzbm,Rtrim(jgbm)jgbm,Rtrim(zwbm)zwbm ,Rtrim(ywckbm)ywclbm,"
			+ "Rtrim(zybm)zybm,Rtrim(gjbm)gjbm,brxm,pydm,brxb,brsr,brnl,brnldw,brxx,  sfzh,jtzz,gzdw,dwdh,sj,zcrq from platform_brxx where zcrq>='"+kssj+"'and zcrq<='"+jssj+"' ";
	try {
		stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		//循环输出每一条记录
		while(rs.next())
		{list.add(rs.getString("ylkh"));
		list.add(rs.getString("brid"));
		list.add(rs.getString("mzbm"));
		list.add(rs.getString("jgbm"));
		list.add(rs.getString("zwbm"));
		list.add(rs.getString("ywclbm"));
		list.add(rs.getString("zybm"));
		list.add(rs.getString("gjbm"));
		list.add(rs.getString("brxm"));
		list.add(rs.getString("pydm"));
		list.add(rs.getString("brxb"));
		list.add(rs.getString("brsr"));
		list.add(rs.getString("brnl"));
		list.add(rs.getString("brnldw"));
		list.add(rs.getString("brxx"));
		list.add(rs.getString("sfzh"));
		list.add(rs.getString("jtzz"));
		list.add(rs.getString("gzdw"));
		list.add(rs.getString("dwdh"));
		list.add(rs.getString("sj"));
		list.add(rs.getString("zcrq"));
		
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
