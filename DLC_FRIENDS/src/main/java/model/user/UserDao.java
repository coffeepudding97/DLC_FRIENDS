package model.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBManager;

public class UserDao {
	// Database 연동을 위한 준비
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;


	// private ArrayList<User> list; ->
	// Database 연동하여 데이터 관리.
	// Dao 싱글톤 구현

	private UserDao() {
	}

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
	public boolean duplNickname(String nickname) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT nickname FROM user where nickname= ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, nickname);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					int count = rs.getInt(1);
					if(count > 0)
						return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
		return false;
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
		
		boolean isSuccess = false;

		if(id != null && password != null && nickName != null && birth != 0) {
			this.conn = DBManager.getConnection();
			if(this.conn != null) {
				String userInsertSql = "INSERT INTO user VALUES(?, ?, ?, ?, DATE(?))";
				String profileInsertSql = "INSERT INTO profile(user_id, profile_img, info) VALUES(?, null, '소개글을 입력해주세요.');";
				
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
					this.pstmt = this.conn.prepareStatement(profileInsertSql);
					this.pstmt.setString(1, id);
					pstmt.executeUpdate();
					
					// 트랜잭션 커밋
					conn.commit();
					isSuccess = true;
					
				} catch (Exception e) {
					e.printStackTrace();
					isSuccess = false;
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
		return isSuccess;
	}

	public User getUserById(String id) {
		User user = null;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM user WHERE user_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					String password = this.rs.getString(2);
					String nickname = this.rs.getString(3);
					String email = this.rs.getString(4);
					Date birth = this.rs.getDate(5);

					user = new User(id, password, nickname, email, birth);
				}
			} catch (Exception e) {
//				e.printStackTrace();
				return null;
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return user;
	}
}
