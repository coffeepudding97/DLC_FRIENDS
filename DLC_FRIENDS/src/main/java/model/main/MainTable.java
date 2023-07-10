package model.main;

import java.sql.Timestamp;

public class mainTable {

	int postNo;
	String gameTitle;
	String title;
	String userId;
	int recruitMax;
	Timestamp createTime;
	int viewCount;
	
	public mainTable(int postNo, String gameTitle, String title, String userId, int recruitMax, Timestamp createTime,
			int viewCount) {
		super();
		this.postNo = postNo;
		this.gameTitle = gameTitle;
		this.title = title;
		this.userId = userId;
		this.recruitMax = recruitMax;
		this.createTime = createTime;
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "mainTable [postNo=" + postNo + ", gameTitle=" + gameTitle + ", title=" + title + ", userId=" + userId
				+ ", recruitMax=" + recruitMax + ", createTime=" + createTime + ", viewCount=" + viewCount + "]";
	}
	
	
	
}
