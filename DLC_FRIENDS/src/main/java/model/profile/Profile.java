package model.profile;

import java.sql.Blob;

public class Profile {
	// profile_no, user_id, profile_img, info, title, content
	private String id;
	private Blob profileImg;
	private String info;
	
	
	
	public Profile(String id, Blob profileImg, String info) {
		super();
		this.id = id;
		this.profileImg = profileImg;
		this.info = info;
	}
	
	
	public Profile(String id, String info) {
		super();
		this.id = id;
		this.info = info;
	}


	public Profile(String id) {
		super();
		this.id = id;
	}


	public String getId() {
		return id;
	}
	public Blob getProfileImg() {
		return profileImg;
	}
	public String getInfo() {
		return info;
	}


	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileImg=" + profileImg + ", info=" + info + "]";
	}
	
	
}
