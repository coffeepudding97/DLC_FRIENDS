package model.party;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.post.PostDao;
import model.profile.Profile;
import util.DBManager;

public class PartyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private PartyDao() {}
	private static PartyDao instance = new PartyDao();
	public static PartyDao getInstance() {
		return instance;
	}
	
	public boolean createParty(PartyRequestDto partyDto, int postNo) {
		PostDao postDao = PostDao.getInstance();
		
		String userId = postDao.getUserIdByPostNo(postNo);
		
		boolean check = true;
		
		if(postNo > 0 && userId!=null) {
			this.conn = DBManager.getConnection();
			if(this.conn != null) {
				String sql = "insert into party_member(post_no, user_id) values (?, ?)";
				String hostId = userId;
				
				try {
					this.pstmt = this.conn.prepareStatement(sql);
					
					this.pstmt.setInt(1, postNo);
					this.pstmt.setString(2, hostId);
					
					this.pstmt.execute();
				} catch (Exception e) {
					e.printStackTrace();
					check = false;
				} finally {
					DBManager.close(conn, pstmt);
				}
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public Party getPartyByPostNo(int postNo) {
		Party party = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM party_member WHERE post_no=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				
				this.rs = this.pstmt.executeQuery();
				
				int partyNo = 0;
				ArrayList<String> userIds = new ArrayList<String>();
				
				while(this.rs.next()) {
					partyNo = this.rs.getInt(1);
					userIds.add(this.rs.getString(3));
				}
				
				party = new Party(partyNo, postNo, userIds);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return party;
	}
	
	// 로그인 기능 환성 후 작성
	public void join() {
		
	}
	
	public void leave() {
		
	}
	
}
