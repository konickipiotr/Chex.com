package com.chex.admin.achievement;

public class PlaceListModel {

	private String idplace;
	private String name;
	private String category;
	
	public PlaceListModel() {
	}

	public PlaceListModel(String idplace, String name, String category) {
		this.idplace = idplace;
		this.name = name;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "PlaceListModel [idplace=" + idplace + ", name=" + name + ", category=" + category + "]";
	}
}
