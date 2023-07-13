package model.selectgames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.user.UserDao;
import util.DBManager;

public class SelectGamesDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private SelectGamesDao() {
	}

	private static SelectGamesDao instance = new SelectGamesDao();

	public static SelectGamesDao getInstance() {
		return instance;
	}

	// 유저+게임 추가
	public boolean addGames(String id, String[] gameList) {

		if (id != null) {
			this.conn = DBManager.getConnection();
			if (this.conn != null) {
				String sql = "INSERT INTO select_games VALUES(?,?)";

				try {
					// 선호게임 저장
					this.pstmt = this.conn.prepareStatement(sql);
					for (String selectGame : gameList) {
						this.pstmt.setString(1, id);
						this.pstmt.setString(2, selectGame);
						this.pstmt.executeUpdate();
					}
					return true;

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}

		return false;
	}

	// 사용자가 선택한 게임 리스트 반환
	public ArrayList<SelectGames> getSelectedListById(String id) {
		ArrayList<SelectGames> list = new ArrayList<>();
		if (id != null) {
			this.conn = DBManager.getConnection();
			if (this.conn != null) {
				// select
				String sql = "SELECT gametitle FROM select_games WHERE user_id = ?";
				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, id);

					this.rs = pstmt.executeQuery();

					while (this.rs.next()) {
						String gameId = this.rs.getString(1);

						SelectGames selectgames = new SelectGames(id, gameId);
						list.add(selectgames);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}
		return list;
	}

	// 선호 게임목록 저장 & 업데이트
	public boolean updateSelectedList(String id, String[] gameList) {
		boolean result = false;
		if (id != null) {
			this.conn = DBManager.getConnection();
			if (this.conn != null && gameList != null) {
				try {
					// 트랜잭션 위해 자동커밋 비활성화
					this.conn.setAutoCommit(false);

					// 기존 선호게임 목록 삭제
					String deleteSql = "DELETE FROM select_games WHERE user_id=?";
					this.pstmt = this.conn.prepareStatement(deleteSql);
					this.pstmt.setString(1, id);
					this.pstmt.executeUpdate();

					// 새로운 선호게임 목록 추가
					String insertSql = "INSERT INTO select_games VALUES(?,?)";
					this.pstmt = conn.prepareStatement(insertSql);

					for (String gameTitle : gameList) {
						this.pstmt.setString(1, id);
						this.pstmt.setString(2, gameTitle);
						// sql문장을 한번만 전체적으로 실행.
						this.pstmt.addBatch();
					}
					this.pstmt.executeBatch();
					this.conn.commit();
					result = true;

				} catch (Exception e) {
					e.printStackTrace();
					try {
						if (this.conn != null) {
							// 에러 발생 시 롤백
							this.conn.rollback();
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
		}
		return result;
	}

}
