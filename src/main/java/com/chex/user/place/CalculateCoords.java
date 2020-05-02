package com.chex.user.place;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.chex.model.place.Coords;
import com.chex.model.place.Place;

public class CalculateCoords {
	

	public Place isInRange(Coords currentCoords, List<Place> allplaces) {

		for(Place p : allplaces) {
			double distance = calculateDistance(new Coords(p.getLatitude(), p.getLongitude()), currentCoords);
			if(distance <= p.getRadius())
				return p;
		}
		return null;
	}
	
	private double calculateDistance(Coords place, Coords point){
		
		double latPoint1 = place.latitude;
		double lngPoint1 = place.longitude;	
		double latPoint2 = point.latitude;
		double lngPoint2 = point.longitude;	

	    final double EARTH_RADIUS = 6371.0; //km value;

	    //converting to radians
	    latPoint1 = Math.toRadians(latPoint1);
	    lngPoint1 = Math.toRadians(lngPoint1);
	    latPoint2 = Math.toRadians(latPoint2);
	    lngPoint2 = Math.toRadians(lngPoint2);

	    double distance = Math.pow(Math.sin((latPoint2 - latPoint1) / 2.0), 2) 
	            + Math.cos(latPoint1) * Math.cos(latPoint2)
	            * Math.pow(Math.sin((lngPoint2 - lngPoint1) / 2.0), 2);
	    distance = 2.0 * EARTH_RADIUS * Math.asin(Math.sqrt(distance));
	    distance *= 1000;
	    
	    distance = BigDecimal.valueOf(distance).setScale(2, RoundingMode.HALF_UP).doubleValue();

	    return distance;
	}
}
