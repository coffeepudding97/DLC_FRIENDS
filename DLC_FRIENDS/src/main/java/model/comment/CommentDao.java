package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.post.Post;
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
	
	public boolean createComment(CommentRequestDto cmtDto, int postNo) {
		String userId = cmtDto.getUserId();
		String content = cmtDto.getContent();
		
		boolean check = true;
		
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
				check = false;
			} finally {
				DBManager.close(conn, pstmt);
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public boolean createReply(CommentRequestDto cmtDto, int postNo) {
		String userId = cmtDto.getUserId();
		String content = cmtDto.getContent();
		int rpNo = cmtDto.getRpNo();
		
		boolean check = true;
		
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
				check = false;
			} finally {
				DBManager.close(conn, pstmt);
			}
		}else {
			check = false;
		}
		return check;
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
	
	public boolean deleteCommentsByPostNo(int postNo) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn!=null) {
			String sql = "DELETE FROM comment WHERE post_no=?";
			
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public void deleteAllCommentByPostNo(int postNo) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "DELETE FROM comment WHERE post_no = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
	}
	
	public boolean deleteCommentByCmtNo(int cmtNo) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "DELETE FROM comment WHERE comment_no=?";
			String delRpSql = "DELETE FROM comment WHERE reply_no=?";
			
			try {
				this.conn.setAutoCommit(false);
				
				// 원댓글 삭제
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cmtNo);
				
				this.pstmt.execute();
				
				// 답글 삭제
				this.pstmt = this.conn.prepareStatement(delRpSql);
				this.pstmt.setInt(1, cmtNo);
				
				this.pstmt.execute();
				
				this.conn.commit();
				check = true;
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
				try {
					this.conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				DBManager.close(conn, pstmt);
			}
		} else {
			check = false;
		}
		
		return check;
	}
	public ArrayList<Comment> getCommentsByUserId(String id){
		ArrayList<Comment> commentList = new ArrayList<>();
		Comment comment = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT content, created_time FROM comment WHERE user_id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					String content = this.rs.getString(1);
					Timestamp createdTime = this.rs.getTimestamp(2);
					
					comment = new Comment(content, createdTime);
					commentList.add(comment);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return commentList;
	}
	
	public void sortCommentList(ArrayList<CommentResponseDto> list) {
		int size = list.size();
		
		for(int i=0;i<size;i++) {
			int cnt = 1;
			for(int j=0;j<size;j++) {
				if(list.get(i).getCmtNo() == list.get(j).getRpNo()) {
					list.add(i+cnt, list.get(j));
					list.remove(j+1);
					cnt++;
				}
			}
		}
	}
	
	public ArrayList<Comment> getCommentByIdAndIdx(String id, int pageNum, int sizeComments) {
		ArrayList<Comment> commentList = new ArrayList<>();
		Comment comment = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT post_no, content, created_time FROM comment WHERE user_id=? ORDER BY created_time DESC LIMIT ?,?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.pstmt.setInt(2, (pageNum-1) * sizeComments);
				this.pstmt.setInt(3, sizeComments);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int postNo = this.rs.getInt(1);
					String content = this.rs.getString(2);
					Timestamp createdTime = this.rs.getTimestamp(3);
					
					comment = new Comment(postNo, content, createdTime);
					commentList.add(comment);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return commentList;
	}
}
