package model.user;

import java.security.Timestamp;

public class User {
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private Timestamp birthday;
	
	public User(String id, String pw, String nickname, String email, Timestamp birthday) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return String.format("id: %s\npw: %s\nnickname: %s\nemail: %s\nbirthday: %s", this.id, this.pw, this.nickname, this.email, this.birthday);
	}
	
	
}
