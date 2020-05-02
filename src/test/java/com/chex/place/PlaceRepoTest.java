package com.chex.place;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PlaceRepoTest {
	
	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private UsersVisitedRepo usersVisitedRepo;

	Place p1 = new Place("EUPLDLSREG0001", "P1", 50.000100, 15.000100, 40, "góry", "2222",  100, 3);
	Place p2 = new Place("EUPLDLSREG0001", "P2", 50.000200, 15.000200, 20, "góry", "2222",  100, 3);
	Place p3 = new Place("EUPLDLSREG0001", "P3", 50.000300, 15.000300, 20, "góry", "2222",  100, 3);
	Place p4 = new Place("EUPLDLSREG0001", "P4", 50.000400, 15.000400, 20, "góry", "2222",  100, 3);
	@BeforeEach
	void setUp() throws Exception {
		this.placeRepo.deleteAll();
		this.placeRepo.saveAll(Arrays.asList(p1,p2,p3,p4));
	}

	@Test
	public void testForOnePlace() {
		Coords inputNegative = new Coords(50.000050, 15.000050);
		Coords input = new Coords(50.000150, 15.000150);

		List<Place> output = placeRepo.filterCoords(input.latitude, inputNegative.latitude,inputNegative.longitude, input.longitude);

		assertEquals(1, output.size());
		assertEquals(p1.getIdplace(), output.get(0).getIdplace());
		assertEquals(p1.getName(), output.get(0).getName());	
	}
	
	@Test
	public void baseOninputFunctionReturnTwoPlaces() {
		Coords inputNegative = new Coords(50.000150, 15.000150);
		Coords input = new Coords(50.000350, 15.000350);
		
		List<Place> output = placeRepo.filterCoords(input.latitude, inputNegative.latitude,inputNegative.longitude, input.longitude);

		assertEquals(2, output.size());
		assertEquals(p2.getIdplace(), output.get(0).getIdplace());
		assertEquals(p2.getName(), output.get(0).getName());	

		assertEquals(p3.getIdplace(), output.get(1).getIdplace());
		assertEquals(p3.getName(), output.get(1).getName());
	}
	
	@Test
	void testUserVisitedRepot_existByPlaceId() {
		UsersVisited visited = new UsersVisited(1l, "AAA", Date.valueOf("2020-03-01"));
		assertFalse(this.usersVisitedRepo.existsByIdplace(visited.getIdplace()));
		
		this.usersVisitedRepo.save(visited);
		assertTrue(this.usersVisitedRepo.existsByIdplace(visited.getIdplace()));
	}

}
