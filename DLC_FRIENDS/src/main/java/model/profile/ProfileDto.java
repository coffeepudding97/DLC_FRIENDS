package model.profile;

import java.sql.Blob;

public class ProfileDto {
	private String id;
//	private Blob profileImg;
	private String imageHtml;
	private String info;
	
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
	
	@Override
	public String toString() {
		return "ProfileDto [id=" + id + ", info=" + info + "]";
	}
	
	
}
