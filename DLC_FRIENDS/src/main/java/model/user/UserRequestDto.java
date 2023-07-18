package model.user;

import java.sql.Date;

public class UserRequestDto {
	private String id;
	private String password;
	private String nickName;
	private String email;
	private int birthday;
	private String info;
	
	public UserRequestDto(String id, String password, String nickname, String email, int birthday) {
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


	public int getBirthday() {
		return birthday;
	}


	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	

	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}

	
	
	public UserRequestDto(String id, String password, String info) {
		super();
		this.id = id;
		this.password = password;
		this.info = info;
	}
	
	public UserRequestDto(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	


	@Override
	public String toString() {
		return "UserRequestDto [id=" + id + ", password=" + password + ", nickName=" + nickName + ", email=" + email
				+ ", birthday=" + birthday + ", info=" + info + "]";
	}

	
	
}
