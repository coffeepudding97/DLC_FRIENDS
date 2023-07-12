package model.gametitle;

public class GameTitle {
	int gameTitleNo;
	String gameTitle;
	
	
	public int getGameTitleNo() {
		return gameTitleNo;
	}
	public String getGameTitle() {
		return gameTitle;
	}
	
	
	public GameTitle(int gameTitleNo, String gameTitle) {
		super();
		this.gameTitleNo = gameTitleNo;
		this.gameTitle = gameTitle;
	}
	
	@Override
	public String toString() {
		return "GameTitle [gameTitleNo=" + gameTitleNo + ", gameTitle=" + gameTitle + "]";
	}
}
