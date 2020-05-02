package com.chex.model.place;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UsersVisited {

	@Id
	@GeneratedValue
	private Long id;
	private Long iduser;
	private String idplace;
	private Date date;
	
	public UsersVisited() {
	}

	public UsersVisited(Long iduser, String idplace, Date date) {
		this.iduser = iduser;
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
		return "UsersVisited [id=" + id + ", iduser=" + iduser + ", idplace=" + idplace + ", date=" + date + "]";
	}	
}
