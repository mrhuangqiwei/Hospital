package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import bean.YspbBean;
import bean.YspbfaBean;
import bean.ysbcbean;

public class Ysbcsql {
/**
 * 获取医生排班方案
 * @param ksbm
 * @return
 */
	public String yspb(String ksbm){
		List<YspbBean> json1=Getyspbsw(ksbm);
		List<YspbBean> json2=Getyspbxw(ksbm);
		YspbfaBean yspbfaBean=new YspbfaBean(json1, json2);
		String json=JSON.toJSONString(yspbfaBean);
		return json;
		
	}
	
	
	/**
	 * 医生排班上午
	 * @param ksbm
	 * @return
	 */
	public List<YspbBean>  Getyspbsw(String ksbm){
	List<String> list1 = new ArrayList<String>(); List<String> list2 =new ArrayList<String>(); List<String> list3 =new ArrayList<String>();
    List<String> list4 = new ArrayList<String>(); List<String> list5 = new ArrayList<String>(); List<String> list6 = new ArrayList<String>(); List<String> list7 =new ArrayList<String>();
    list1 = getyspb(0, ksbm); list2 = getyspb(1, ksbm); list3 = getyspb(2, ksbm); list4 = getyspb(3, ksbm); list5 = getyspb(4, ksbm); list6 = getyspb(5, ksbm);
    list7 = getyspb(6, ksbm);
	Map<String, String>map=new HashMap<String, String>();
	//获取操作员挂号费用
	map=JdbcUtilSql.Getczyghfy();
    int[] arry = { list1.size(), list2.size(), list3.size(), list4.size(), list5.size(), list6.size(), list7.size() };
    int max = arry[0];
    for (int i = 0; i < arry.length; i++)
    {
        if (arry[i] > max)
            max = arry[i];
    }
    String tc = "";
    leather(list1, tc, max); leather(list2, tc, max); leather(list3, tc, max); leather(list4, tc, max); leather(list5, tc, max); leather(list6, tc, max); leather(list7, tc, max);
    List<YspbBean>lBeans=new ArrayList<YspbBean>();
    for (int i = 0; i < max - 17; i = i + 19)
    {   String ghfy1,ghfy2,ghfy3,ghfy4,ghfy5,ghfy6,ghfy7;
      if(map.containsKey(list1.get(i+5))){ghfy1=map.get(list1.get(i+5));}else{ghfy1="";}
      if(map.containsKey(list2.get(i+5))){ghfy2=map.get(list2.get(i+5));}else{ghfy2="";}
      if(map.containsKey(list3.get(i+5))){ghfy3=map.get(list3.get(i+5));}else{ghfy3="";}
      if(map.containsKey(list4.get(i+5))){ghfy4=map.get(list4.get(i+5));}else{ghfy4="";}
      if(map.containsKey(list5.get(i+5))){ghfy5=map.get(list5.get(i+5));}else{ghfy5="";}
      if(map.containsKey(list6.get(i+5))){ghfy6=map.get(list6.get(i+5));}else{ghfy6="";}
      if(map.containsKey(list7.get(i+5))){ghfy7=map.get(list7.get(i+5));}else{ghfy7="";}
    	YspbBean yspbBean=new YspbBean(list1.get(i),list1.get(i+1),list1.get(i+2),list1.get(i+3),list1.get(i+4),list1.get(i+5),list1.get(i+6),list1.get(i+7),list1.get(i+8),list1.get(i+9),list1.get(i+10),list1.get(i+11),list1.get(i+12),list1.get(i+13),list1.get(i+14),list1.get(i+15),list1.get(i+16),list1.get(i+17),list1.get(i+18),ghfy1);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4),list2.get(i+5),list2.get(i+6),list2.get(i+7),list2.get(i+8),list2.get(i+9),list2.get(i+10),list2.get(i+11),list2.get(i+12),list2.get(i+13),list2.get(i+14),list2.get(i+15),list2.get(i+16),list2.get(i+17),list2.get(i+18),ghfy2);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list3.get(i),list3.get(i+1),list3.get(i+2),list3.get(i+3),list3.get(i+4),list3.get(i+5),list3.get(i+6),list3.get(i+7),list3.get(i+8),list3.get(i+9),list3.get(i+10),list3.get(i+11),list3.get(i+12),list3.get(i+13),list3.get(i+14),list3.get(i+15),list3.get(i+16),list3.get(i+17),list3.get(i+18),ghfy3);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list4.get(i),list4.get(i+1),list4.get(i+2),list4.get(i+3),list4.get(i+4),list4.get(i+5),list4.get(i+6),list4.get(i+7),list4.get(i+8),list4.get(i+9),list4.get(i+10),list4.get(i+11),list4.get(i+12),list4.get(i+13),list4.get(i+14),list4.get(i+15),list4.get(i+16),list4.get(i+17),list4.get(i+18),ghfy4);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list5.get(i),list5.get(i+1),list5.get(i+2),list5.get(i+3),list5.get(i+4),list5.get(i+5),list5.get(i+6),list5.get(i+7),list5.get(i+8),list5.get(i+9),list5.get(i+10),list5.get(i+11),list5.get(i+12),list5.get(i+13),list5.get(i+14),list5.get(i+15),list5.get(i+16),list5.get(i+17),list5.get(i+18),ghfy5);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list6.get(i),list6.get(i+1),list6.get(i+2),list6.get(i+3),list6.get(i+4),list6.get(i+5),list6.get(i+6),list6.get(i+7),list6.get(i+8),list6.get(i+9),list6.get(i+10),list6.get(i+11),list6.get(i+12),list6.get(i+13),list6.get(i+14),list6.get(i+15),list6.get(i+16),list6.get(i+17),list6.get(i+18),ghfy6);
    lBeans.add(yspbBean);
    yspbBean=new YspbBean(list7.get(i),list7.get(i+1),list7.get(i+2),list7.get(i+3),list7.get(i+4),list7.get(i+5),list7.get(i+6),list7.get(i+7),list7.get(i+8),list7.get(i+9),list7.get(i+10),list7.get(i+11),list7.get(i+12),list7.get(i+13),list7.get(i+14),list7.get(i+15),list7.get(i+16),list7.get(i+17),list7.get(i+18),ghfy7);
    lBeans.add(yspbBean);
    }
    
    
   // String json=JSON.toJSONString(lBeans);
    return lBeans;

	}
	/****
	 * 医生排班下午
	 * @param ksbm
	 * @return
	 */
	public List<YspbBean>  Getyspbxw(String ksbm){
		List<String> list1 = new ArrayList<String>(); List<String> list2 =new ArrayList<String>(); List<String> list3 =new ArrayList<String>();
	    List<String> list4 = new ArrayList<String>(); List<String> list5 = new ArrayList<String>(); List<String> list6 = new ArrayList<String>(); List<String> list7 =new ArrayList<String>();
	    list1 = getyspb(0, ksbm); list2 = getyspb(1, ksbm); list3 = getyspb(2, ksbm); list4 = getyspb(3, ksbm); list5 = getyspb(4, ksbm); list6 = getyspb(5, ksbm);
	    list7 = getyspb(6, ksbm);
		Map<String, String>map=new HashMap<String, String>();
		//获取操作员挂号费用
		map=JdbcUtilSql.Getczyghfy();
	    int[] arry = { list1.size(), list2.size(), list3.size(), list4.size(), list5.size(), list6.size(), list7.size() };
	    int max = arry[0];
	    for (int i = 0; i < arry.length; i++)
	    {
	        if (arry[i] > max)
	            max = arry[i];
	    }
	    String tc = "";
	    leather(list1, tc, max); leather(list2, tc, max); leather(list3, tc, max); leather(list4, tc, max); leather(list5, tc, max); leather(list6, tc, max); leather(list7, tc, max);
	    List<YspbBean>lBeans=new ArrayList<YspbBean>();
	    for (int i = 0; i < max - 17; i = i + 19)
	    {   String ghfy1,ghfy2,ghfy3,ghfy4,ghfy5,ghfy6,ghfy7;
	      if(map.containsKey(list1.get(i+5))){ghfy1=map.get(list1.get(i+5));}else{ghfy1="";}
	      if(map.containsKey(list2.get(i+5))){ghfy2=map.get(list2.get(i+5));}else{ghfy2="";}
	      if(map.containsKey(list3.get(i+5))){ghfy3=map.get(list3.get(i+5));}else{ghfy3="";}
	      if(map.containsKey(list4.get(i+5))){ghfy4=map.get(list4.get(i+5));}else{ghfy4="";}
	      if(map.containsKey(list5.get(i+5))){ghfy5=map.get(list5.get(i+5));}else{ghfy5="";}
	      if(map.containsKey(list6.get(i+5))){ghfy6=map.get(list6.get(i+5));}else{ghfy6="";}
	      if(map.containsKey(list7.get(i+5))){ghfy7=map.get(list7.get(i+5));}else{ghfy7="";}
	    	
	    	YspbBean yspbBean=new YspbBean(list1.get(i),list1.get(i+1),list1.get(i+2),list1.get(i+3),list1.get(i+4),list1.get(i+5),list1.get(i+6),list1.get(i+7),list1.get(i+8),list1.get(i+9),list1.get(i+10),list1.get(i+11),list1.get(i+12),list1.get(i+13),list1.get(i+14),list1.get(i+15),list1.get(i+16),list1.get(i+17),list1.get(i+18),ghfy1);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list2.get(i),list2.get(i+1),list2.get(i+2),list2.get(i+3),list2.get(i+4),list2.get(i+5),list2.get(i+6),list2.get(i+7),list2.get(i+8),list2.get(i+9),list2.get(i+10),list2.get(i+11),list2.get(i+12),list2.get(i+13),list2.get(i+14),list2.get(i+15),list2.get(i+16),list2.get(i+17),list2.get(i+18),ghfy2);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list3.get(i),list3.get(i+1),list3.get(i+2),list3.get(i+3),list3.get(i+4),list3.get(i+5),list3.get(i+6),list3.get(i+7),list3.get(i+8),list3.get(i+9),list3.get(i+10),list3.get(i+11),list3.get(i+12),list3.get(i+13),list3.get(i+14),list3.get(i+15),list3.get(i+16),list3.get(i+17),list3.get(i+18),ghfy3);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list4.get(i),list4.get(i+1),list4.get(i+2),list4.get(i+3),list4.get(i+4),list4.get(i+5),list4.get(i+6),list4.get(i+7),list4.get(i+8),list4.get(i+9),list4.get(i+10),list4.get(i+11),list4.get(i+12),list4.get(i+13),list4.get(i+14),list4.get(i+15),list4.get(i+16),list4.get(i+17),list4.get(i+18),ghfy4);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list5.get(i),list5.get(i+1),list5.get(i+2),list5.get(i+3),list5.get(i+4),list5.get(i+5),list5.get(i+6),list5.get(i+7),list5.get(i+8),list5.get(i+9),list5.get(i+10),list5.get(i+11),list5.get(i+12),list5.get(i+13),list5.get(i+14),list5.get(i+15),list5.get(i+16),list5.get(i+17),list5.get(i+18),ghfy4);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list6.get(i),list6.get(i+1),list6.get(i+2),list6.get(i+3),list6.get(i+4),list6.get(i+5),list6.get(i+6),list6.get(i+7),list6.get(i+8),list6.get(i+9),list6.get(i+10),list6.get(i+11),list6.get(i+12),list6.get(i+13),list6.get(i+14),list6.get(i+15),list6.get(i+16),list6.get(i+17),list6.get(i+18),ghfy6);
	    lBeans.add(yspbBean);
	    yspbBean=new YspbBean(list7.get(i),list7.get(i+1),list7.get(i+2),list7.get(i+3),list7.get(i+4),list7.get(i+5),list7.get(i+6),list7.get(i+7),list7.get(i+8),list7.get(i+9),list7.get(i+10),list7.get(i+11),list7.get(i+12),list7.get(i+13),list7.get(i+14),list7.get(i+15),list7.get(i+16),list7.get(i+17),list7.get(i+18),ghfy7);
	    lBeans.add(yspbBean);
	    }
	    
	    return lBeans;
	  

		}
	
	
	  //填充为空的数据
    private void leather(List<String> arr, String str, int k)
    {
        if (arr.size() < k)
        {
            for (int i = arr.size(); i < k; i++)
            {
                arr.add(str);
            }
        }

    }
	public List<String> getyspb( int k,String ksbm){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select  Yzrq,sbsj,xbsj, xhzs,xyzs,czybm,czyxm,czyjj,(convert(int,xyzs)-convert(int,yyys))as kyzs,zcmc,xq,mzsbdd,ksmc , ksbm, (select CONVERT(varchar(30),GETDATE(),120)+':'+DATENAME(MILLISECOND,GETDATE()))as yydjsj,(select CONVERT(varchar(10),GETDATE()+" + k + ",120)+' 07:00:00.000')as yyghsj,(select CONVERT(varchar(30),GETDATE()+1+" + k + ",120)+':'+DATENAME(MILLISECOND,GETDATE())) as yyyxsj, yghs,yyys from v_yspb where Yzrq>=(select CONVERT(varchar(10),GETDATE()+" + k + ",120)+' 00:00:00.000')and Yzrq<(select CONVERT(varchar(10),GETDATE()+1+" + k + ",120)+' 00:00:00.000') and  sbsj>='1900-01-01 06:00:00.000' and sbsj<'1900-01-01 12:00:00.000'  and xbsj>='1900-01-01 12:00:00.000' and ksbm='"+ksbm+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("Yzrq"));
			list.add(rs.getString("sbsj"));
			list.add(rs.getString("xbsj"));
			list.add(rs.getString("xhzs"));
			list.add(rs.getString("xyzs"));
			list.add(rs.getString("czybm"));
			list.add(rs.getString("czyxm"));
			list.add(rs.getString("czyjj"));
			list.add(rs.getString("kyzs"));
			list.add(rs.getString("zcmc"));
			list.add(rs.getString("xq"));
			list.add(rs.getString("mzsbdd"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("ksbm"));
			list.add(rs.getString("yydjsj"));
			list.add(rs.getString("yyghsj"));
			list.add(rs.getString("yyyxsj"));
			list.add(rs.getString("yghs"));
			list.add(rs.getString("yyys"));
			}
			stmt.close();								// 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//获取医生排班下午
	public List<String> getyspbxw( int k,String ksbm){
		List<String> list=new ArrayList<String>();
		Connection conn = JDBC.getConnection();	
		Statement stmt;
		String sql="select  Yzrq,sbsj,xbsj, xhzs,xyzs,czybm,czyxm,czyjj,(convert(int,xyzs)-convert(int,yyys))as kyzs,zcmc,xq,mzsbdd,ksmc , ksbm, (select CONVERT(varchar(30),GETDATE(),120)+':'+DATENAME(MILLISECOND,GETDATE()))as yydjsj,(select CONVERT(varchar(10),GETDATE()+" + k + ",120)+' 14:00:00.000')as yyghsj,(select CONVERT(varchar(30),GETDATE()+1+" + k + ",120)+':'+DATENAME(MILLISECOND,GETDATE())) as yyyxsj,yghs,yyys from from v_yspb where Yzrq>=(select CONVERT(varchar(10),GETDATE()+" + k + ",120)+' 00:00:00.000')and Yzrq<(select CONVERT(varchar(10),GETDATE()+1+" + k + ",120)+' 00:00:00.000')   and xbsj>='1900-01-01 17:30:00.000' and ksbm='" + ksbm + "'";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			//循环输出每一条记录
			while(rs.next())
			{
			list.add(rs.getString("Yzrq"));
			list.add(rs.getString("sbsj"));
			list.add(rs.getString("xbsj"));
			list.add(rs.getString("xhzs"));
			list.add(rs.getString("xyzs"));
			list.add(rs.getString("czybm"));
			list.add(rs.getString("czyxm"));
			list.add(rs.getString("czyjj"));
			list.add(rs.getString("kyzs"));
			list.add(rs.getString("zcmc"));
			list.add(rs.getString("xq"));
			list.add(rs.getString("mzsbdd"));
			list.add(rs.getString("ksmc"));
			list.add(rs.getString("ksbm"));
			list.add(rs.getString("yydjsj"));
			list.add(rs.getString("yyghsj"));
			list.add(rs.getString("yyyxsj"));
			list.add(rs.getString("yghs"));
			list.add(rs.getString("yyys"));
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
