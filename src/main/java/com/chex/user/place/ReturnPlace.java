package com.chex.user.place;

import java.util.List;

import com.chex.model.achievement.Achievement;
import com.chex.model.place.Place;

public class ReturnPlace {

	private Place place;
	private AddPlaceStatus placeStatus;
	private List<Place> parentPlaceList;
	private List<Achievement> achievementList;
	private boolean isnewlevel;
	private int points;
	
	public ReturnPlace() {
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public AddPlaceStatus getPlaceStatus() {
		return placeStatus;
	}

	public void setPlaceStatus(AddPlaceStatus placeStatus) {
		this.placeStatus = placeStatus;
	}

	public List<Place> getParentPlaceList() {
		return parentPlaceList;
	}

	public void setParentPlaceList(List<Place> parentPlaceList) {
		this.parentPlaceList = parentPlaceList;
	}

	public List<Achievement> getAchievementList() {
		return achievementList;
	}

	public void setAchievementList(List<Achievement> achievementList) {
		this.achievementList = achievementList;
	}

	public boolean isIsnewlevel() {
		return isnewlevel;
	}

	public void setIsnewlevel(boolean isnewlevel) {
		this.isnewlevel = isnewlevel;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "ReturnPlace [place=" + place + ", placeStatus=" + placeStatus + ", parentPlaceList=" + parentPlaceList
				+ ", achievementList=" + achievementList + ", isnewlevel=" + isnewlevel + ", points=" + points + "]";
	}
}
