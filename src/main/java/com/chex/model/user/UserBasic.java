package com.chex.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserBasic {

	@Id
	private Long iduser;
	private String firstname;
	private String lastname;
	private String gender;
	private String city;
	private int exp;
	private int level;
	
	public UserBasic() {
		this.exp = 0;
		this.level = 1;
	}

	public UserBasic(Long iduser, String firstname, String lastname, String gender, String city) {
		this.iduser = iduser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.city = city;
		this.exp = 0;
		this.level = 1;
	}
	
	public String getName() {
		return firstname + " " + lastname;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "UserBasic [iduser=" + iduser + ", firstname=" + firstname + ", lastname=" + lastname + ", gender="
				+ gender + ", city=" + city + ", exp=" + exp + ", level=" + level + "]";
	}
}
