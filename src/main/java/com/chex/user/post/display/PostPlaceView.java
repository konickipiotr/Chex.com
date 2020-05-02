package com.chex.user.post.display;

import java.sql.Date;
import java.util.List;

import com.chex.user.album.Photo;
import com.chex.user.comment.Comment;
import com.chex.user.post.PostStatus;

public class PostPlaceView {
	
	private Long postid;
	private Date date;
	private String name;
	private Long userid;
	private String placename;
	private Long placeid;
	private String content;
	private String photourl;
	private List<Photo> photos;
	private List<Comment> coments;
	private PostStatus postStatus;
	
	public PostPlaceView() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Comment> getComents() {
		return coments;
	}

	public void setComents(List<Comment> coments) {
		this.coments = coments;
	}

	public PostStatus getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(PostStatus postStatus) {
		this.postStatus = postStatus;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getPlaceid() {
		return placeid;
	}

	public void setPlaceid(Long placeid) {
		this.placeid = placeid;
	}
}
