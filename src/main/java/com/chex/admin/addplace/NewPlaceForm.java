package com.chex.admin.addplace;

public class NewPlaceForm {
	
	private String parentid;
	private String newid;
	private String name;
	private double latitude;
	private double longitude;
	private double radius;
	private String category;
	private String imgurl;
	private String description;
	private int points;
	private int difficultylevel;
	
	public NewPlaceForm() {
	}

	public NewPlaceForm(String parentid, String newid, String name, double latitude, double longitude, double radius,
			String category, String imgurl, String description, int points, int difficultylevel) {
		super();
		this.parentid = parentid;
		this.newid = newid;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.category = category;
		this.imgurl = imgurl;
		this.description = description;
		this.points = points;
		this.difficultylevel = difficultylevel;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getNewid() {
		return newid;
	}

	public void setNewid(String newid) {
		this.newid = newid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getDifficultylevel() {
		return difficultylevel;
	}

	public void setDifficultylevel(int difficultylevel) {
		this.difficultylevel = difficultylevel;
	}
}
