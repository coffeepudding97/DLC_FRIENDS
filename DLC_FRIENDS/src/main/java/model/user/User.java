package model.user;

import java.sql.Date;

public class User {
	private String id;
	private String pw;
	private String nickName;
	private String email;
	private Date birthday;
	
	public User(String id, String pw, String nickName, String email, Date birth) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.email = email;
		this.birthday = birth;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNickname() {
		return nickName;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return String.format("id: %s\npw: %s\nnickname: %s\nemail: %s\nbirthday: %s", this.id, this.pw, this.nickName, this.email, this.birthday);
	}
	
	
}
