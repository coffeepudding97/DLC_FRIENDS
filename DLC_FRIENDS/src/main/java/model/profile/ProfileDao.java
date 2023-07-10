package model.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
//					Blob profileImg = this.rs.getBlob(3);
					InputStream inputStream = rs.getBinaryStream(3);
					Path outputPath = Path.of("output.png");
					Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
					
					String base64Image = encodeImageToBase64(outputPath);
					String imageHtml = "<img src=\"data:image/png;base64," + base64Image + "\" alt=\"image\" width=\"100\" height=\"100\">";
					
					
					String info = this.rs.getString(4);
					
					profile = new Profile(id, imageHtml, info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profile;
	}
	
	public ArrayList<ProfileDto> getProfileDtosByUserIds(ArrayList<String> userIds, int postNo) {
		ArrayList<ProfileDto> profileDtos = new ArrayList<ProfileDto>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";
			
			try {
				for(String id : userIds) {
					this.pstmt = this.conn.prepareStatement(sql);
					// profile_no, user_id, profile_img, info
					this.pstmt.setString(1, id);
					//this.pstmt.setInt(2, postNo);
					
					this.rs = this.pstmt.executeQuery();
					
					if(this.rs.next()) {
//						Blob profileImg = this.rs.getBlob(3);
						String userId = this.rs.getString("user_id");
						InputStream inputStream = rs.getBinaryStream("profile_img");
						Path outputPath = Path.of("output.png");
						Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
						
						String base64Image = encodeImageToBase64(outputPath);
						String imageHtml = "<img src=\"data:image/png;base64," + base64Image + "\" alt=\"image\" width=\"100\" height=\"100\">";
						String info = this.rs.getString("info");
						
						profileDtos.add(new ProfileDto(userId, imageHtml, info));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profileDtos;
	}
	
    private static String encodeImageToBase64(Path imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
