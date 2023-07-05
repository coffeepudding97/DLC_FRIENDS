package model.post;

import java.sql.Date;
import java.sql.Timestamp;

public class PostRequestDto {
	String title;
	String user_id;
	String gameTitle;
	int recruitMax;
	Timestamp meetTime;
	Timestamp leaveTime;
	String content;
	
	public PostRequestDto(String title, String user_id, String gameTitle, int recruitMax, Timestamp meetTime, Timestamp leaveTime) {
		super();
		this.title = title;
		this.user_id = user_id;
		this.gameTitle = gameTitle;
		this.recruitMax = recruitMax;
		this.meetTime = meetTime;
		this.leaveTime = leaveTime;
	}
	
	public PostRequestDto(String title, String user_id, String gameTitle, int recruitMax, Timestamp meetTime, Timestamp leaveTime,
			String content) {
		super();
		this.title = title;
		this.user_id = user_id;
		this.gameTitle = gameTitle;
		this.recruitMax = recruitMax;
		this.meetTime = meetTime;
		this.leaveTime = leaveTime;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	
}
