package com.chex.model.place;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class PlaceDetails {

	@Id
	@GeneratedValue
	private Long id;
	private String idplace;
	@Lob
	private String description;
	private String imgurl;
	
	public PlaceDetails() {
	}

	public PlaceDetails(String idplace, String description, String imgurl) {
		this.idplace = idplace;
		this.description = description;
		this.imgurl = imgurl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdplace() {
		return idplace;
	}

	public void setIdplace(String idplace) {
		this.idplace = idplace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		return "PlaceDetails [id=" + id + ", idplace=" + idplace + ", description=" + description + ", imgurl=" + imgurl
				+ "]";
	}
}
