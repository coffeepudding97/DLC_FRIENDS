package model.party;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.post.PostDao;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.rating.RatingDao;
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
				ArrayList<String> imageHtmls = new ArrayList<String>();
				ProfileDao profileDao = ProfileDao.getInstance();
				
				while(this.rs.next()) {
					partyNo = this.rs.getInt(1);
					String userId = this.rs.getString(3);
					userIds.add(userId);
					
					Profile profile = profileDao.getUserProfile2(userId);
					imageHtmls.add(profile.getProfileImg());
				}
				
				//party = new Party(partyNo, postNo, userIds);
				party = new Party(partyNo, postNo, userIds, imageHtmls);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return party;
	}
	
	public Party getPartyExceptSelfByPostNo(int postNo, String self) {
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
				ArrayList<String> imageHtmls = new ArrayList<String>();
				ProfileDao profileDao = ProfileDao.getInstance();
				
				while(this.rs.next()) {
					partyNo = this.rs.getInt(1);
					String userId = this.rs.getString(3);
					if(!userId.equals(self)) {
						userIds.add(userId);
						
						Profile profile = profileDao.getUserProfile(userId);
						imageHtmls.add(profile.getProfileImg());
					}
					
				}
				
				//party = new Party(partyNo, postNo, userIds);
				party = new Party(partyNo, postNo, userIds, imageHtmls);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return party;
	}
	
	
	// 프로필 유저의 최근 파티정보 출력
	public ArrayList<Party> getPartyById(String id) {
		ArrayList<Party> list = new ArrayList<>();
		
		this.conn = DBManager.getConnection();
		if(this.conn != null) {
			try {
				//  중복 제거, 프로필유저의 최근 파티목록 (최대3명)
				String sql = "SELECT DISTINCT user_id FROM party_member WHERE post_no IN ( SELECT post_no FROM party_member WHERE user_id = ? ) AND user_id != ? LIMIT 3";
			
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, id);
				this.pstmt.setString(2, id);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					String partyId = this.rs.getString(1);
					
					Party party = new Party(partyId);
					list.add(party);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
		return list;
	}
	
	// 로그인 기능 환성 후 작성
	public void join(int postNo, String userId) {
		this.conn = DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "INSERT INTO party_member(post_no, user_id) values(?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, userId);
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public void leave(int postNo, String userId) {
		this.conn = DBManager.getConnection();
		if(this.conn!=null) {
			String sql = "DELETE FROM party_member WHERE post_no = ? AND user_id = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
				this.pstmt.setString(2, userId);
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public boolean deletePartyByPostNo(int postNo) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn!= null) {
			String sql = "DELETE FROM party_member WHERE post_no = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, postNo);
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
		
		return check;
	}
	
	public ArrayList<Integer> getPostNosByUserId(String userId) {
		ArrayList<Integer> postNos = new ArrayList<Integer>();
		this.conn = DBManager.getConnection();
		
		if(this.conn!=null) {
			String sql = "SELECT post_no FROM party_member WHERE user_id=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					postNos.add(this.rs.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return postNos;
	}
}
