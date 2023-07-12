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

	public boolean createUser(UserRequestDto userDto) {
		User result = getUserById(userDto.getId());
		if (result != null)
			return false;

		String id = userDto.getId();
		String password = userDto.getPassword();
		String nickName = userDto.getNickname();
		String email = userDto.getEmail();
		Date birth = userDto.getBirthday(); // date

		boolean check = true;

		if (id != null && password != null && nickName != null && birth != null) {
			this.conn = DBManager.getConnection();
			if (this.conn != null) {
				String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";
// id, pw, nick, email, birth
				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, id);
					this.pstmt.setString(2, password);
					this.pstmt.setString(3, nickName);
					this.pstmt.setString(4, email);
					this.pstmt.setDate(5, (java.sql.Date) birth);

					this.pstmt.execute();

				} catch (Exception e) {
					e.printStackTrace();
					check = false;
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		return check;
	}

	/*public ArrayList<User> getUserAll() {
		ArrayList<User> list = new ArrayList<User>();

		// 1. 데이터 베이스 연동
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			// 2. 연동된 DB에 execute할 SQL쿼리를 문자열로 작성
			String sql = "SELECT * FROM user";

			try {
				// 3. 쿼리를 객체에 담아 날릴 준비
				this.pstmt = this.conn.prepareStatement(sql);

				// 4. 쿼리 실행 execute -> 반환 받은 ResultSet 을 초기화
				this.rs = this.pstmt.executeQuery();

				// 5. ResultSet의 행 읽기
				while (this.rs.next()) {
					String id = this.rs.getString(1);
					String password = this.rs.getString(2);
					String nickName = this.rs.getString(3);
					String email = this.rs.getString(4);
					Date birth = this.rs.getDate(5);

					User user = new User(id, password, email, nickName, birth);
					list.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}*/

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
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return user;
	}

	/*public void updateUser(UserRequestDto userDto, String password) {
		this.conn = DBManager.getConnection();

		if (this.conn != null && userDto.getId() != null && userDto.getPassword() != null
				&& userDto.getEmail() != null) {
			if (userDto.getPassword() != "") {
				String sql = "UPDATE user SET password=?, email=? WHERE id=? AND password=?";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, userDto.getId());
					this.pstmt.setString(2, userDto.getPassword());
					this.pstmt.setString(3, userDto.getEmail());
					this.pstmt.setString(4, password);

					this.pstmt.execute();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			} else {
				String sql = "UPDATE user SET email=? WHERE id=? AND password=?";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, userDto.getEmail());
					this.pstmt.setString(2, userDto.getId());
					this.pstmt.setString(3, password);

					this.pstmt.execute();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}
	}

	public boolean deleteUserById(String id, String password) {
		this.conn = DBManager.getConnection();

		boolean check = true;

		if (this.conn != null) {
			String sql = "DELETE FROM user WHERE id=? AND password=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.pstmt.setString(2, password);

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
				check = false;
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		} else {
			check = false;
		}
		return check;
	}
*/
}
