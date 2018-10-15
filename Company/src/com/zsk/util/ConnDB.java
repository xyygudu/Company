package com.zsk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Statement 接口提供了三种执行 SQL 语句的方法：executeQuery、executeUpdate 和 execute。
//使用哪一个方法由 SQL 语句所产生的内容决定。 方法executeQuery 用于产生单个结果集的语句，例如 SELECT 语句。
//被使用最多的执行 SQL 语句的方法是 executeQuery。这个方法被用来执行 SELECT 语句，它几乎是使用最多的 SQL 语句。 
//方法executeUpdate 用于执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句，
//例如 CREATE TABLE 和 DROP TABLE。INSERT、UPDATE 或 DELETE 语句的效果是修改表中零行或多行中的一列或多列。
//executeUpdate 的返回值是一个整数，指示受影响的行数（即更新条数）

//连接数据库所用的工具类
public class ConnDB {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//链接数据库（）的url
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Company;";
	
	//String sql = "select * from user_table";
	
	
	public ConnDB() {
		
	}
	public static Connection getConnection() {
		try {
            //加载sqlserver的驱动类
            Class.forName(driver);
            //获取数据库连接
            return DriverManager.getConnection(url,"sa", "456759");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("链接数据库失败");
            return null;
        }
	}
	/**
	 * 执行查询，返回结果集 ，使用next方法，迭代结果集，使用getXXX方法，获得每条记录的字段值 
	 * @param sql 要执行的sql语句
	 * @return
	 */
	public ResultSet executeQuery(String sql)throws Exception {
		try { //链接数据库然后执行sql语句
			conn = getConnection(); 
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);//执行sql语句
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); 
		}
		return rs; 
		
	}
	
	/**
	 * 更新操作
	 * @param sql
	 * @return 更新条数
	 * @throws Exception
	 */
	public int executeUpdate(String sql) throws Exception {
		int result = 0; //
		try { 
			conn = getConnection(); // 先进行链接
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql); 
		} catch (SQLException ex) {
			result = 0; 
		}
		return result; 
	}

	//关闭链接
	public void close() {
		try {
			if (rs != null) { 
				rs.close(); 
			}
			if (stmt != null) { 
				stmt.close(); 
			}
			if (conn != null) {
				conn.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
