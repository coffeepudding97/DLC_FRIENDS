package model.gametitle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class GameTitleDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private GameTitleDao() {}
	
	private static GameTitleDao instance = new GameTitleDao();
	
	public static GameTitleDao getInstance() {
		return instance;
	}
	
	public ArrayList<GameTitle> allGameTitle() {
		ArrayList<GameTitle> gameList = new ArrayList<>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM gametitle";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int titleNo = this.rs.getInt(1);
					String title = this.rs.getString(2);
					
					GameTitle titles = new GameTitle(titleNo, title);
					gameList.add(titles);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
		return gameList;
	}
}
