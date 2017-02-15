package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.GlobalConfigUtil;

public class JDBC {
	public  static final String DRIVERCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String URL = GlobalConfigUtil.getJdbcUrl();
     public static final String USERNAME = GlobalConfigUtil.getJdbcUSERNAME1();
	public  static final String PASSWORD = GlobalConfigUtil.getPASSWORD();
	public  static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {											// 在静态代码块中加载数据库驱动
		try {
			Class.forName(DRIVERCLASS).newInstance();	// 加载数据库驱动
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {	// 创建数据库连接的方法
		Connection conn = threadLocal.get();	// 从线程中获得数据库连接
		if (conn == null) {						// 没有可用的数据库连接
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 创建新的数据库连接
				threadLocal.set(conn);			// 将数据库连接保存到线程中
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;							// 返回数据库连接
	}

	public static boolean closeConnection() {	// 关闭数据库连接的方法
		boolean isClosed = true;				// 默认关闭成功
		Connection conn = threadLocal.get();	// 从线程中获得数据库连接
		if (conn != null) {						// 数据库连接可用
			threadLocal.set(null);				// 清空线程中的数据库连接
			try {
				conn.close();					// 关闭数据库连接
			} catch (SQLException e) {
				isClosed = false;				// 关闭失败
				e.printStackTrace();
			}
		}
		return isClosed;						// 返回关闭结果
	}

}

