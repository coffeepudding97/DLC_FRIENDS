package model.post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import model.user.User;
import util.DBManager;

public class PostDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private PostDao() {}
	private static PostDao instance = new PostDao();
	public static PostDao getInstance() {
		return instance;
	}
	
	public boolean createPost(PostRequestDto postDto) {
		String title = postDto.getTitle();
		String user_id = postDto.getUser_id();
		String gameTitle = postDto.getGameTitle();
		int recruitMax = postDto.getRecruitMax();
		Timestamp meetTime = postDto.getMeetTime();
		Timestamp leaveTime = postDto.getLeaveTime();
		String content = postDto.getContent();
		
		boolean check = true;
		
		if(title!=null && gameTitle!=null && recruitMax>1 && meetTime!=null && leaveTime!=null) {
			this.conn = DBManager.getConnection();
			if(this.conn!=null) {
				if(!content.equals("")) {
					String sql = "insert into post(title, user_id, content, game_title, recruitment_max, meet_time, leave_time) values(?, ?, ?, ?, ?, ?, ?)";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, title);
						this.pstmt.setString(2, user_id);
						this.pstmt.setString(3, content);
						this.pstmt.setString(4, gameTitle);
						this.pstmt.setInt(5, recruitMax);
						this.pstmt.setTimestamp(6, meetTime);
						this.pstmt.setTimestamp(7, leaveTime);
						
						this.pstmt.execute();
					} catch (Exception e) {
						e.printStackTrace();
						check = false;
					} finally {
						DBManager.close(conn, pstmt);
					}
				} else {
					String sql = "insert into post(title, user_id, game_title, recruitment_max, meet_time, leave_time) values(?, ?, ?, ?, ?, ?)";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, title);
						this.pstmt.setString(2, user_id);
						this.pstmt.setString(3, gameTitle);
						this.pstmt.setInt(4, recruitMax);
						this.pstmt.setTimestamp(5, meetTime);
						this.pstmt.setTimestamp(6, leaveTime);
						
						this.pstmt.execute();
					} catch (Exception e) {
						e.printStackTrace();
						check = false;
					} finally {
						DBManager.close(conn, pstmt);
					}
				}
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public Post getPostByPostNo(int postNo) {
		Post post = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT * FROM post WHERE post_no=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					String userId = this.rs.getString(2);
					String title = this.rs.getString(3);
					String gameTitle = this.rs.getString(5);
					int recruitMax = this.rs.getInt(6);
					Timestamp createdTime = this.rs.getTimestamp(7);
					Timestamp meetTime = this.rs.getTimestamp(8);
					Timestamp leaveTime = this.rs.getTimestamp(9);
					String content = this.rs.getString(4);
					int viewCount = this.rs.getInt(10);
					
					post = new Post(postNo, userId, title, gameTitle, recruitMax, createdTime,
							meetTime, leaveTime, content, viewCount);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		
		return post;
	}
}
