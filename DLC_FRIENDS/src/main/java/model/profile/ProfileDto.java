package model.profile;

import java.sql.Blob;

public class ProfileDto {
	private String id;
//	private Blob profileImg;
	private String imageHtml;
	private String info;
	private String nickname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	public String getImageHtml() {
		return imageHtml;
	}
	public void setImageHtml(String imageHtml) {
		this.imageHtml = imageHtml;
	}
	
	
	public ProfileDto(String id, String imageHtml, String info, String nickname) {
		super();
		this.id = id;
		this.imageHtml = imageHtml;
		this.info = info;
		this.nickname = nickname;
	}
	public ProfileDto(String id, String imageHtml, String info) {
		super();
		this.id = id;
		this.imageHtml = imageHtml;
		this.info = info;
	}
	public ProfileDto(String id, String info) {
		super();
		this.id = id;
		this.info = info;
	}
	
	
	public ProfileDto(String id) {
		super();
		this.id = id;
	}
	
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "ProfileDto [id=" + id + ", info=" + info + "]";
	}
	
	
}
