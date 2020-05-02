package com.chex.place;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chex.db.place.PlaceDetailRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.PlaceDetails;
import com.chex.user.place.CheckPlaceService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CheckPlaceServiceTest {
	
	@Autowired
	private PlaceRepo placeRepo;
	
	@Autowired
	private UsersVisitedRepo usersVistedRepo;
	@Autowired
	private PlaceDetailRepo placeDetailRepo;
	
	@Autowired
	private CheckPlaceService checkPlaceService;
	
	Place p1 = new Place("EUPLDLSREG0001", "Śnieżka", 50.736038, 15.739837, 20, "góry", "2222",  100, 3);
	Place p2 = new Place("EUPLDLSREG0002", "Ślężka", 50.865434, 16.708550, 20, "góry", "2222",  50, 2);
	Place p3 = new Place("EUPLDLSWRO0001", "Katerdra", 51.114891, 17.043851, 20, "budowle", "111",  20, 1);
	Place p5 = new Place("P5", "P5", 0.000050, 0.000020, 20, "góry", "2222",  100, 3);
	Place p6 = new Place("P6", "P6", 0.000080, 0.000010, 20, "góry", "2222",  100, 3);
	
	@BeforeEach
	void initializeDb() {
		this.placeRepo.deleteAll();
		this.placeDetailRepo.deleteAll();
		
		this.placeRepo.saveAll(Arrays.asList(p1, p2, p3, p5, p6));
		
		PlaceDetails pd1 = new PlaceDetails(p1.getIdplace(), "Najwyższa góra w Karkonoszach" , "");
		PlaceDetails pd2 = new PlaceDetails(p2.getIdplace(), "Pogańska góra" , "");
		PlaceDetails pd3 = new PlaceDetails(p1.getIdplace(), "Katedra na ostrowie Tumskim", "");
		this.placeDetailRepo.saveAll(Arrays.asList(pd1, pd2, pd3));
	}

	@BeforeEach
	void setUp() throws Exception {
		this.usersVistedRepo.deleteAll();
	}

	@Test
	void ifPlaceDoesntExistInDbReturnNull() {
		Coords currentCoords = new Coords(100.123123, 200.321321);

		Place place = checkPlaceService.checkPlace(currentCoords);
		
		assertNull(place);
	}
	
	@Test
	void nothingMachtToCurrenPoint() {
		Coords currentCoords = new Coords(60.123123, 80.321321);

		assertNull(checkPlaceService.checkPlace(currentCoords));
	}
	
	@Test
	public void placeMachToPlaceCoords() {
		Coords currentCoords = new Coords(50.736038, 15.739837);		
		Place place = checkPlaceService.checkPlace(currentCoords);
		
		assertNotNull(place);
		assertEquals(p1.getIdplace(), place.getIdplace());
		assertEquals(p1.getName(), place.getName());
	}
	
	@Test
	public void placeDoesntMatchToPlaceCoords() {
		Coords currentCoords = new Coords(50.736238, 15.739837);		
		Place place = checkPlaceService.checkPlace(currentCoords);
		
		assertNull(place);
	}
	
	//@Test //private
//	public void testPrivatemethodCoordsWiethOffset() {
//		Coords currentCoords = new Coords(50.000200, 15.000300);
//		List<Coords> newcoords = checkPlaceService.coordsWithoffset(currentCoords);
//		
//		assertEquals(50.000100, newcoords.get(0).latitude);
//		assertEquals(50.000300, newcoords.get(1).latitude);
//		assertEquals(15.000200, newcoords.get(0).longitude);
//		assertEquals(15.000400, newcoords.get(1).longitude);
//		
//		currentCoords = new Coords(-50.000200, -15.000300);
//		newcoords = checkPlaceService.coordsWithoffset(currentCoords);
//		
//		assertEquals(-50.000300, newcoords.get(0).latitude);
//		assertEquals(-50.000100, newcoords.get(1).latitude);
//		assertEquals(-15.000400, newcoords.get(0).longitude);
//		assertEquals(-15.000200, newcoords.get(1).longitude);
//		
//		System.out.println("Private on border");
//		currentCoords = new Coords(0.00000, 0.000000);
//		newcoords = checkPlaceService.coordsWithoffset(currentCoords);
//		
//		assertEquals(-0.000100, newcoords.get(0).latitude);
//		assertEquals( 0.000100, newcoords.get(1).latitude);
//		assertEquals(-0.000100, newcoords.get(0).longitude);
//		assertEquals( 0.000100, newcoords.get(1).longitude);
//		
//		currentCoords = new Coords(0.000020, 0.000020);
//		newcoords = checkPlaceService.coordsWithoffset(currentCoords);
//		
//		assertEquals(-0.000080, newcoords.get(0).latitude);
//		assertEquals( 0.000120, newcoords.get(1).latitude);
//		assertEquals(-0.000080, newcoords.get(0).longitude);
//		assertEquals( 0.000120, newcoords.get(1).longitude);
//	}
	

}
