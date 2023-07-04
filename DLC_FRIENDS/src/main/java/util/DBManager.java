package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		
		String url = "jdbc:mysql://database-1.cbctget0hufv.ap-northeast-2.rds.amazonaws.com:3306/DLC_FRIENDS?serverTimezone=UTC";
		String username = "admin";
		String password = "802N6UyLQBa";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("database 연동 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("database 연동 실패");
		}
		
		return conn;
	}
}