package com.chex.user.place;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chex.db.place.PlaceRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;

@Service
public class CheckPlaceService {
	private static final double latitudeOffset = 0.000100;
	private static final double longitudeOffset = 0.000100;
	
	@Autowired
	private PlaceRepo placeRepo;
	
	public Place checkPlace(Coords currentCoords) {
		List<Place> places = filterPlace(currentCoords);

		if(places.isEmpty())
			return null;
		Place place = new CalculateCoords().isInRange(currentCoords, places);		
		return place;
	}

	public List<Place> filterPlace(Coords currentCoords){
		List<Coords> newcoords = coordsWithoffset(currentCoords);
		Coords from = newcoords.get(0);
		Coords to = newcoords.get(1);
		 
		List<Place> filtredPlace = placeRepo.filterCoords(to.latitude, from.latitude, from.longitude, to.longitude);
		return filtredPlace;
	}
	
	private List<Coords> coordsWithoffset(Coords currentCoords) {
		Coords coordsfrom = new Coords(currentCoords.latitude, currentCoords.longitude);
		Coords coordsTo = new Coords(currentCoords.latitude, currentCoords.longitude);
		
		coordsTo.latitude = BigDecimal.valueOf(currentCoords.latitude + latitudeOffset).setScale(6, RoundingMode.HALF_UP).doubleValue(); 
		coordsfrom.latitude = BigDecimal.valueOf(currentCoords.latitude - latitudeOffset).setScale(6, RoundingMode.HALF_UP).doubleValue();

		coordsTo.longitude = BigDecimal.valueOf(currentCoords.longitude + longitudeOffset).setScale(6, RoundingMode.HALF_UP).doubleValue();
		coordsfrom.longitude = BigDecimal.valueOf(currentCoords.longitude - longitudeOffset).setScale(6, RoundingMode.HALF_UP).doubleValue();
		
		List<Coords> newCoords = Arrays.asList(coordsfrom, coordsTo);
		return newCoords;
	}
}
