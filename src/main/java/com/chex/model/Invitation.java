package com.chex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pk;
	private Long userid;
	private Long inviters;
	
	public Invitation() {
	}

	public Invitation(Long userid, Long inviters) {
		this.userid = userid;
		this.inviters = inviters;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getInviters() {
		return inviters;
	}

	public void setInviters(Long inviters) {
		this.inviters = inviters;
	}

	@Override
	public String toString() {
		return "Invitation [pk=" + pk + ", userid=" + userid + ", inviters=" + inviters + "]";
	}
}
