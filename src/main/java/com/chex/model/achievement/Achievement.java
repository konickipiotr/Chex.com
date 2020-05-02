package com.chex.model.achievement;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Achievement {

	@Id
	@GeneratedValue
	private Long idachievement;
	private String name;
	private int points;
	@Lob
	private String imgurl;
	private String group;
	@Lob
	private String description;
	@Transient
	private Date date;
	
	public Achievement() {
	}

	public Achievement(String name, int points, String imgurl, String group, String description) {
		this.name = name;
		this.points = points;
		this.imgurl = imgurl;
		this.group = group;
		this.description = description;
	}

	public Long getIdachievement() {
		return idachievement;
	}

	public void setIdachievement(Long idachievement) {
		this.idachievement = idachievement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Achievement [idachievement=" + idachievement + ", name=" + name + ", points=" + points + ", imgurl="
				+ imgurl + ", group=" + group + ", description=" + description + ", date=" + date + "]";
	}
}
