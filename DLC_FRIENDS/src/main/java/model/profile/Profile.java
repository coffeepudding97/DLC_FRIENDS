package model.profile;

public class Profile {
	// profile_no, user_id, profile_img, info, title, content
	private String id;
//	private Blob profileImg;
	private String imageHtml;
	private String info;
	private String nickname;
	
	
	public Profile(String id, String imageHtml, String info, String nickname) {
		super();
		this.id = id;
		this.imageHtml = imageHtml;
		this.info = info;
		this.nickname = nickname;
	}
	
	public Profile(String id, String imageHtml, String info) {
		super();
		this.id = id;
		this.imageHtml = imageHtml;
		this.info = info;
	}
	
	public Profile(String id, String nickname) {
		super();
		this.id = id;
		this.info = "소개글을 입력해주세요.";
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
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
