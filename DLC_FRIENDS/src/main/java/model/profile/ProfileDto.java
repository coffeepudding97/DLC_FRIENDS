package model.profile;

public class ProfileDto {
	private String id;
	private String nickname;
	private String intro;
	
	public ProfileDto(String id, String nickname, String intro) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
