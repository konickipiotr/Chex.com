package com.chex.place;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.user.place.AddPlaceService;
import com.chex.user.place.AddPlaceStatus;
import com.chex.user.place.CheckPlaceService;
import com.chex.user.place.ReturnPlace;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddPlaceServiceTest {

	@Mock
	private CheckPlaceService checkPlaceService;
	
	@MockBean
	private UsersVisitedRepo usersVisitedRepo;
	@MockBean
	private PlaceRepo placeRepo;
	
	@Autowired
	@InjectMocks
	@Spy
	private AddPlaceService addPlaceService;
	
	public static 
	
	Place p1 = new Place("EUPLDLSREG0001", "Śnieżka", 50.736038, 15.739837, 20, "góry", "2222",  100, 3);
	Place p2 = new Place("EUPLDLSREG0002", "Ślężka", 50.865434, 16.708550, 20, "góry", "2222",  50, 2);
	Long iduser = 101l;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void ifPlaceDoesntExistAtCurrentCoordsReturnStatusNotExist() {

		Coords currentCoords = new Coords(50.000000, 15.000000);
		when(checkPlaceService.checkPlace(currentCoords)).thenReturn(null);
		
		ReturnPlace returnPlace = addPlaceService.addPlace(currentCoords, iduser);
		
		assertEquals(AddPlaceStatus.NOTEXIST, returnPlace.getPlaceStatus());
		assertNull(returnPlace.getPlace());
	}
	
	@Test
	public void ifPlaceWasAddedBeforeReturnStatusExist() {
		Coords currentCoords = new Coords(50.736038, 15.739837);
		when(checkPlaceService.checkPlace(currentCoords)).thenReturn(p1);
		when(usersVisitedRepo.existsByIdplace(p1.getIdplace())).thenReturn(true);
		
		ReturnPlace returnPlace = addPlaceService.addPlace(currentCoords, iduser);
		
		assertEquals(AddPlaceStatus.EXIST, returnPlace.getPlaceStatus());
		assertNotNull(returnPlace.getPlace());
		assertEquals(p1.getIdplace(), returnPlace.getPlace().getIdplace());
		assertEquals(p1.getName(), returnPlace.getPlace().getName());
	}
	
	@Test
	public void ifPlaceWasntAddedBeforeReturnStatusSuccess() {
		Coords currentCoords = new Coords(50.736038, 15.739837);
		when(checkPlaceService.checkPlace(currentCoords)).thenReturn(p1);
		when(usersVisitedRepo.existsByIdplace(p1.getIdplace())).thenReturn(false);
		
		List<Place> parentPlace = new ArrayList<>();
		Mockito.doReturn(parentPlace).when(addPlaceService).getParentList(Mockito.anyString());
		ReturnPlace returnPlace = addPlaceService.addPlace(currentCoords, iduser);
		
		assertEquals(AddPlaceStatus.SUCCESS, returnPlace.getPlaceStatus());
		assertNotNull(returnPlace.getPlace());
		assertEquals(p1.getIdplace(), returnPlace.getPlace().getIdplace());
		assertEquals(p1.getName(), returnPlace.getPlace().getName());
	}
	
//	@Test //private method
//	public void placeHasntAnyAchievement() {
//		ReturnPlace rp = new ReturnPlace();
//		rp.setPlace(new Place("11", "AAA", 5, 4, 5, "", "", 0, 0));
//		
//		List<Long> actual = addPlaceService.getAchievementIds(rp);
//		
//		assertEquals(0, actual.size());
//	}
//	
//	@Test //private method
//	public void getAchievementIdFromOnlyOnePlaceWithOneAchievement() {
//		ReturnPlace rp = new ReturnPlace();
//		rp.setPlace(new Place("11", "AAA", 5, 4, 5, "", "123", 0, 0));
//		
//		List<Long> actual = addPlaceService.getAchievementIds(rp);
//		
//		assertEquals(1, actual.size());
//		assertEquals(123l, actual.get(0));
//	}
//	
//	@Test //private method
//	public void getAchievementIdFromOnlyOnePlaceWithThreeAchievements() {
//		ReturnPlace rp = new ReturnPlace();
//		rp.setPlace(new Place("11", "AAA", 5, 4, 5, "", "123:456:789", 0, 0));
//		
//		List<Long> actual = addPlaceService.getAchievementIds(rp);
//		
//		assertEquals(3, actual.size());
//		assertEquals(123l, actual.get(0));
//		assertEquals(456l, actual.get(1));
//		assertEquals(789l, actual.get(2));
//	}
//	
//	@Test //private method
//	public void getAchievementsIdFromThreePlaceWithOneAchievement() {
//		ReturnPlace rp = new ReturnPlace();
//		rp.setPlace(new Place("11", "AAA", 5, 4, 5, "", "123", 0, 0));
//		List<Place> parent = new ArrayList<>();
//		parent.add(new Place("11", "BBB", 5, 4, 5, "", "111", 0, 0));
//		parent.add(new Place("11", "CCC", 5, 4, 5, "", "222", 0, 0));
//		rp.setParentPlaceList(parent);
//		
//		List<Long> actual = addPlaceService.getAchievementIds(rp);
//		
//		assertEquals(3, actual.size());
//		assertEquals(123l, actual.get(0));
//		assertEquals(111l, actual.get(1));
//		assertEquals(222l, actual.get(2));
//	}

}
