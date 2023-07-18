package model.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.user.UserRequestDto;

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
	private ProfileDao() {
	}

	private static ProfileDao instance = new ProfileDao();

	public static ProfileDao getInstance() {
		return instance;
	}

	// 프로필 불러오기(id 받아오고 profile 정보 반환)
	/*public Profile getUserProfile(String id) {
		Profile profile = null;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				// user_id, profile_img, info, nickname
				this.pstmt.setString(1, id);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					InputStream inputStream = this.rs.getBinaryStream(3);

					Path outputPath = Path.of("output.png");
					if (inputStream != null) {
						Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);

						// 프로필 이미지 변환 및 base64 인코딩
						String base64Image = encodeImageToBase64(outputPath);
//						String imageHtml = "<img src=\"data:image/png;base64," + base64Image + "\" alt=\"image\" width=\"100\" height=\"100\">";

						// 프로필 정보 읽어오기
						String info = this.rs.getString(4);
						String nickname = this.rs.getString(5);

						profile = new Profile(id, base64Image, info, nickname);

						// 이미지파일이 null일 때
					} else {
						String info = this.rs.getString(4);
						String nickname = this.rs.getString(5);

						profile = new Profile(id, null, info, nickname);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profile;
	}*/

	public Profile getUserProfile(String id) {
		Profile profile = null;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				// user_id, profile_img, info, nickname
				this.pstmt.setString(1, id);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					InputStream inputStream = this.rs.getBinaryStream(3);

					if (inputStream != null) {
						byte[] imageBytes = inputStream.readAllBytes();
						// 프로필 이미지 변환 및 base64 인코딩
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//						String imageHtml = "<img src=\"data:image/png;base64," + base64Image + "\" alt=\"image\" width=\"100\" height=\"100\">";

						// 프로필 정보 읽어오기
						String info = this.rs.getString(4);
						String nickname = this.rs.getString(5);

						profile = new Profile(id, base64Image, info, nickname);

						// 이미지파일이 null일 때
					} else {
						String info = this.rs.getString(4);
						String nickname = this.rs.getString(5);

						profile = new Profile(id, null, info, nickname);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profile;
	}

	/*public ArrayList<ProfileDto> getProfileDtosByUserIds(ArrayList<String> userIds, int postNo) {
		ArrayList<ProfileDto> profileDtos = new ArrayList<ProfileDto>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";

			try {
				for (String id : userIds) {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, id);

					this.rs = this.pstmt.executeQuery();

					if (this.rs.next()) {
						String userId = this.rs.getString("user_id");
						InputStream inputStream = rs.getBinaryStream("profile_img");
						String info = this.rs.getString("info");

						String nickname = this.rs.getString("nickname");
						if (inputStream != null) {
							Path outputPath = Path.of("output.png");
							Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);

							String base64Image = encodeImageToBase64(outputPath);
							String imageHtml = "<img src=\"data:image/png;base64," + base64Image
									+ "\" alt=\"image\" width=\"100\" height=\"100\">";

							profileDtos.add(new ProfileDto(userId, imageHtml, info, nickname));
						} else {
							profileDtos.add(new ProfileDto(userId, null, info, nickname));
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return profileDtos;
	}*/

	public ArrayList<ProfileDto> getProfileDtosByUserIds(ArrayList<String> userIds, int postNo) {
		ArrayList<ProfileDto> profileDtos = new ArrayList<ProfileDto>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM profile WHERE user_id=?";

			try {
				for (String id : userIds) {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, id);

					this.rs = this.pstmt.executeQuery();

					if (this.rs.next()) {
						String userId = this.rs.getString("user_id");
						InputStream inputStream = rs.getBinaryStream("profile_img");
						String info = this.rs.getString("info");

						String nickname = this.rs.getString("nickname");
						if (inputStream != null) {
							// Path outputPath = Path.of("output.png");
							// Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
							byte[] imageBytes = inputStream.readAllBytes();
							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
							String imageHtml = "<img src=\"data:image/png;base64," + base64Image
									+ "\" alt=\"image\" width=\"100\" height=\"100\">";

							profileDtos.add(new ProfileDto(userId, imageHtml, info, nickname));
						} else {
							profileDtos.add(new ProfileDto(userId, null, info, nickname));
						}

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

	// 사진 수정
	public boolean upload(String id, Part inputImage, InputStream inputStream) {
		Connection conn = DBManager.getConnection();

		if (conn != null) {
			String sql = "UPDATE profile SET profile_img=? WHERE user_id=?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setBinaryStream(1, inputImage.getInputStream(), inputImage.getSize());
				this.pstmt.setString(2, id);

				this.pstmt.execute();
				System.out.println("DB 업로드 성공");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB 업로드 실패");
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		return false;
	}

	public String updateUser(UserRequestDto userDto, String password) {
		this.conn = DBManager.getConnection();
		String result = "";

		if (this.conn != null && userDto.getPassword() != null) {
			String profileSql = "UPDATE profile SET info=? WHERE user_id=?";
			String userSql = "UPDATE user SET pw=? WHERE user_id=? AND pw=?";

			// 비밀번호만 변경
			if (!userDto.getPassword().isEmpty()) {
				// using transaction to update both tables together
				try {
					this.conn.setAutoCommit(false);

					// 비밀번호 수정
					this.pstmt = this.conn.prepareStatement(userSql);
					this.pstmt.setString(1, userDto.getPassword());
					this.pstmt.setString(2, userDto.getId());
					this.pstmt.setString(3, password);

					int affectedRows1 = this.pstmt.executeUpdate();

					// info 수정
					this.pstmt = this.conn.prepareStatement(profileSql);
					this.pstmt.setString(1, userDto.getInfo());
					this.pstmt.setString(2, userDto.getId());

					int affectedRows2 = this.pstmt.executeUpdate();

					if (affectedRows1 > 0 && affectedRows2 > 0) {
						this.conn.commit();
						result = "pwInfoChange";
					} else if (affectedRows1 == 0) {
						result = "pwWrong";
					} else if (affectedRows2 == 0) {
						result = "infoWrong";
					}

				} catch (Exception e) {
					e.printStackTrace();

					try {
						this.conn.rollback();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}

				// info만 변경
			} else if (userDto.getPassword().isEmpty()) {
				try {
					this.pstmt = this.conn.prepareStatement(profileSql);
					this.pstmt.setString(1, userDto.getInfo());
					this.pstmt.setString(2, userDto.getId());

					int affectedRows = this.pstmt.executeUpdate();
					if (affectedRows > 0) {
						result = "infoChange";
					} else if (affectedRows == 0) {
						result = "infoWrong";
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}
		return result;
	}
}
