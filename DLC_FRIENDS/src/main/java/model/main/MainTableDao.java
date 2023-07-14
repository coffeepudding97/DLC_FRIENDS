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

public class MainTableDao {
	
	//	 연동 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MainTableDao() {};
	private static MainTableDao instance = new MainTableDao();
	public static MainTableDao getInstance() {
		return instance;
	}
	
	//게시판 불러오기 -> list return
	public ArrayList<MainTable> getPostAll() {
		ArrayList<MainTable> list = new ArrayList<MainTable>();
		
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
					
					MainTable mt= new MainTable(postNo, gameTitle, title, userId, recruitMax, createdTime, viewCount);
					
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
	
	public ArrayList<MainTable> getTenPosts(int pageNo) {
		ArrayList<MainTable> list = new ArrayList<MainTable>();
		
		//데이터베이스 연동
		this.conn=DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "SELECT * FROM post ORDER BY post_no DESC LIMIT 10 OFFSET ?";
			try {
				//쿼리를 개체에 담아 날릴 준비
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, (pageNo-1)*10);
				
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
					
					MainTable mt= new MainTable(postNo, gameTitle, title, userId, recruitMax, createdTime, viewCount);
					
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
	
	public ArrayList<MainTable> searchPost(String input, String select, int pageNo) {
		ArrayList<MainTable> list = new ArrayList<MainTable>();
		String where = "";
		String like = " LIKE '%" + input + "%'";
		String or = " OR ";
		
		this.conn = DBManager.getConnection();
		
		if(select.equals("all")) {
			where = "title" + like + or + "game_title" + like + or + "content" + like;
		}else if (select.equals("title")) {
			where = "title" + like;
		}else if (select.equals("gametitle")) {
			where = "game_title" + like;
		}else if (select.equals("content")) {
			where = "content" + like;
		}else {
			return list;
		}
		
		if(this.conn != null) {
			String sql = "SELECT * FROM post WHERE " + where + " ORDER BY post_no DESC LIMIT 10 OFFSET ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, (pageNo-1)*10);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int postNo = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String title = this.rs.getString(3);
					String gameTitle = this.rs.getString(5);
					int recruitMax = this.rs.getInt(6);
					Timestamp createdTime = this.rs.getTimestamp(7);
					int viewCount = this.rs.getInt(10);
					
					MainTable mt= new MainTable(postNo, gameTitle, title, userId, recruitMax, createdTime, viewCount);
					
					list.add(mt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}
}
