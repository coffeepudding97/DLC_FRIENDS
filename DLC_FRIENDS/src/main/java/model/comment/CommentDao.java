package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private CommentDao() {}
	private static CommentDao instance = new CommentDao();
	public static CommentDao getInstance() {
		return instance;
	}
	
	public void createComment() {
		
	}
}
