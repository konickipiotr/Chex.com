package com.chex.user.post;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long postid;
	private Date date;
	private Long userid;
	private Long achievementid;
	private String placeid;
	private Long albumid;
	private PostType postType;
	private String content;
	private String photoUrl;
	private PostStatus postStatus;;
	
	public Post() {
	}
	public Post(Date date, Long userid, Long achievementid, String placeid, Long albumid,
			PostType postType, String content, String photoUrl, PostStatus postStatus) {
		this.date = date;
		this.userid = userid;
		this.achievementid = achievementid;
		this.placeid = placeid;
		this.albumid = albumid;
		this.postType = postType;
		this.content = content;
		this.photoUrl = photoUrl;
		this.postStatus = postStatus;
	}


	public Long getPostid() {
		return postid;
	}


	public void setPostid(Long postid) {
		this.postid = postid;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Long getUserid() {
		return userid;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public Long getAchievementid() {
		return achievementid;
	}


	public void setAchievementid(Long achievementid) {
		this.achievementid = achievementid;
	}


	public String getPlaceid() {
		return placeid;
	}


	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}


	public Long getAlbumid() {
		return albumid;
	}


	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}


	public PostType getPostType() {
		return postType;
	}


	public void setPostType(PostType postType) {
		this.postType = postType;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public PostStatus getPostStatus() {
		return postStatus;
	}


	public void setPostStatus(PostStatus postStatus) {
		this.postStatus = postStatus;
	}
}
