package com.chex.user.album;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue
	private Long photoid;
	private Long postid;
	private Long userid;
	private String photourl;
	private String keywords;
	
	public Photo() {
	}

	public Photo(Long postid, Long userid, String photourl, String keywords) {
		this.postid = postid;
		this.userid = userid;
		this.photourl = photourl;
		this.keywords = keywords;
	}

	public Long getPhotoid() {
		return photoid;
	}

	public void setPhotoid(Long photoid) {
		this.photoid = photoid;
	}

	public Long getPostid() {
		return postid;
	}

	public void setPostid(Long postid) {
		this.postid = postid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	
}
