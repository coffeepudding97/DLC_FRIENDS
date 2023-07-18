package model.user;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.profile.Profile;
import model.profile.ProfileDto;
import util.DBManager;

public class UserDao {
	// Database 연동을 위한 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;


	// private ArrayList<User> list; ->
	// Database 연동하여 데이터 관리.
	// Dao 싱글톤 구현

	private UserDao() {}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// CRUD
	// ㄴ Create
	// ㄴ Read
	// ㄴ Update (Update 테이블명 SET 컬럼명1=값1, 컬럼명2=값2 ... WHERE 기본키=값3 AND 비밀번호=값4)
	// ㄴ Delete (DELETE FROM 테이블명 WHERE 기본키=값1)
	
	
	// 아이디 중복체크(true: 중복있음)
	public boolean duplIdCheck(String id) {
		User result = getUserById(id);
		if(result != null)
			return true;
		
		return false;
	}
	
	// 닉네임 중복체크 (true: 중복있음)
	public boolean duplNickname(String nickName) {
		boolean dupl = false;
		String name = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT nickname FROM user where nickname= ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, nickName);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					name = rs.getString(1);
					System.out.println("name:" + name);
					if(name != null)
						dupl = true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
		return dupl;
	}
	
	// 유저 생성
	public boolean createUser(UserRequestDto userDto) {
		User result = getUserById(userDto.getId());
		if (result != null)
			return false;

		String id = userDto.getId();
		String password = userDto.getPassword();
		String nickName = userDto.getNickname();
		String email = userDto.getEmail();
		int birth = userDto.getBirthday(); // date
		InputStream inputStream = getDefaultImg();
		
		boolean check = false;

		if(id != null && password != null && nickName != null && birth != 0) {
			this.conn = DBManager.getConnection();
			if(this.conn != null) {
				
				String userInsertSql = "INSERT INTO user VALUES(?, ?, ?, ?, DATE(?))";

				String profileInsertSql = "INSERT INTO profile(user_id, profile_img, info, nickname) VALUES(?, ?, ?, ?)";
				
				try {
					// 트랜잭션 시작
					conn.setAutoCommit(false);
					
					// 회원정보 저장
					this.pstmt = this.conn.prepareStatement(userInsertSql);
					this.pstmt.setString(1, id);
					this.pstmt.setString(2, password);
					this.pstmt.setString(3, nickName);
					this.pstmt.setString(4, email);
					this.pstmt.setInt(5, birth);
					this.pstmt.executeUpdate();

					// 프로필 정보 저장
					Profile profile = new Profile(id, nickName);
					
					this.pstmt = this.conn.prepareStatement(profileInsertSql);
					this.pstmt.setString(1, profile.getId());
					this.pstmt.setBinaryStream(2, inputStream);
					this.pstmt.setString(3, profile.getInfo());					
					this.pstmt.setString(4, profile.getNickname());
					pstmt.executeUpdate();
					
					
					// 트랜잭션 커밋
					conn.commit();
					check = true;
					
				} catch (Exception e) {
					e.printStackTrace();
					check = false;
					try {
						// 트랜지션 롤백
						this.conn.rollback();
						
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}
		return check;
	}
	
	
	// 코드 생성
	private String makeCode() {
		String code = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        Random r = new Random();
//        char c = alphabet.charAt(r.nextInt(alphabet.length()));
        
		return code;
	}
	
    
    
	public boolean deleteUserById(String id, String password) {
		this.conn = DBManager.getConnection();
		boolean check = true;
		
		if (this.conn != null) {
			// FK로 연결된 요소들 우선 삭제 후 user 삭제
			String profileDeleteSql = "DELETE FROM profile WHERE user_id=?";
			String userDeleteSql = "DELETE FROM user WHERE user_id=? AND pw=?";

			try {
				// 트랜잭션 시작
				this.conn.setAutoCommit(false);

				// 프로필 정보 삭제
				this.pstmt = this.conn.prepareStatement(profileDeleteSql);
				this.pstmt.setString(1, id);
				this.pstmt.executeUpdate();

				// 유저 삭제
				this.pstmt = this.conn.prepareStatement(userDeleteSql);
				this.pstmt.setString(1, id);
				this.pstmt.setString(2, password);
				this.pstmt.executeUpdate();

				// 트랜잭션 커밋
				conn.commit();
				check = true;

			} catch (Exception e) {
				e.printStackTrace();
				check = false;

				try {
					// 트랜지션 롤백
					this.conn.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
		return check;
	}

	public User getUserById(String id) {
		User user = null;

		this.conn = DBManager.getConnection();
		
		if (this.conn != null) {
			String sql = "SELECT * FROM user WHERE user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);

				this.rs = this.pstmt.executeQuery();
				
				if (this.rs.next()) {
					System.out.println("rs");
					String password = this.rs.getString(2);
					System.out.println(password);
					String nickName = this.rs.getString(3);
					String email = this.rs.getString(4);
					Date birth = this.rs.getDate(5);

					user = new User(id, password, nickName, email, birth);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return user;
	}
	
	
	public String checkEmail(String addr, String id) {
		String userId = "";
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT user_id FROM user WHERE user_id=? AND email=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.pstmt.setString(2, addr);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
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
	
	public boolean checkAccount(String id, String password) {
		boolean isTrue = false;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT user_id FROM user WHERE user_id = ? AND pw = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.pstmt.setString(2, password);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					String user_id = this.rs.getString(1);

					if (user_id != null || user_id != "") {
						isTrue = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				isTrue = false;
				
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return isTrue;
	}
	
	public boolean updatePwd(String id, String password) {
		boolean result = false;
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "UPDATE user SET pw=? WHERE user_id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setString(1, password);
				this.pstmt.setString(2, id);
				
				int affectedRows = this.pstmt.executeUpdate();
				if (affectedRows > 0) {
					result = true;
				} else if (affectedRows == 0) {
					result = false;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private InputStream getDefaultImg() {
		InputStream inputStream = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT FILE FROM tbl_test WHERE ID = 1";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					inputStream = rs.getBinaryStream("FILE");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return inputStream;
	}
}
