package com.chex.admin.addplace;

public class ParentPlaceModel {
	
	private String id;
	private String parentid;
	private String name;
	private String category;
	private String description;
	
	public ParentPlaceModel() {
	}

	public ParentPlaceModel(String id, String parentid, String name, String category, String description) {
		this.id = id;
		this.parentid = parentid;
		this.name = name;
		this.category = category;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ParentPlaceModel [id=" + id + ", parentid=" + parentid + ", name=" + name + ", category=" + category
				+ ", description=" + description + "]";
	}	
}
