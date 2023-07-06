package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class CommentDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private CommentDao() {}
	private static CommentDao instance = new CommentDao();
	public static CommentDao getInstance() {
		return instance;
	}
	
	public void createComment(CommentRequestDto cmtDto, int postNo) {
		String userId = cmtDto.getUserId();
		String content = cmtDto.getContent();
		
		this.conn = DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "INSERT INTO comment(post_no, user_id, content) values(?, ?, ?)";
			
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, userId);
				this.pstmt.setString(3, content);
				
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public void createReply(CommentRequestDto cmtDto, int postNo) {
		String userId = cmtDto.getUserId();
		String content = cmtDto.getContent();
		int rpNo = cmtDto.getRpNo();
		
		this.conn = DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "INSERT INTO comment(post_no, user_id, content, reply_no) values(?, ?, ?, ?)";
			
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, userId);
				this.pstmt.setString(3, content);
				this.pstmt.setInt(4, rpNo);
				
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public ArrayList<CommentResponseDto> getCommentsByPostNo(int postNo) {
		ArrayList<CommentResponseDto> cmtList = new ArrayList<CommentResponseDto>();
		CommentResponseDto cmt = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT * FROM comment WHERE post_no=?";
			
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				
				this.rs = this.pstmt.executeQuery();
				
				int cmtNo = 0;
				int postNotuse = 0;
				String userId = "";
				String content = "";
				Timestamp createdTime = null;
				int rpNo = 0;
				
				while (this.rs.next()) {
					cmtNo = this.rs.getInt("comment_no");
					userId = this.rs.getString("user_id");
					content = this.rs.getString("content");
					createdTime = this.rs.getTimestamp("created_time");
					rpNo = this.rs.getInt("reply_no");
					
					cmt = new CommentResponseDto(cmtNo, userId, content, createdTime, rpNo);
					
					cmtList.add(cmt);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return cmtList;
	}
}
