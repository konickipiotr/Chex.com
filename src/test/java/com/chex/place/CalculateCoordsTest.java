package com.chex.place;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.user.place.CalculateCoords;

class CalculateCoordsTest {

	private CalculateCoords cc = new CalculateCoords();
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	public void positionOutThePlaceArea() {
		System.out.println("positionOutThePlaceArea");
		Coords currenCoords = new Coords(52.114399, 18.007084);
		Place p1 = new Place("EUPLDLSREG0001", "P1", 51.114399, 17.007084, 14, "góry", "2222",  100, 3);
		List<Place> allplaces = Arrays.asList(p1);
		
		assertNull(cc.isInRange(currenCoords, allplaces));
	}
	
	@Test
	public void positionIsOneMeterOutOfPlaceArea() {

		System.out.println("positionIsOneMeterOutOfPlaceArea");
		Coords currenCoords = new Coords(51.114434, 17.006899);
		Place p1 = new Place("EUPLDLSREG0002", "P1", 53.736038, 13.739837, 1, "góry", "2222",  100, 3);
		Place p2 = new Place("EUPLDLSREG0001", "P2", 51.114399, 17.007084, 13, "góry", "2222",  100, 3);
		List<Place> allplaces = Arrays.asList(p1, p2);
		
		Place place = cc.isInRange(currenCoords, allplaces);
		
		assertNull(place);
	}
	
	@Test
	public void positionIsOnPlaceAreaBorder() {

		System.out.println("positionIsOnPlaceAreaBorder");
		Coords currenCoords = new Coords(51.114434, 17.006899);
		Place p1 = new Place("EUPLDLSREG0002", "P1", 53.736038, 13.739837, 1, "góry", "2222",  100, 3);
		Place p2 = new Place("EUPLDLSREG0001", "P2", 51.114399, 17.007084, 14, "góry", "2222",  100, 3);
		List<Place> allplaces = Arrays.asList(p1, p2);
		
		Place place = cc.isInRange(currenCoords, allplaces);
		
		assertNotNull(place);
	}
	
	@Test
	public void positionIsInBuilding() {
		Coords currenCoords = new Coords(51.114605, 17.006869);
		Place p1 = new Place("EUPLDLSREG0002", "P1", 53.736038, 13.739837, 1, "góry", "2222",  100, 3);
		Place p2 = new Place("EUPLDLSREG0001", "P2", 51.114399, 17.007084, 30, "góry", "2222",  100, 3);
		List<Place> allplaces = Arrays.asList(p1, p2);

		Place place = cc.isInRange(currenCoords, allplaces);

		assertNotNull(place);
		assertEquals(p2.getIdplace(), place.getIdplace());
	}
}
