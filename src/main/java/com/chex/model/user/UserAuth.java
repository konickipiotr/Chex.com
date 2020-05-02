package com.chex.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAuth {

	@Id
	@GeneratedValue
	private Long iduser;
	private String username;
	private String password;
	private String role;
	private int status;
	
	public UserAuth() {
	}

	public UserAuth( String username, String password, String role, int status) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAuth [iduser=" + iduser + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", status=" + status + "]";
	}
}
