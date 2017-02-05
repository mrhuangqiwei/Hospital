package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.stringtree.json.JSONValidatingWriter;

import utils.ConvertTime;
import bean.UserinfoSqlBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GetFriendInfosql {
	public String userinfotojison(String sfzh,String ylkh){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String json = null;
		String sql=null;
		if(sfzh.length()>10){
			sql="select ylkh,ylklxbm,brid,ghxh,ghrq,sfzh,brxm,brnl,brnldw,mzbm,brxb,jtzz,sj  from v_his_brjbxx2 where sfzh='"+sfzh+"'";
		}
		else{sql="select ylkh,ylklxbm,brid,ghxh,ghrq,sfzh,brxm,brnl,brnldw,mzbm,brxb,jtzz,sj from v_his_brjbxx  where ylkh='"+ylkh+"'";}
	
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("ylkh"));
			list.add(rs.getString("ylklxbm"));
			list.add(rs.getString("brid"));
			list.add(rs.getString("ghxh"));
			list.add(rs.getString("ghrq"));
			list.add(rs.getString("sfzh"));
			list.add(rs.getString("brxm"));
			list.add(rs.getString("brnl"));
			list.add(rs.getString("brnldw"));
			list.add(rs.getString("mzbm"));
			list.add(rs.getString("brxb"));
			list.add(rs.getString("jtzz"));
			list.add(rs.getString("sj"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UserinfoSqlBean> userinfoSqlBeans=new ArrayList<UserinfoSqlBean>();
	for(int i=0;i<list.size();i=i+13){
		String ghrq=ConvertTime.dateToStamp(list.get(i+4));
		UserinfoSqlBean userinfoSqlBean =new UserinfoSqlBean(list.get(i), 
list.get(i+1), list.get(i+2), list.get(i+3), ghrq, list.get(i+5),
list.get(i+6),list.get(i+7), list.get(i+8),list.get(i+9),list.get(i+10), list.get(i+11), list.get(i+12));
userinfoSqlBeans.add(userinfoSqlBean);
	}
		json=JSON.toJSONString(userinfoSqlBeans,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	
		
	
		
		
		
	
	return json;
	}
}
