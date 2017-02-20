package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ConvertTime;
import bean.his_yycxBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class his_yycxsql {
	public  String getyymx(String ylkh,String sfzh) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql;
		if(!sfzh.trim().equals("")){
			sql = "select * from view_yygh_cx where ghrq>=  (SELECT DATEADD(day, +0,(select convert(varchar(10),GETDATE(),120)+' 00:00:00.000'))) and sfzh='"+sfzh+"'";
		}
		else{
	sql = "select * from view_yygh_cx where ghrq>=  (SELECT DATEADD(day, +0,(select convert(varchar(10),GETDATE(),120)+' 00:00:00.000'))) and ylkh='"+ylkh+"'";
		}	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("ghxh"));
				list.add(rs.getString("yyghid"));
				list.add(rs.getString("brxm"));
				list.add(rs.getString("brnl"));
				list.add(rs.getString("ksmc"));
				list.add(rs.getString("mzsbdd"));
				list.add(rs.getString("jzys"));
				list.add(rs.getString("czyxm"));
				list.add(rs.getString("brid"));
				list.add(rs.getString("ghrq"));
				list.add(rs.getString("yydjrq"));
				list.add(rs.getString("yyyxrq"));
				list.add(rs.getString("sfzh"));
				list.add(rs.getString("ylkh"));
				list.add(rs.getString("sj"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<his_yycxBean>yycxBeans=new ArrayList<his_yycxBean>();
		for (int k = 0; k < list.size(); k = k + 15) {
			String yyghrq;
			yyghrq=ConvertTime.dateToStamp(list.get(k+9));
          List<String> yssbsj=new ArrayList<String>();
          yssbsj=getyssbsj(list.get(k+6), list.get(k+9));
          his_yycxBean his_yycxBean=new his_yycxBean(list.get(k), 
list.get(k+1),list.get(k+2), list.get(k+3), list.get(k+4), list.get(k+5), list.get(k+6),
list.get(k+7), list.get(k+8), yyghrq,list.get(k+10), list.get(k+11),list.get(k+12),
list.get(k+13), list.get(k+14), yssbsj.get(0), yssbsj.get(1));
		yycxBeans.add(his_yycxBean);
		}
		String json=JSON.toJSONString(yycxBeans,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		return json;
	}

	public  List<String> getyssbsj(String ysbm, String ghrq) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select (SELECT CONVERT(VARCHAR(5),(select  sbsj from gyb_bcfa where"
				+ " bcfabm=(select top 1 bcfabm from ghb_yzhb where ghb_yzhb.Yzrq>= "
				+ "(SELECT DATEADD(day, +0,(select convert(varchar(10),'"
				+ ghrq
				+ "',120)+' 00:00:00.000')))and "
				+ "ghb_yzhb.Yzrq< (SELECT DATEADD(day, +0, (select convert(varchar(10),'"
				+ ghrq
				+ "',120)+' 23:59:00.000')))and"
				+ " ghb_yzhb.czybm='"
				+ ysbm
				+ "' )),8))as'sbsj', (SELECT CONVERT(VARCHAR(5),( select  xbsj from gyb_bcfa where "
				+ "bcfabm=(select top 1 bcfabm from ghb_yzhb where ghb_yzhb.Yzrq>= (SELECT DATEADD(day, +0"
				+ ", (select convert(varchar(10),'"
				+ ghrq
				+ "',120)+' 00:00:00.000')))and ghb_yzhb.Yzrq< (SELECT DATEADD(day, +0,"
				+ " (select convert(varchar(10),'"
				+ ghrq
				+ "',120)+' 23:59:00.000')))and ghb_yzhb.czybm='"
				+ ysbm
				+ "' "
				+ " order by bcfabm desc)),8))as'xbsj' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("sbsj"));
				list.add(rs.getString("xbsj"));

			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
