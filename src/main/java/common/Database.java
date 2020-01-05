package common;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class Database { 
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/vote?useUnicode=true&characterEncoding=UTF-8";
	private static final String user = "root";
	private static final String pwd = "201920";
	private static Connection conn = null;

	static {
		// 注册类
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception: com.mysql.jdbc.driver not found Exception");
			e.printStackTrace();
		}
		// 连接数据库
		try {
      conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("Exception: get mysql connection Exception");
			e.printStackTrace();
		}
	}

	// 查询操作
	public ResultSet executeQuery(String sql) {
		ResultSet result = null;
		PreparedStatement pre = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
		}catch(SQLException e) {
			System.out.println("Exception: executeQuery ==> " + sql);
			e.printStackTrace();
		}
		return result;
	}

	// 更新操作
	public int executeUpdate(String sql) {
		int result = 0;
		PreparedStatement pre = null;
		try {
			pre = conn.prepareStatement(sql);
			result = pre.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Exception: executeUpdate ==> " + sql);
			e.printStackTrace();
		}
		return result;
	}
	
	// 释放资源
	public static void free(ResultSet result,Connection connection){
		try {
			if(result!=null){  // 关闭结果集
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Exception: free result error");
			e.printStackTrace();
		}finally{
			try {
				if(connection!=null){
					connection.close(); // 关闭连接
				}
			} catch (SQLException e) {
				System.out.println("Exception: free connection error");
				e.printStackTrace();
			}
		}
	}
}