package model.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Blob;

import util.DBManager;

public class ProfileDao {
	// DB 연동 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// DB 연동하여 데이터 관리
	// Dao: 싱글톤으로 구현
	private ProfileDao() {}
	
	private static ProfileDao instance = new ProfileDao();
	
	public static ProfileDao getInstance() {
		return instance;
	}
	
//	public Profile getAllProfiles() {
//		Profile profile = null;
//		
//		this.conn = DBManager.getConnection();
//		
//		if(this.conn != null) {
//			String sql = "SELECT * FROM profile";
//			try {
//				if(this.rs.next()) {
//					String id = this.rs.getString(1);
//					String nickname = this.rs.getString(2);
//					String info = this.rs.getString(3);
//					System.out.println(id + nickname + info);
//					profile = new Profile(id, nickname, info);
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					this.pstmt.close();
//					conn.close();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
//				
//		
//		return profile;
//	}
	
	
	/*
		SELECT profile.*, post.title, post.content
		FROM profile
		JOIN post ON profile.user_id = post.user_id
		WHERE profile.user_id = 'dlrbwo2023';
		
	 */
	// 프로필 불러오기(id 받아오고 profile 정보 반환)
	public Profile getUserProfile(String id) {
		Profile profile = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				// profile_no, user_id, profile_img, info
				this.pstmt.setString(1, id);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					Blob profileImg = this.rs.getBlob(3);
					String info = this.rs.getString(4);
					
					profile = new Profile(id, profileImg, info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profile;
	}
	
	
}
