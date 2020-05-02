package com.chex.user.achievement;

import com.chex.model.achievement.Achievement;

import java.util.List;

import javax.persistence.Lob;

public class AchievementView {
    private Long idachievement;
    private String name;
    private int points;
    private String imgurl;
    private String group;
    private String description;

    private int current;
    private int max;
    private List<PlaceAchievement> places;
    

    public AchievementView() {
    }


	public AchievementView(Achievement a) {
		super();
		this.idachievement = a.getIdachievement();
		this.name = a.getName();
		this.points = a.getPoints();
		this.imgurl = a.getImgurl();
		this.group = a.getGroup();
		this.description = a.getDescription();
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


	public int getCurrent() {
		return current;
	}


	public void setCurrent(int current) {
		this.current = current;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public List<PlaceAchievement> getPlaces() {
		return places;
	}


	public void setPlaces(List<PlaceAchievement> places) {
		this.places = places;
	}


	@Override
	public String toString() {
		return "AchievementView [idachievement=" + idachievement + ", name=" + name + ", points=" + points + ", imgurl="
				+ imgurl + ", group=" + group + ", description=" + description + ", current=" + current + ", max=" + max
				+ ", places=" + places + "]";
	}
}
