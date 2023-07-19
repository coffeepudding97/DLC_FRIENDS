package model.post;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
	private int postNo;
	private String userId;
	private String title;
	private String gameTitle;
	private int recruitMax;
	private Timestamp createdTime;
	private Timestamp meetTime;
	private Timestamp leaveTime;
	private String content;
	private int viewCount;
	
	public Post(int postNo, String userId, String title, String gameTitle, int recruitMax, Timestamp createdTime,
			Timestamp meetTime, Timestamp leaveTime, int viewCount) {
		super();
		this.postNo = postNo;
		this.userId = userId;
		this.title = title;
		this.gameTitle = gameTitle;
		this.recruitMax = recruitMax;
		this.createdTime = createdTime;
		this.meetTime = meetTime;
		this.leaveTime = leaveTime;
		this.viewCount = viewCount;
	}

	public Post(int postNo, String userId, String title, String gameTitle, int recruitMax, Timestamp createdTime,
			Timestamp meetTime, Timestamp leaveTime, String content, int viewCount) {
		super();
		this.postNo = postNo;
		this.userId = userId;
		this.title = title;
		this.gameTitle = gameTitle;
		this.recruitMax = recruitMax;
		this.createdTime = createdTime;
		this.meetTime = meetTime;
		this.leaveTime = leaveTime;
		this.content = content;
		this.viewCount = viewCount;
	}
	
	public Post(String title, String gameTitle) {
		super();
		this.title = title;
		this.gameTitle = gameTitle;
	}
	
	public Post(String title, String gameTitle, Timestamp createdTime) {
		super();
		this.title = title;
		this.gameTitle = gameTitle;
		this.createdTime = createdTime;
	}
	
	public Post(int postNo, String title, String gameTitle, Timestamp createdTime) {
		super();
		this.postNo = postNo;
		this.title = title;
		this.gameTitle = gameTitle;
		this.createdTime = createdTime;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public int getRecruitMax() {
		return recruitMax;
	}

	public void setRecruitMax(int recruitMax) {
		this.recruitMax = recruitMax;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(Timestamp meetTime) {
		this.meetTime = meetTime;
	}

	public Timestamp getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Timestamp leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", userId=" + userId + ", title=" + title + ", gameTitle=" + gameTitle
				+ ", recruitMax=" + recruitMax + ", createdTime=" + createdTime + ", meetTime=" + meetTime
				+ ", leaveTime=" + leaveTime + ", content=" + content + ", viewCount=" + viewCount + "]";
	}
	
	

}
