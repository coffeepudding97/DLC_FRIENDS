package model.comment;

import java.sql.Timestamp;

public class Comment {
	private int cmtNo; //
	private int postNo;
	private String userId;
	private String content; //
	private Timestamp createdTime; //
	private int rpNo; //
	public Comment(int cmtNo, String userId, String content, Timestamp createdTime, int rpNo) {
		super();
		this.cmtNo = cmtNo;
		this.userId = userId;
		this.content = content;
		this.createdTime = createdTime;
		this.rpNo = rpNo;
	}
	
	public Comment(String content, Timestamp createdTime) {
		super();
		this.content = content;
		this.createdTime = createdTime;
	}
	
	
	public Comment(int postNo, String content, Timestamp createdTime) {
		super();
		this.postNo = postNo;
		this.content = content;
		this.createdTime = createdTime;
	}

	

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public int getRpNo() {
		return rpNo;
	}

	public void setRpNo(int rpNo) {
		this.rpNo = rpNo;
	}

	@Override
	public String toString() {
		return "Comment [cmtNo=" + cmtNo + ", postNo=" + postNo + ", userId=" + userId + ", content=" + content
				+ ", createdTime=" + createdTime + ", rpNo=" + rpNo + "]";
	}

	
	
}
