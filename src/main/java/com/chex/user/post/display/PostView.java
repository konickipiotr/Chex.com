package com.chex.user.post.display;

import java.sql.Date;

public class PostView {
	
	private Long postid;
	private Date date;
	private Long userid;
	private String name;
	private String photourl;
	private String elementname;
	private String pathname;
	private String content;
	
	public PostView() {
	}
	

	public PostView(Long postid, Date date, Long userid, String name, String photourl, String elementname,
			String pathname, String content) {
		super();
		this.postid = postid;
		this.date = date;
		this.userid = userid;
		this.name = name;
		this.photourl = photourl;
		this.elementname = elementname;
		this.pathname = pathname;
		this.content = content;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public String getElementname() {
		return elementname;
	}

	public void setElementname(String elementname) {
		this.elementname = elementname;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ViewPost [postid=" + postid + ", date=" + date + ", userid=" + userid + ", name=" + name + ", photourl="
				+ photourl + ", elementname=" + elementname + ", pathname=" + pathname + ", content=" + content + "]";
	}	
}
