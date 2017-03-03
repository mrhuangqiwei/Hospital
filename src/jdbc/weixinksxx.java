package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import bean.ksmsBean;

public class weixinksxx {
	public String wxgetksxx(){
		List<String> list=new ArrayList<String>();
		list=getksxx();
		List<ksmsBean> ksmsBeans=new ArrayList<ksmsBean>();
		for(int i=0;i<list.size();i=i+4){
			ksmsBean lBean=new ksmsBean(list.get(i),list.get(i+1),list.get(i+2), list.get(i+3));
		ksmsBeans.add(lBean);
		}
String kString=JSON.toJSONString(ksmsBeans,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);		
		return kString;
	}
	
	/**
	 * 获取科室信息
	 * @return
	 */
	public List<String> getksxx(){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select Rtrim(ksbm)ksbm,Rtrim(ksmc)ksmc,Rtrim(isnull(ksms,''))ksms,Rtrim(isnull(ksjj,''))ksjj from gyb_ks where tybz='0' and sfghks='1'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("ksbm"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("ksms"));
			list.add(rs.getString("ksjj"));
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
