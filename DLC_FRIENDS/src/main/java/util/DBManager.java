package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		
		String url = "jdbc:mysql://database-1.cbctget0hufv.ap-northeast-2.rds.amazonaws.com:3306/DLC_FRIENDS?serverTimezone=UTC";
		String username = "admin";
		String password = "802N6UyLQBa";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/*String url = "jdbc:mysql://localhost:3306/db?serverTimeZone=UTC";
			String username = "root";
			String password = "my1234";*/
			
			conn = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("database 연동 실패");
		}
		
		return conn;
	}
	
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}