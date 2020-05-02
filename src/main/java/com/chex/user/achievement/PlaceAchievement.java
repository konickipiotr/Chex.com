package com.chex.user.achievement;

public class PlaceAchievement {
	
	private String idplace;
	private String name;
	private boolean isAchieved;
	
	public PlaceAchievement() {
	}
	public PlaceAchievement(String idplace, String name, boolean isAchieved) {
		this.idplace = idplace;
		this.name = name;
		this.isAchieved = isAchieved;
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
	public boolean isAchieved() {
		return isAchieved;
	}
	public void setAchieved(boolean isAchieved) {
		this.isAchieved = isAchieved;
	}
}
