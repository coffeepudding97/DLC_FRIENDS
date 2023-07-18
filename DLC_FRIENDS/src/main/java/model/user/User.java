package model.user;

import java.sql.Date;

public class User {
	private String id;
	private String password;
	private String nickName;
	private String email;
	private Date birthday;
	 
	public User(String id, String password, String nickName, String email, Date birth) {
		this.id = id;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.birthday = birth;
	}
	
	public User(String id) {
		super();
		this.id = id;
	}


	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
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
		return String.format("id: %s\npassword: %s\nnickName: %s\nemail: %s\nbirthday: %s", this.id, this.password, this.nickName, this.email, this.birthday);
	}
	
	
}
