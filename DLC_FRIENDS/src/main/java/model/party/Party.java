package model.party;

import java.util.ArrayList;

import model.profile.Profile;

public class Party {
	private int partyNo;
	private int postNo;
	private ArrayList<String> userIds;
	private ArrayList<String> imageHtml;
	
	public Party(int partyNo, int postNo, ArrayList<String> userIds) {
		super();
		this.partyNo = partyNo;
		this.postNo = postNo;
		this.userIds = userIds;
	}
	public Party(int partyNo, int postNo, ArrayList<String> userIds, ArrayList<String> imageHtml) {
		super();
		this.partyNo = partyNo;
		this.postNo = postNo;
		this.userIds = userIds;
		this.imageHtml = imageHtml;
	}
	public ArrayList<String> getImageHtml() {
		return imageHtml;
	}
	public void setImageHtml(ArrayList<String> imageHtml) {
		this.imageHtml = imageHtml;
	}
	public int getPartyNo() {
		return partyNo;
	}
	public void setPartyNo(int partyNo) {
		this.partyNo = partyNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public ArrayList<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(ArrayList<String> userIds) {
		this.userIds = userIds;
	}
	@Override
	public String toString() {
		return "Party [userIds=" + userIds + ", imageHtml=" + imageHtml + "]";
	}

	
	
}
