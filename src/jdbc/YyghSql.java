package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.sword.lang.DateTime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import bean.yyghBean;
import utils.ConvertTime;
import utils.GlobalConfigUtil;

public class YyghSql {
	//Dao dao = Dao.getInstance();

	public  String  appointment(String yyghrq, String brxm, String brxb,
			String brnl, String sfzh, String jtzz, String sj, String ysbm,
			String yyks, String yydjrq, String yyyxrq, String ylkh, String yyys)

	{  // 挂号员编码
		String ghybm = GlobalConfigUtil.getGhybm();
		// 挂号员科室
		String ghyks = GlobalConfigUtil.getGhyks();
		// 挂号方式编码
		String ghfsbm =GlobalConfigUtil.getGhfsbm();
		// 业务窗口编码
		String ywckbm = GlobalConfigUtil.getYwckbm();

		//挂号序号
		String ghxh="";
		//预约挂号ID
		String yyid="";

		// 当预约挂号日期等于当天的日期时。
		if (ConvertTime.datesdiferrent(yyghrq)) {
			// 当日挂号
			System.out.print("dajjjjjjjjjjjj\t");
			ghxh=Yyghdrgh(yyghrq, brxm, brxb, brnl, sfzh, jtzz, sj, ysbm, yyks,
					yydjrq, yyyxrq, ylkh, yyys, ghfsbm, ghybm, ghyks, ywckbm);
		yyghBean yyghBean=new yyghBean(ghxh, yyid);
		String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
			return json;
		}else { 
		
		
		
		
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt ;
		String sql = "   SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='yyghid'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
			}


			boolean ok = false;
			// 获取医嘱序号表的YZRQ 来更新YZHB
			String Yzrq = "";
			Yzrq = ConvertTime.converttimetoYYMMDDHH00(yyghrq);
			String yy = "";
			//List<String> list = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			// 获取yyghid 的xhlx , xh , ssrq , cslx
			//list = getywxhidyy();
			// 获取brid 的 xhlx , xh , ssrq , cslx
			list2 = getusertime(stmt);
			// 当前时间转化为20170213000000格式即后面需要的流水号
			Long yyid1 = Long.parseLong(getyyghid(stmt).trim());
			// 获取预约挂号id的末尾号数
			int m = Integer.parseInt(list.get(1).trim()) + 1;
			String xh1 = "";
			// 生成00001样式的字符串用来更新yygh的xh
			xh1 = getywxuxhyyid(m,stmt);

			String s = list2.get(1);
			// 获取brzc的尾号
			int k2 = Integer.parseInt(s.trim()) + 1;
			// 生成00001样式的字符串用来更新brzc的的xh
			String bridxh = getywxuxhyyid(k2,stmt);
			String hu = list.get(2);
			// 获取预约挂号时间和当前时间的差
			int k = ConvertTime.daysBetween(list.get(2));
			String xh = "000001";
			// 获取当前时间
			String ssrq =ConvertTime.Getdnowdatexx();
			// 获取注册日期和当前日期的差
			int k1 = Integer.parseInt(list2.get(1).trim()) + 1;
			// 注册日期，获取系统当前
			String zcrq =ConvertTime.Getdnowdatexx();

			// 当预约挂号日期不等于当天日期时候
			// 判断该病人是否注册当病人没有注册时
			String sqlString="BEGIN TRAN"+"\t";
				if (JdbcUtilSql.getuserghbrxx(sfzh, ylkh,stmt) == null) {
					System.out.print("病人没有注册，运行没有注册的代码");
					// 当挂哈日期和当前日期的差大于等于1时
					
					if (k >= 1) {
					    	ok = updateYwxhb1(xh, ssrq,stmt);
						 System.out.print("大于1"+ok);
						Long f = Long.parseLong(getyyghid(stmt).trim()) + 1;
					    yyid = String.valueOf(f);
						String insetghbString= Insertyygh(yyid, ywckbm, ghybm, yyghrq, brxm, brxb,
								"", brnl, "1", sfzh, jtzz, sj, ysbm, yyks, yydjrq,
								yyyxrq, ghyks, ylkh);
						sqlString =insetghbString+"\t";
						sqlString=updateYzxhb(ysbm, Yzrq, yyys)+"\t";
						// System.out.print("大于1插入"+ok);
						//获取当前时间与输入时间的差
						int j = ConvertTime.daysBetween(list2.get(2));
						if (j >= 1) {
							ok = updateYwxhb2("000001", ssrq,stmt);
							// / System.out.print("大于1更新2"+ok);
							Long q = yyid1 + 1;
							String brid = String.valueOf(q);

							String brzcString= Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj,
									zcrq, ywckbm, ghybm);
						    sqlString=sqlString+brzcString+"\t";
							// System.out.print("大于1插入注册"+ok);
							 sqlString=sqlString+ updateyyghbrid(brid, yyid)+"\t";
							 sqlString=sqlString+" COMMIT TRAN"; 
							 System.out.print(sqlString);
							 stmt.execute(sqlString);
								yyghBean yyghBean=new yyghBean(ghxh, yyid);
								String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
								

								stmt.close(); // 关闭连接状态对象
								conn.commit();return json;

						} 
						//当上次预约日期小于当前日期且上次挂号日期等于当天时执行
						else {
							ok = updateYwxhb2(bridxh, ssrq,stmt);
							// System.out.print("更新222"+ok);
							Long f1 = yyid1 + k1;
							String brid = String.valueOf(f1);
							sqlString=sqlString+ Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj,
									zcrq, ywckbm, ghybm)+"\t";
							sqlString=sqlString+ updateyyghbrid(brid, yyid)+"\t";
							 sqlString=sqlString+" COMMIT TRAN"; 
							 System.out.print(sqlString);
							 stmt.execute(sqlString);
yyghBean yyghBean=new yyghBean(ghxh, yyid);
String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

stmt.close(); // 关闭连接状态对象
conn.commit();
return json;

						}
					}
					//当前预约时间等于上次预约日期时
					else {
						yy = xh1;
						ok = updateYwxhb1(xh1, ssrq,stmt);
						// System.out.print("更新555"+ok);
						Long f1 = yyid1 + m;
					 yyid = String.valueOf(f1);

				      sqlString=sqlString+Insertyygh(yyid, ywckbm, ghybm, yyghrq, brxm, brxb,
								"", brnl, "1", sfzh, jtzz, sj, ysbm, yyks, yydjrq,
								yyyxrq, ghyks, ylkh)+"\t";
						int ww = ConvertTime.daysBetween(list2.get(2));;
						//当前预约时间等于上次预约日期时,上次注册时间小于当前时间时
						if (ww >= 1) {
							ok = updateYwxhb2("000001", ssrq,stmt);
							Long q = yyid1 + 1;
							String brid = String.valueOf(q);
					         sqlString=sqlString+Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj,
									zcrq, ywckbm, ghybm)+"\t";
							sqlString=sqlString+updateyyghbrid(brid, yyid)+"\t";
							 sqlString=sqlString+" COMMIT TRAN"; 
							 System.out.print(sqlString);
							 stmt.execute(sqlString);
                            yyghBean yyghBean=new yyghBean(ghxh, yyid);
                          String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
                        

              			stmt.close(); // 关闭连接状态对象
              			conn.commit();return json;
						} 
						//当预约时间与注册时间都等于当天时执行
						else {
							System.out.print("dddddddddd");
							 updateYwxhb2(bridxh, ssrq,stmt);
							Long f2 = yyid1 + k2;
							String brid = String.valueOf(f2);
						sqlString=sqlString+ Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj,
									zcrq, ywckbm, ghybm)+"\t";

