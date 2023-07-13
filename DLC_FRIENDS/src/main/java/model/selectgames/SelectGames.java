package model.selectgames;

public class SelectGames {
	String userId;
	String gametitle;
	
	public String getUserId() {
		return userId;
	}
	public String getGametitle() {
		return gametitle;
	}
	
	public SelectGames(String userId, String gametitle) {
		super();
		this.userId = userId;
		this.gametitle = gametitle;
	}
	
	@Override
	public String toString() {
		return "SelectGames [userId=" + userId + ", gametitle=" + gametitle + "]";
	}
	
	
	
}
