package model.main;

import java.sql.Timestamp;

public class MainTable {

	int postNo;
	String gameTitle;
	String title;
	String userId;
	int recruitMax;
	Timestamp createdTime;
	int viewCount;
	
	public MainTable(int postNo, String gameTitle, String title, String userId, int recruitMax, Timestamp createdTime,
			int viewCount) {
		super();
		this.postNo = postNo;
		this.gameTitle = gameTitle;
		this.title = title;
		this.userId = userId;
		this.recruitMax = recruitMax;
		this.createdTime = createdTime;
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "MainTable [postNo=" + postNo + ", gameTitle=" + gameTitle + ", title=" + title + ", userId=" + userId
				+ ", recruitMax=" + recruitMax + ", createdTime=" + createdTime + ", viewCount=" + viewCount + "]";
	}

	public int getPostNo() {
		return postNo;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public String getTitle() {
		return title;
	}

	public String getUserId() {
		return userId;
	}

	public int getRecruitMax() {
		return recruitMax;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public int getViewCount() {
		return viewCount;
	}
	
	
	
	
	
}
