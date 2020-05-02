package com.chex.model.achievement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AchievementPlaces {

	@Id
	@GeneratedValue
	private Long id;
	private Long idachievement;
	private String idplace;
	
	public AchievementPlaces() {
	}

	public AchievementPlaces(Long idachievement, String idplace) {
		this.idachievement = idachievement;
		this.idplace = idplace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdachievement() {
		return idachievement;
	}

	public void setIdachievement(Long idachievement) {
		this.idachievement = idachievement;
	}

	public String getIdplace() {
		return idplace;
	}

	public void setIdplace(String idplace) {
		this.idplace = idplace;
	}

	@Override
	public String toString() {
		return "AchievementPlaces [id=" + id + ", idachievement=" + idachievement + ", idplace=" + idplace + "]";
	}
}
