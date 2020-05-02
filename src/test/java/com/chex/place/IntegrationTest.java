package com.chex.place;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.db.achievement.UsersAchievementsRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.UsersAchievInProgress;
import com.chex.model.achievement.UsersAchievements;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;
import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;
import com.chex.user.place.AddPlaceService;
import com.chex.user.place.AddPlaceStatus;
import com.chex.user.place.ReturnPlace;
import com.chex.utils.IdUtils;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class IntegrationTest {
	
	private AddPlaceService service;
	private UserAuthRepo userAuthRepo;
	private UserBasicRepo userBasicRepo;
	private PlaceRepo placeRepo;
	private UsersVisitedRepo placeVisitedRepo;
	private AchievementRepo achievementRepo;
	private AchievementPlacesRepo achiePlavceRepo;
	private UsersAchievementsRepo usersAchievementsRepo;
	private UsersAchievInProgressRepo userAInProgress;
	
	@Autowired
	public IntegrationTest(AddPlaceService service, UserAuthRepo userAuthRepo, UserBasicRepo userBasicRepo,
			PlaceRepo placeRepo, UsersVisitedRepo placeVisitedRepo, AchievementRepo achievementRepo,
			AchievementPlacesRepo achiePlavceRepo, UsersAchievementsRepo usersAchievementsRepo,
			UsersAchievInProgressRepo userAInProgress) {
		super();
		this.service = service;
		this.userAuthRepo = userAuthRepo;
		this.userBasicRepo = userBasicRepo;
		this.placeRepo = placeRepo;
		this.placeVisitedRepo = placeVisitedRepo;
		this.achievementRepo = achievementRepo;
		this.achiePlavceRepo = achiePlavceRepo;
		this.usersAchievementsRepo = usersAchievementsRepo;
		this.userAInProgress = userAInProgress;
	}

	private UserAuth ua1 = new UserAuth("user1", "pass", "USER", 2);
	private UserAuth ua2 = new UserAuth("user2", "pass", "USER", 2);
	private UserBasic ub1 = new UserBasic(0l, "Jan", "Nowa", "M", "London");
	private UserBasic ub2 = new UserBasic(0l, "Adam", "Kowalski", "M", "Kraków");
	
	@BeforeEach
	public void initDB() {
		userAuthRepo.deleteAll();
		userBasicRepo.deleteAll();
		userAuthRepo.saveAll(Arrays.asList(ua1, ua2));
		ub1.setIduser(ua1.getIduser());
		ub2.setIduser(ua2.getIduser());
		userBasicRepo.saveAll(Arrays.asList(ub1, ub2));
		usersAchievementsRepo.deleteAll();
		userAInProgress.deleteAll();
		placeVisitedRepo.deleteAll();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	//@Test
	void bb() {
		Coords coord = new Coords(-40.4444, -87.5466);
		ReturnPlace rp = service.addPlace(coord, ua1.getIduser());
		
		assertEquals(AddPlaceStatus.NOTEXIST, rp.getPlaceStatus());
		assertNull(rp.getParentPlaceList());
		assertNull(rp.getAchievementList());
	}

	//@Test
	void aa() {
		UserBasic ub = userBasicRepo.findById(ua1.getIduser()).orElse(null);
		assertEquals(0, ub.getExp());
		
		Place p = new Place("EUPL0DLS0000005", "Jaskinie Niedźwiedzia", 50.234488, 16.842555, 20, "jaskinia", "456", 100, 3);
		Achievement a = new Achievement("Grotołaz", 300, "", "", "");
		Coords coord = new Coords(p.getLatitude(), p.getLongitude());
		ReturnPlace rp = service.addPlace(coord, ua1.getIduser());
		
		assertEquals(p.getIdplace(), rp.getPlace().getIdplace());
		assertEquals(p.getName(), rp.getPlace().getName());
		
		assertNotNull(rp.getParentPlaceList());
		assertNotNull(rp.getAchievementList());

		List<Place> paretnList = rp.getParentPlaceList();
		assertEquals(IdUtils.getContinentId(p.getIdplace()), paretnList.get(0).getIdplace());
		assertEquals(IdUtils.getCountryId(p.getIdplace()), paretnList.get(1).getIdplace());
		assertEquals(IdUtils.getRegionId(p.getIdplace()), paretnList.get(2).getIdplace());
		assertEquals(IdUtils.getSubregionId(p.getIdplace()), paretnList.get(2).getIdplace());
		
		List<Achievement> achievelist = rp.getAchievementList();
		assertEquals(a.getIdachievement(), achievelist.get(0).getIdachievement());
		assertEquals(a.getName(), achievelist.get(0).getName());
		
		UserBasic ubb = userBasicRepo.findById(ua1.getIduser()).orElse(null);
		assertEquals(800, ubb.getExp());
		
		List<UsersAchievements> usersAList = usersAchievementsRepo.findByIduser(ua1.getIduser());
		assertEquals(1, usersAList.size());
		assertEquals(456l, usersAList.get(0).getIdachievement());
		
		List<UsersVisited> placeinDB = placeVisitedRepo.findByIduser(ua1.getIduser());
		assertEquals(p.getIdplace(), placeinDB.get(0).getIdplace());
		
		ReturnPlace rp2 = service.addPlace(coord, ua1.getIduser());
		assertEquals(AddPlaceStatus.EXIST, rp2.getPlaceStatus());
		
	}
	
	//@Test
	public void fullTest() {
		Long iduser = ua1.getIduser();		
		Place p1 = new Place("EUPL0MAPKRK0001", "Kopitec Kościuszki", 50.054821, 19.893242, 20, "punkt widokowy", "666;777", 100, 1);
		Place p2 = new Place("EUPL0MAPKRK0002", "Kopitec Krakusa", 50.038060, 19.958429, 20, "punkt widokowy", "666;777", 100, 1);
		Place p3 = new Place("EUPL0MAPKRK0003", "Kopitec Wandy", 50.070199, 20.068088, 20, "punkt widokowy", "666;777", 100, 1);
		Place p4 = new Place("EUPL0MAPKRK0004", "Wawel", 50.053981, 19.935143, 100, "historia", "666", 100, 1);
		
		Achievement a1 = new Achievement( "Krakus", 300, "", "", "Odziedź wsztstkie punkry w Krakowie");
		Achievement a2 = new Achievement( "Zdobywca kopuców", 300, "", "", "Odzwiedź wsztstkoe kopce w Krakowie");
		Coords coord = new Coords(p1.getLatitude(), p1.getLongitude());
		
		//---------------------------------------------------------------------------------------
		List<UsersAchievements> ua_list = usersAchievementsRepo.findByIduser(iduser);
		List<UsersAchievInProgress> uaip_list = userAInProgress.findByIduser(iduser);
		List<UsersVisited> uv_list = placeVisitedRepo.findByIduser(iduser);
		
		assertEquals(0, ua_list.size());
		assertEquals(0, uaip_list.size());
		assertEquals(0, uv_list.size());
		//---------------------------------------------------------------------------------------
		//PROCESS
		ReturnPlace rp = service.addPlace(coord, ua1.getIduser());
		//---------------------------------------------------------------------------------------
		
		ua_list = usersAchievementsRepo.findByIduser(iduser);
		uaip_list = userAInProgress.findByIduser(iduser);
		uv_list = placeVisitedRepo.findByIduser(iduser);

		assertEquals(0, ua_list.size());
		assertEquals(4, uaip_list.size());
		assertEquals(5, uv_list.size());
		
		assertEquals(AddPlaceStatus.SUCCESS, rp.getPlaceStatus().SUCCESS);
		assertEquals(p1.getIdplace(), rp.getPlace().getIdplace());
		
		

	}

}

