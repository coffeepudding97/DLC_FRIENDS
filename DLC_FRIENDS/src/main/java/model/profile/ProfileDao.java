package model.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	// 프로필 불러오기(id 받아오고 profile 정보 반환)
	public Profile getUserProfile(String id) {
		Profile profile = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM profile WHERE id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					String nickname = this.rs.getString(2);
					String info = this.rs.getString(3);
					
					profile = new Profile(id, nickname, info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					this.pstmt.close();
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return profile;
	}
}
