package model.profile;

import java.sql.Blob;

public class Profile {
	// profile_no, user_id, profile_img, info, title, content
	private String id;
//	private Blob profileImg;
	private String imageHtml;
	private String info;
	
	
	
	public Profile(String id, String imageHtml, String info) {
		super();
		this.id = id;
		this.imageHtml = imageHtml;
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
	public String getProfileImg() {
		return imageHtml;
	}
	public String getInfo() {
		return info;
	}


	@Override
	public String toString() {
		return "Profile [id=" + id + ", imageHtml=" + imageHtml + ", info=" + info + "]";
	}
	
	
}
