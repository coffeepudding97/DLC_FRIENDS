package model.comment;

import java.sql.Timestamp;

public class CommentRequestDto {
	private int cmtNo;
	private String userId;
	private String content;
	private Timestamp createdTime;
	private int rpNo;
	
	public CommentRequestDto(String userId, String content) {
		super();
		this.userId = userId;
		this.content = content;
	}
	
	public CommentRequestDto(String userId, String content, int rpNo) {
		super();
		this.userId = userId;
		this.content = content;
		this.rpNo = rpNo;
	}

	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
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
	
	
}
