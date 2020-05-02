package com.chex.model.achievement;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UsersAchievements {

	@Id
	@GeneratedValue
	private Long id;
	private Long iduser;
	private Long idachievement;
	private Date date;
	
	public UsersAchievements() {
	}

	public UsersAchievements(Long iduser, Long idachievement, Date date) {
		this.iduser = iduser;
		this.idachievement = idachievement;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Long getIdachievement() {
		return idachievement;
	}

	public void setIdachievement(Long idachievement) {
		this.idachievement = idachievement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UsersAchievements [id=" + id + ", iduser=" + iduser + ", idachievement=" + idachievement + ", date="
				+ date + "]";
	}
}
