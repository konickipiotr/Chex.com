package com.chex.model.achievement;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UsersAchievInProgress {

	@Id
	@GeneratedValue
	private Long id;
	private Long iduser;
	private Long idachievement;
	private String idplace;
	private Date date;
	
	public UsersAchievInProgress() {
	}

	public UsersAchievInProgress(Long iduser, Long idachievement, String idplace, Date date) {
		this.iduser = iduser;
		this.idachievement = idachievement;
		this.idplace = idplace;
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

	public String getIdplace() {
		return idplace;
	}

	public void setIdplace(String idplace) {
		this.idplace = idplace;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UsersAchievInProgress [id=" + id + ", iduser=" + iduser + ", idachievement=" + idachievement
				+ ", idplace=" + idplace + ", date=" + date + "]";
	}
}
