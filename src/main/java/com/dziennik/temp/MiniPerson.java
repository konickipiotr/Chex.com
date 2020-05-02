package com.chex.user.friends;

public class MiniPerson {
	
	private Long userid;
	private String publicname;
	private String photo;
	private String personStatus;
	
	public MiniPerson() {
	}
	
	public MiniPerson(Long userid, String publicname, String photo, String personStatus) {
		this.userid = userid;
		this.publicname = publicname;
		this.photo = photo;
		this.personStatus = personStatus;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPublicname() {
		return publicname;
	}

	public void setPublicname(String publicname) {
		this.publicname = publicname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	@Override
	public String toString() {
		return "MiniPerson [userid=" + userid + ", publicname=" + publicname + ", photo=" + photo + ", personStatus="
				+ personStatus + "]";
	}
}
