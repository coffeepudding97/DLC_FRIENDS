package model.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.post.Post;
import model.post.PostResponseDto;
import util.DBManager;

public class mainTableDao {
	
	//	 연동 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private mainTableDao() {};
	private static mainTableDao instance = new mainTableDao();
	public static mainTableDao getInstance() {
		return instance;
	}
	
	//게시판 불러오기 -> list return
	public ArrayList<mainTable> getPostAll() {
		ArrayList<mainTable> list = new ArrayList<mainTable>();
		
		//데이터베이스 연동
		this.conn=DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "select * from post";
			try {
				//쿼리를 개체에 담아 날릴 준비
				this.pstmt = this.conn.prepareStatement(sql);
				
				//쿼리실행
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int postNo = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String title = this.rs.getString(3);
					String gameTitle = this.rs.getString(5);
					int recruitMax = this.rs.getInt(6);
					Timestamp createdTime = this.rs.getTimestamp(7);
					int viewCount = this.rs.getInt(10);
					
					mainTable mt= new mainTable(postNo, gameTitle, title, userId, recruitMax, createdTime, viewCount);
					
					list.add(mt);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
			
		}
		return list;
	}
	
	
	
	
	
}
