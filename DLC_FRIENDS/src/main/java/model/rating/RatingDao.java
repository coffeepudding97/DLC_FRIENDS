package model.rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.party.Party;
import util.DBManager;

public class RatingDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private RatingDao() {}
	private static RatingDao instance = new RatingDao();
	public static RatingDao getInstance() {
		return instance;
	}
	
	public void createRating(int postNo, String rater, String rated) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "INSERT INTO rating(post_no, rater, rated) VALUES(?, ?, ?)";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, rater);
				this.pstmt.setString(3, rated);
				
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	public void deleteRating(int postNo, String rater) {
		this.conn = DBManager.getConnection();
		
		if(this.conn!= null) {
			String sql = "DELETE FROM rating WHERE post_no=? AND rater=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, rater);
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public void updateRating(RatingDto ratingDto) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "UPDATE rating SET content=?, score=?, curse=?, run=?, late=?, disturb=?, hack=?, finish=true";
			
			String content = ratingDto.getContent();
			int score = ratingDto.getScore();
			int curse = ratingDto.getCurse();
			int run = ratingDto.getRun();
			int late = ratingDto.getLate();
			int disturb = ratingDto.getDisturb();
			int hack = ratingDto.getHack();
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, content);
				this.pstmt.setInt(2, score);
				this.pstmt.setInt(3, curse);
				this.pstmt.setInt(4, run);
				this.pstmt.setInt(5, late);
				this.pstmt.setInt(6, disturb);
				this.pstmt.setInt(7, hack);
				
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public ArrayList<Rating> getRatingByPostNoAndUserId(int postNo, String rater) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT * FROM rating WHERE post_no=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setInt(1, postNo);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					String rated = this.rs.getString("user_id");
					if(!rater.equals(rated)) {
						String content = this.rs.getString("content");
						int score = this.rs.getInt("score");
						int curse = this.rs.getInt("curse");
						int run = this.rs.getInt("run");
						int late = this.rs.getInt("late");
						int disturb = this.rs.getInt("disturb");
						int hack = this.rs.getInt("hack");
						boolean finish = this.rs.getBoolean("finish");
						
						Rating rating = new Rating(postNo, rated, content, score, curse, run, late, disturb, hack, finish);
						
						list.add(rating);
					}
				}
						
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return list;
	}
}
