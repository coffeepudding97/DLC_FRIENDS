package model.index;

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

public class IndexDao {
	
	//	 연동 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private IndexDao() {};
	private static IndexDao instance = new IndexDao();
	public static IndexDao getInstance() {
		return instance;
	}
	
	public ArrayList<PostResponseDto> getPostNo() {
		ArrayList<PostResponseDto> list = new ArrayList<PostResponseDto>();
		
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
					int postNo = this.rs.getInt(0);
					String gameTitle = this.rs.getString(4);
					String title = this.rs.getString(2);
					String userId = this.rs.getString(1);
					int recruitMax = this.rs.getInt(5);
					Timestamp createdTime = this.rs.getTimestamp(6);
					int viewCount = this.rs.getInt(10);
					
					PostResponseDto post = new PostResponseDto(postNo, gameTitle, title, userId, recruitMax, createdTime ,viewCount);
					list.add(post);
					
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
