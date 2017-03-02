package platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GlobalConfigUtil;
import jdbc.JDBC;

public class platform_lis_jymx {
	/**
	 * 平台获取检验明细
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	public String platformjymx(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		list=getjymxlist(kssj, jssj);
		String hospitalid=GlobalConfigUtil.getHospitalid();
		String sqString="";
		for(int i=0;i<list.size();i=i+21){
		String sql="insert into platform_lis_jymx(hospitalid,jyxh,lx,bah,sqrq,bz,xh,value_N,value_L,value_T,n_min,n_max,zwmc,ywmc,sjlx,dw,cklx,xsws,value_N_1,tjdw,yblx,ckz_t)"
+ " values('"+hospitalid+"','"+list.get(i)+"','"+list.get(i+1)+"','"+list.get(i+2)+"','"+list.get(i+3)+"','"+list.get(i+4)+"','"+list.get(i+5)+"','"+list.get(i+6)+"','"+list.get(i+7)+"','"+list.get(i+8)+"',"
+ "'"+list.get(i+9)+"','"+list.get(i+10)+"','"+list.get(i+11)+"','"+list.get(i+12)+"','"+list.get(i+13)+"','"+list.get(i+14)+"','"+list.get(i+15)+"','"+list.get(i+16)+"','"+list.get(i+17)+"','"+list.get(i+18)+"','"+list.get(i+19)+"','"+list.get(i+20)+"')";
		sqString=sqString+sql+"\t";
		}
		return sqString;
	}

	public List<String>getjymxlist(String kssj,String jssj){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		System.out.print(kssj +"\t"+jssj);
		Statement stmt;
		String sql="select jyxh,lx,bah ,sqrq,Rtrim(isnull(bz,''))bz ,xh ,Convert(decimal(18,2),isnull(RTRIM(value_N),''))value_N,RTRIM(isnull(value_L,''))value_L,RTRIM(isnull(value_T,''))value_T,RTRIM(n_min)n_min,RTRIM(n_max)n_max,zwmc,isnull(ywmc,'')ywmc,sjlx ,dw ,cklx,xsws,value_N_1,Rtrim(isnull(tjdw,''))tjdw ,isnull(yblx,'')yblx,isnull(ckz_t,'')ckz_t from view_his_jymx "
				+ "where  sqrq>='"+kssj+"'and sqrq<='"+jssj+"' order by xh  ,zbxm desc  ";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{list.add(rs.getString("jyxh"));
			list.add(rs.getString("lx"));
			list.add(rs.getString("bah"));
			list.add(rs.getString("sqrq"));
			list.add(rs.getString("bz"));
			list.add(rs.getString("xh"));
			list.add(rs.getString("value_N"));
			list.add(rs.getString("value_L"));
			list.add(rs.getString("value_T"));
			list.add(rs.getString("n_min"));
			list.add(rs.getString("n_max"));
			list.add(rs.getString("zwmc"));
			list.add(rs.getString("ywmc"));
			list.add(rs.getString("sjlx"));
			list.add(rs.getString("dw"));
			list.add(rs.getString("cklx"));
			list.add(rs.getString("xsws"));
			list.add(rs.getString("value_N_1"));
			list.add(rs.getString("tjdw"));
			list.add(rs.getString("yblx"));
			list.add(rs.getString("ckz_t"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(list);
		return list;
	}
}
