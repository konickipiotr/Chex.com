package com.chex.model.place;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Place {

	@Id
	@GeneratedValue
	private Long id;
	private String idplace;
	private String name;
	private double latitude;
	private double longitude;
	private double radius;
	private String category;
	private String achievements;
	private int points;
	private int difficultylevel;
	
	public Place() {
		this.latitude = 1000;
		this.longitude = 1000;
		this.radius = 0;
	}

	public Place(String idplace, String name, double latitude, double longitude, double radius,
			String category, String achievements, int points, int difficultylevel) {
		this.idplace = idplace;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.category = category;
		this.achievements = achievements;
		this.points = points;
		this.difficultylevel = difficultylevel;
	}
	
	public void addAchievement(Long id) {
		String sid = String.valueOf(id);
		if(this.achievements == null || this.achievements.isBlank()) 
			this.achievements = sid;
		else
			this.achievements += ":" + sid;
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

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
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

	@Override
	public String toString() {
		return "Place [id=" + id + ", idplace=" + idplace + ", name=" + name + ", latitude=" + latitude + ", longitude="
				+ longitude + ", radius=" + radius + ", category=" + category + ", achievements=" + achievements
				+ ", points=" + points + ", difficultylevel=" + difficultylevel + "]";
	}
}
