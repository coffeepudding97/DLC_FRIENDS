package model.post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	
	public int createPost(PostRequestDto postDto) {
		String title = postDto.getTitle();
		String user_id = postDto.getUser_id();
		String gameTitle = postDto.getGameTitle();
		int recruitMax = postDto.getRecruitMax();
		Timestamp meetTime = postDto.getMeetTime();
		Timestamp leaveTime = postDto.getLeaveTime();
		String content = postDto.getContent();
		
		int check = 0;
		
		if(title!=null && gameTitle!=null && recruitMax>1 && meetTime!=null && leaveTime!=null) {
			this.conn = DBManager.getConnection();
			if(this.conn!=null) {
				if(!content.equals("")) {
					String sql = "insert into post(title, user_id, content, game_title, recruitment_max, meet_time, leave_time) values(?, ?, ?, ?, ?, ?, ?)";
					
					try {
						// Statement.RETURN_GENERATED_KEYS로 insert후 id 반환받기
						this.pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						//this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, title);
						this.pstmt.setString(2, user_id);
						this.pstmt.setString(3, content);
						this.pstmt.setString(4, gameTitle);
						this.pstmt.setInt(5, recruitMax);
						this.pstmt.setTimestamp(6, meetTime);
						this.pstmt.setTimestamp(7, leaveTime);
						
						this.pstmt.execute();
						// id 반환
						this.rs = this.pstmt.getGeneratedKeys();
						if (this.rs.next()) {
							check = this.rs.getInt(1);
						    System.out.println("postNo : " + check);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						//DBManager.close(conn, pstmt);
						DBManager.close(conn, pstmt, rs);
					}
				} else {
					String sql = "insert into post(title, user_id, game_title, recruitment_max, meet_time, leave_time) values(?, ?, ?, ?, ?, ?)";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						//this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, title);
						this.pstmt.setString(2, user_id);
						this.pstmt.setString(3, gameTitle);
						this.pstmt.setInt(4, recruitMax);
						this.pstmt.setTimestamp(5, meetTime);
						this.pstmt.setTimestamp(6, leaveTime);
						
						this.pstmt.execute();
						this.rs = this.pstmt.getGeneratedKeys();
						if (this.rs.next()) {
							check = this.rs.getInt(1);
						    System.out.println("postNo : " + check);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						//DBManager.close(conn, pstmt);
						DBManager.close(conn, pstmt, rs);
					}
				}
			} else {
			}
		} else {
			
		}
		
		return check;
	}
	
	public void deletePostByPostNo(int postNo) {
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "DELETE FROM post WHERE post_no = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public String getUserIdByPostNo(int postNo) {
		String userId = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT user_id FROM post WHERE post_no=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					userId = this.rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		
		return userId;
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
	
	public void increaseViewCount(int postNo) {
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT view_count FROM post WHERE post_no = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()){
					int viewCount = this.rs.getInt("view_count");
					System.out.println("조회수:" + viewCount);
					
					sql = "UPDATE post SET view_count = ? where post_no = ?";
					
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setInt(1, viewCount+1);
					this.pstmt.setInt(2, postNo);
					
					this.pstmt.execute();
				}				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
		}
	}
	
	// 선택된 프로필 유저의 id값을 받아, 그 유저가 작성한 게시글 받아오는 기능
	public ArrayList<Post> getPostByUserId(String id) {
		ArrayList<Post> postList = new ArrayList<>();
		Post post = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT title, game_title FROM post WHERE user_id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					String title = this.rs.getString(1);
					String gameTitle = this.rs.getString(2);
					
					post = new Post(title, gameTitle);
					postList.add(post);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return postList;
	}
}
