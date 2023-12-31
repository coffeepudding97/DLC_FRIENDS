package model.post;

import java.sql.Timestamp;

public class PostResponseDto {
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
	
	//IndexDao 생성자
	public PostResponseDto(int postNo, String gameTitle, String title, String userId, int recruitMax, Timestamp createdTime ,int viewCount) {
		this.postNo = postNo;
		this.gameTitle = gameTitle;
		this.title = title;
		this.userId = userId;
		this.recruitMax = recruitMax;
		this.createdTime = createdTime;
		this.viewCount = viewCount;
	}
	
	public PostResponseDto(int postNo, String userId, String title, String gameTitle, int recruitMax,
			Timestamp createdTime, Timestamp meetTime, Timestamp leaveTime, String content, int viewCount) {
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
	
	public PostResponseDto(String title, String gameTitle) {
		super();
		this.title = title;
		this.gameTitle = gameTitle;
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

	
	
	
	
}
