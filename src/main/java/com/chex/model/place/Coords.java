package com.chex.model.place;

public class Coords {
	public double latitude;
	public double longitude;	
	
	public Coords() {
	}

	public Coords(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Coords [latitude=" + latitude + ", longitude=" + longitude + "]";
	}	
}
