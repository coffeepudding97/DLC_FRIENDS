package model.party;

import java.util.ArrayList;

import model.profile.Profile;

public class PartyRequestDto {
	private int partyNo;
	private int postNo;
	private ArrayList<String> userIds;
	
	
	public PartyRequestDto(int postNo, ArrayList<String> userIds) {
		super();
		this.postNo = postNo;
		this.userIds = userIds;
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
	public void setUserIds(ArrayList<String> profiles) {
		this.userIds = profiles;
	}
}
