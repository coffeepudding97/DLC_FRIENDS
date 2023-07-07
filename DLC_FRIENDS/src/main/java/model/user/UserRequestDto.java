package model.user;

import java.sql.Date;

public class UserRequestDto {
	private String id;
	private String password;
	private String nickName;
	private String email;
	private Date birthday;
	
	public UserRequestDto(String id, String password, String nickname, String email, Date birthday) {
		this.id = id;
		this.password = password;
		this.nickName = nickname;
		this.email = email;
		this.birthday = birthday;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPw(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickName;
	}
	public void setNickname(String nickname) {
		this.nickName = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	
}
