package com.chex.user.comment;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long postid;
	private Long userid;
	@Column(columnDefinition="TIMESTAMP")
	private Timestamp timestamp;
	@Lob
	private String content;
	
	public Comment() {
	}

	public Comment(Long postid, Long userid, Timestamp timestamp, String content) {
		this.postid = postid;
		this.userid = userid;
		this.timestamp = timestamp;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", postid=" + postid + ", userid=" + userid + ", timestamp=" + timestamp
				+ ", content=" + content + "]";
	}	
}