						sqlString=sqlString+ updateyyghbrid(brid, yyid)+"\t";
						 sqlString=sqlString+" COMMIT TRAN"; 
						 System.out.print(sqlString);
						 stmt.execute(sqlString);
                        yyghBean yyghBean=new yyghBean(ghxh, yyid);
                      String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
                    

          			stmt.close(); // 关闭连接状态对象
          			conn.commit();return json;
						}
						
					}
				} // 当病人已经注册时
				else {
	                System.out.print("病人已经注册后不需要更新注册表");
					String brid = JdbcUtilSql.getuserghbrxx(sfzh, ylkh,stmt);
					// 当病人存在brid且yyghrq与当前日期的差大于等于1时
					if (k >= 1) {
						 updateYwxhb1(xh, ssrq,stmt);
						Long f = Long.parseLong(getyyghid(stmt).trim()) + 1;
					 yyid = String.valueOf(f);
						;
						sqlString=sqlString+ Insertyygh(yyid, ywckbm, ghybm, yyghrq, brxm, brxb,
								"", brnl, "1", sfzh, jtzz, sj, ysbm, yyks, yydjrq,
								yyyxrq, ghyks, ylkh)+"\t";
						sqlString=sqlString+  updateYzxhb(ysbm, Yzrq, yyys)+"\t";
						sqlString=sqlString+ updateyyghbrid(brid, yyid)+"\t";
						 sqlString=sqlString+" COMMIT TRAN"; 
						System.out.print(sqlString);
						 stmt.execute(sqlString);
                       yyghBean yyghBean=new yyghBean(ghxh, yyid);
                     String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
                   

         			stmt.close(); // 关闭连接状态对象
         			conn.commit();return json;
					} else {
						 updateYwxhb1(xh1, ssrq,stmt);
						Long f1 = yyid1 + m;
		                yyid = String.valueOf(f1);

		                sqlString=sqlString+  Insertyygh(yyid, ywckbm, ghybm, yyghrq, brxm, brxb,
								"", brnl, "1", sfzh, jtzz, sj, ysbm, yyks, yydjrq,
								yyyxrq, ghyks, ylkh)+"\t";
		                sqlString=sqlString+  updateyyghbrid(brid, yyid)+"\t";
		                sqlString=sqlString+" COMMIT TRAN"; 
		                System.out.print(sqlString);
						 stmt.execute(sqlString);
                      yyghBean yyghBean=new yyghBean(ghxh, yyid);
                    String json=JSON.toJSONString(yyghBean,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

        			stmt.close(); // 关闭连接状态对象
        			conn.commit();
                    return json;
					}
					
				}

				}
			
			
		catch (SQLException e) {
			return "返回失敗";
		}

		}
		

	}

	/**
	 * 为获取yyghid 的xhlx , xh , ssrq , cslx
	 * 
	 * @return
	 */
	public List<String> getywxhidyy() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='yyghid'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 为病人注册做准备 返回业务序号表中的 xhlx , xh , ssrq , cslx
	 * 
	 * @return
	 */
	public List<String> getusertime(Statement stmt) {
		List<String> list = new ArrayList<String>();
		//Connection conn = JDBC.getConnection();
		// stmt;
		String sql = "SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='brid' ";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取当前时间转化为20170213000000格式
	 * 
	 * @return
	 */
	public String getyyghid(Statement stmt ) {
		String fString = "";
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = "select CONVERT(varchar(12) , getdate(), 112 ) as yyid";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				fString = rs.getString("yyid");
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fString = fString + "000000";
		return fString;
	}

	/** 获取和生成业务序号 **/
	public String getywxuxhyyid(int xh1,Statement stmt) {

		String fString = "";
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = " declare @book_code varchar(50)set @book_code=(select max(right('000000',6)) where '000000' like '%')+"
				+ xh1
				+ " set @book_code=''+right('000000'+@book_code,6) select @book_code as id";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				fString = rs.getString("id");
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fString;

	}

	/** 获取当前时间和输入时间的差 **/
	public int getdatedifference(String date,Statement stmt) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = sdf.parse(date);

		} catch (ParseException ex) {
			System.out.print("YyghSql" + "时间转换有错误");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String tablename = dateFormat.format(date1);

		int k = 0;
		String s = "";
		//Connection conn = JDBC.getConnection();
		//Statement stmt;
		String sql = "SELECT DATEDIFF(day , '"+tablename+"',getdate()) as df ";
		try {
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				s = rs.getString("df");
			}
			//stmt.close(); // 关闭连接状态对象
			//conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(s);

	}

	/** 获取当前日期 **/
	public String getdatetime() {

		String fString = "";
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = " select (SELECT DISTINCT GetDate ( ) FROM sysfilegroups )  as time ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				fString = rs.getString("time");
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = sdf.parse(fString);

		} catch (ParseException ex) {
			System.out.print("YyghSql" + "时间转换有错误");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String tablename = dateFormat.format(date1);

		return tablename;

	}

	/** 插入预约挂号表 **/
	public String  Insertyygh(String yyghid, String ywckbm, String czybm,
			String yyghrq, String brxm, String brxb, String brsr, String brnl,
			String brnldw, String sfzh, String jtzz, String sj, String ysbm,
			String yyks, String yydjrq, String yyyxrq, String czyks, String ylkh) {
		//boolean ok = false;
		String sql = "INSERT INTO ghb_yygh ( yyghid, ywckbm, czybm, yyghrq, brxm, brxb, brsr, brnl, brnldw, sfzh, jtzz, sj,yyys, yyks, yydjrq, yyyxrq, czyks,ylkh) VALUES ( '"
				+ yyghid
				+ "', '"
				+ ywckbm
				+ "', '"
				+ czybm
				+ "', '"
				+ yyghrq
				+ "', '"
				+ brxm
				+ "', '"
				+ brxb
				+ "', '"
				+ brsr
				+ "',"
				+ brnl
				+ ", '"
				+ brnldw
				+ "', '"
				+ sfzh
				+ "', '"
				+ jtzz
				+ "', '"
				+ sj
				+ "', '"
				+ ysbm
				+ "', '"
				+ yyks
				+ "', '"
				+ yydjrq
				+ "', '"
				+ yyyxrq + "', '" + czyks + "','" + ylkh + "')";
		
		return sql;
	}

	/** 跟新业务序号1 **/
	public boolean updateYwxhb1(String xh, String ssrq,Statement stmt) {
		boolean ok = false;
		String sql = "BEGIN TRAN update ghb_ywxhb Set xh ='" + xh
				+ "' , ssrq ='" + ssrq + "' Where xhlx ='yyghid' COMMIT TRAN  ";
		try {
			ok=stmt.execute(sql);
			 if(ok==false){
				 System.out.print("更新业务序号表1失败2222222");
			 }
			//stmt1.close();								// 关闭连接状态对象
			//conn1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ok=dao.insert(sql);
		
		  return ok;
	}

	/** 跟新业务序号2 **/
	public boolean updateYwxhb2(String xh, String ssrq,Statement stmt) {
		boolean ok = false;
		String sql = " begin tran update ghb_ywxhb Set xh ='" + xh + "' , ssrq ='" + ssrq
				+ "' Where xhlx ='brid' commit tran ";
		try {
			ok=stmt.execute(sql);
			//stmt1.close();								// 关闭连接状态对象
			//conn1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ok=dao.insert(sql);
		 if(ok==false){
			 System.out.print("更新病人注册表失败");
		 }
		  return ok;
	}

	/** 插入病人注册表 **/
	public String Insertbrzc(String brid, String brxm, String brxb,
			String brnl, String sfzh, String jtzz, String sj, String zcrq,
			String ywckbm, String zcrybm) {
		boolean ok = false;

		String sql = "  INSERT into ghb_zcxx ( brid , czybm , ywckbm , brxm , pydm , brxb , brsr , brnl , brnldw , brxx , sfzh , jtzz , gzdw , dwdz , sj , email , yzbm , lxr , lxrdh , zcrq , czyks )VALUES ( '"
				+ brid
				+ "' , '"
				+ zcrybm
				+ "' , '"
				+ ywckbm
				+ "' , '"
				+ brxm
				+ "' , 'ZRT' , '"
				+ brxb
				+ "' , null , '"
				+ brnl
				+ "' , '1' , null , '"
				+ sfzh
				+ "' ,'"
				+ jtzz
				+ "' , null , null , '"
				+ sj
				+ "' , null , null , null , null , '" + zcrq + "' , '0001' ) ";
		
		return sql+"\t";
	}

	/** 更新挂号表信息 **/
	public String  updateyyghbrid(String brid, String yyghid) {
		//boolean ok = false;

		String sql = " update ghb_yygh set brid ='" + brid
				+ "' where yyghid ='" + yyghid + "' ";
		
		return sql+"\t";
	}

	/** 更新医嘱序号表的预约人数 **/
	public String  updateYzxhb(String ysbm, String yzxh, String yyys) {
		boolean ok = false;
		int k = Integer.valueOf(yyys) + 1;

		String sql = "update ghb_yzhb set yyys='" + k + "' where czybm='"
				+ ysbm + "' and Yzrq='" + yzxh + "'";
		return sql;
	}

	public List<String> GetpycfTT(String cfh) {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "select cfh,ghxh,cflxbm,yfbm,brxm,fysm,zz,zf,fyts,cyxm,ksmc,cfrq ,cfje, mzzd from view_yfb_ypcf where cfh='"
				+ cfh + "'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("cfh"));
				list.add(rs.getString("ghxh"));
				list.add(rs.getString("cflxbm"));
				list.add(rs.getString("yfbm"));
				list.add(rs.getString("brxm"));
				list.add(rs.getString("fysm"));
				list.add(rs.getString("zz"));
				list.add(rs.getString("zf"));
				list.add(rs.getString("fyts"));
				list.add(rs.getString("cyxm"));
				list.add(rs.getString("ksmc"));
				list.add(rs.getString("cfrq"));
				list.add(rs.getString("cfje"));
				list.add(rs.getString("mzzd"));
			}
			stmt.close(); // 关闭连接状态对象
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 预约挂号挂当日号。
	 * 
	 * @return
	 */
	private String Yyghdrgh(String yyghrq, String brxm, String brxb,
			String brnl, String sfzh, String jtzz, String sj, String ysbm,
			String yyks, String yydjrq, String yyyxrq, String ylkh,
			String yyys, String ghfsbm, String ghybm, String ghyks,
			String ywckbm) {
		
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "   SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='ghxh' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
			}

			// 获取挂号诊疗编码sql
			String ghxh ;
			boolean ok =false;
			Map<String, String> map = new HashMap<String, String>();
			map = JdbcUtilSql.getghzlbm(stmt);
			String ghzlbm = "";
			if (map.containsKey(ysbm.trim())) {
				ghzlbm = map.get(ysbm.trim());
			} else {
				ghzlbm = "";
			}
			
			list = JdbcUtilSql.getuserghxhtime();
			/** 获取当前时间转换成字符串格式如20170213 **/
			Long yyid1 = Long.parseLong(getyyghid(stmt).trim());
			// 获取ghxh的尾号
			int m = Integer.parseInt(list.get(1).trim()) + 1;
			// 获取上次挂号序号的日期
			String hu = list.get(2);
			// 获取上次挂号的日期和当前日期的天数差
			int k = ConvertTime.daysBetween(list.get(2));
			// 当当天第一次获取相关业务序号表时用来做序号
			String xh = "000001";
			// 获取系统当前日期
			String ssrq = ConvertTime.Getdnowdatexx();

			String xh1 = "";
			// 返回结果为 000001 这种形式 就是ghxh的尾号
			xh1 = getywxuxhyyid(m,stmt);
			// 病人ID
			String brid = null;
			String sqlString="BEGIN TRAN"+"\t";
			// 当病人未曾在系统中注册时
			if (JdbcUtilSql.getuserghbrxx(sfzh, ylkh,stmt) == null) {
				System.out.print("当病人没有注册时");
				List<String> list2 = new ArrayList<String>();
				list2 = getusertime( stmt);
				String s = list2.get(1);
				// 获取病人注册的尾号
				int k2 = Integer.parseInt(s.trim()) + 1;
				// 获取bridxh的尾号
				String bridxh = getywxuxhyyid(k2,stmt);

				// 获取上次注册日期和当前日期的天数差
				int j =  ConvertTime.daysBetween(list2.get(2));

				// 获取brid后面的尾号
				int k1 = Integer.parseInt(list2.get(1).trim()) + 1;
				// 获取系统当前日期
				String zcrq =ConvertTime.Getdnowdatexx();;
				// 当病人注册日期和上次注册日期的时间差大于1时
				if (j >= 1) {
					ok=	updateYwxhb2("000001", ssrq,stmt);
					Long q = yyid1 + 1;
					brid = String.valueOf(q);
					sqlString=sqlString+Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq,
							ywckbm, ghybm)+""
									+ "t";
					// 当病人注册日期和上次注册日期的时间差大于1并且挂 号时间和上次挂号时间的差大于1时
					if (k >= 1) {
						//System.out.print("111111");
						ok=	JdbcUtilSql.updateYwxhbghxh(xh, ssrq,stmt);
						// System.out.print("大于1"+ok);
						Long f = Long.parseLong(getyyghid(stmt).trim()) + 1;
					 ghxh = String.valueOf(f);
					 sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
								ysbm, ghyks)+"\t";
					 sqlString=sqlString+"commit tran"+"\t";
					 System.out.print(sqlString);
					 stmt.execute(sqlString);
					}
					// 当病人注册日期和上次注册日期的时间差大于1并且挂号时间和上次时间的差小于等于1时执行
					else {
						System.out.print("222222222");
						ok=	JdbcUtilSql.updateYwxhbghxh(xh1, ssrq,stmt);
						Long f1 = yyid1 + m;
					 ghxh = String.valueOf(f1);
					 sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
								ysbm, ghyks)+"\t";
					 sqlString=sqlString+"commit tran"+"\t";
					 System.out.print(sqlString);
					 stmt.execute(sqlString);
					}
				}
				// 当病人注册日期和上次注册日期的时间差小于或等于1 时
				else {
					ok=updateYwxhb2(bridxh, ssrq,stmt);
					Long f1 = yyid1 + k1;
					brid = String.valueOf(f1);
					 sqlString=sqlString+Insertbrzc(brid, brxm, brxb, brnl, sfzh, jtzz, sj, zcrq,
							ywckbm, ghybm)+"\t";
					// 当病人注册日期和上次注册日期的时间差小于或等于1并且挂 号时间和上次挂号时间的差大于1时
					if (k >= 1) {
						ok=	JdbcUtilSql.updateYwxhbghxh(xh, ssrq,stmt);
						Long f = Long.parseLong(getyyghid(stmt).trim()) + 1;
					 ghxh = String.valueOf(f);
					 sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
								ysbm, ghyks)+"\t";
					 sqlString=sqlString+"commit tran"+"\t";
					 System.out.print(sqlString);
					 stmt.execute(sqlString);
					}
					// 当病人注册日期和上次注册日期的时间差小于或等于1并且挂号时间和上次时间的差小于等于1时执行
					else {
						System.out.print("333333333");
						ok=	JdbcUtilSql.updateYwxhbghxh(xh1, ssrq,stmt);
						f1 = yyid1 + m;
						 ghxh = String.valueOf(f1);
						 sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
								ysbm, ghyks)+"\t";
						 sqlString=sqlString+"commit tran"+"\t";
						 System.out.print(sqlString);
						 stmt.execute(sqlString);
					}
				}

			}
			// 当病人已经注册时
			else {
				System.out.print("当病人已经注册时");

				brid = JdbcUtilSql.getuserghbrxx(sfzh, ylkh,stmt);
				if (k >= 1) {
					ok=JdbcUtilSql.updateYwxhbghxh(xh, ssrq,stmt);
					System.out.print("444444444");
					// System.out.print("大于1"+ok);
					Long f = Long.parseLong(getyyghid(stmt).trim()) + 1;
				  ghxh = String.valueOf(f);
					 sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
							ysbm, ghyks)+"\t";
					 sqlString=sqlString+"commit tran"+"\t";
					 System.out.print(sqlString);
					 stmt.execute(sqlString);
				} else {
					System.out.print("5555555555");
					ok=	JdbcUtilSql.updateYwxhbghxh(xh1, ssrq,stmt);
					Long f1 = yyid1 + m;
				   ghxh = String.valueOf(f1);
				   sqlString=sqlString+Insertghb(ghxh, ghzlbm, ghfsbm, brid, ghybm, yyghrq, yyks,
							ysbm, ghyks)+"\t";
				   sqlString=sqlString+"commit tran"+"\t";
				   System.out.print(sqlString);
					 stmt.execute(sqlString);
				}
			}
	    
			
	     
	      stmt.close(); // 关闭连接状态对象
			conn.commit();
		return ghxh;
		} catch (SQLException e) {
			return"挂号失败";
		}
		//return list;
		
		
		
		
		
		
		
		
	}

	/**
	 * 插入挂号序号表
	 * 
	 * @param ghxh
	 * @param ghzlbm
	 * @param ghfsbm
	 * @param brid
	 * @param czybm
	 * @param ghrq
	 * @param ghks
	 * @param jzys
	 * @param czyks
	 * @return
	 */
	private String  Insertghb(String ghxh, String ghzlbm, String ghfsbm,
			String brid, String czybm, String ghrq, String ghks, String jzys,
			String czyks) {
		String sql = "insert  into ghb_brgh (ghxh,ghzlbm,ghfsbm,brid,czybm,ghrq,ghks,jzys,czyks)values"
				+ " ('"
				+ ghxh
				+ "','"
				+ ghzlbm
				+ "','"
				+ ghfsbm
				+ "','"
				+ brid
				+ "','"
				+ czybm
				+ "','"
				+ ghrq
				+ "','"
				+ ghks
				+ "','"
				+ jzys
				+ "','" + czyks + "')";
		//boolean ok = dao.insert(sql);
		return sql;
	}
	/**
	 * 获取挂号序号时间为挂号做准备
	 * 
	 * @return
	 */
	public  List<String> getuserghxhtime() {
		List<String> list = new ArrayList<String>();
		Connection conn = JDBC.getConnection();
		Statement stmt;
		String sql = "SELECT Top 1 xhlx , xh , ssrq , cslx From ghb_ywxhb Where xhlx ='ghxh' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 循环输出每一条记录
			while (rs.next()) {
				list.add(rs.getString("xhlx"));
				list.add(rs.getString("xh"));
				list.add(rs.getString("ssrq"));
				list.add(rs.getString("cslx"));
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
