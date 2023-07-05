package model.profile;

public class Profile {
	private String id;
	private String nickname;
	private String intro;
	
	public Profile(String id, String nickname, String intro) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getIntro() {
		return intro;
	}
	
	@Override
	public String toString() {
		return String.format("id: %s\nnickname: %s\nintro: %s", this.id, this.nickname, this.intro);
	}
}
