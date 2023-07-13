package model.selectgames;

public class SelectGamesDto {
	String userId;
	String gametitle;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGametitle() {
		return gametitle;
	}
	public void setGametitle(String gametitle) {
		this.gametitle = gametitle;
	}
	
	
	public SelectGamesDto(String userId, String gametitle) {
		super();
		this.userId = userId;
		this.gametitle = gametitle;
	}
	
	@Override
	public String toString() {
		return "SelectGamesDto [userId=" + userId + ", gametitle=" + gametitle + "]";
	}
	
	
}
