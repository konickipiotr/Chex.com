package com.chex.achievement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.db.achievement.UsersAchievementsRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.AchievementPlaces;
import com.chex.model.achievement.UsersAchievInProgress;
import com.chex.model.achievement.UsersAchievements;
import com.chex.model.user.achievement.AchievementService;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AchievementServiceTest {
	
	Long iduser = 1001l;
	String idplace = "100l";
	
	@Autowired
	private AchievementService achievementservice;
	@Autowired
	private AchievementRepo achievementRepo;
	@Autowired
	private AchievementPlacesRepo achievementPlaceRepo;
	@Autowired
	private UsersAchievInProgressRepo inPorogressRepo;
	@Autowired
	private UsersAchievementsRepo userARepo;
	
	Achievement a1 = new Achievement( "Gory", 10, "", "", "");
	Achievement a2 = new Achievement( "Morze", 20, "", "", "");
	Achievement a3 = new Achievement( "Las", 30, "", "", "");
	
	@BeforeEach
	void setUp() {
		this.achievementPlaceRepo.deleteAll();
		this.achievementRepo.deleteAll();
		this.userARepo.deleteAll();
		this.inPorogressRepo.deleteAll();
		
		this.achievementRepo.saveAll(Arrays.asList(a1,a2,a3));
		AchievementPlaces ap1 = new AchievementPlaces(1l, "1l");
		AchievementPlaces ap2 = new AchievementPlaces(1l, "2l");
		AchievementPlaces ap3 = new AchievementPlaces(1l, "6l");
		AchievementPlaces ap4 = new AchievementPlaces(2l, "4l");
		AchievementPlaces ap5 = new AchievementPlaces(2l, "5l");
		AchievementPlaces ap6 = new AchievementPlaces(3l, "6l");
		this.achievementPlaceRepo.saveAll(Arrays.asList(ap1, ap2, ap3, ap4, ap5, ap6));
	}

	@Test
	public void emptyIdAchievementInListReturnEmptyAchievmentOutList() {
		List<Long> idachievement = new ArrayList<>();
		List<Achievement> expect = new ArrayList<>();
		
		List<Achievement> actual = achievementservice.placeAchievementProcessing(idachievement, idplace, iduser);
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void achievementContainOnePlace() {
		Long aid = 3l;
		List<Long> idachievement = new ArrayList<>();
		idachievement.add(aid);
		
		Long iduser = 101l;
		List<Achievement> actual = achievementservice.placeAchievementProcessing(idachievement, "6l", iduser);
		
		assertEquals(1, actual.size());
		assertEquals(a3.getIdachievement(),actual.get(0).getIdachievement());
		assertEquals(a3.getName(), actual.get(0).getName());
		
		List<UsersAchievements> actualA = this.userARepo.findAll();
		assertEquals(1, actualA.size());
		assertEquals(aid, actualA.get(0).getIdachievement());
	}
	
	@Test
	public void addOnePlaceToAchievementContainsTwoElements() {
		List<Long> idachievement = new ArrayList<>();
		idachievement.add(2l);
		
		Long iduser = 101l;
		List<Achievement> actual = achievementservice.placeAchievementProcessing(idachievement, "4l", iduser);
		
		assertEquals(0, actual.size());
	}
	
	@Test
	public void addlastPlaceToAchievementContainsTreeElements() {
		Long iduser = 101l;
		UsersAchievInProgress p1 = new UsersAchievInProgress(iduser, 1l, "1l", Date.valueOf("2020-01-01"));
		UsersAchievInProgress p2 = new UsersAchievInProgress(iduser, 1l, "2l", Date.valueOf("2020-01-02"));
		this.inPorogressRepo.saveAll(Arrays.asList(p1, p2));
		
		List<Long> idachievement = new ArrayList<>();
		idachievement.add(1l);
		
		
		List<Achievement> actual = achievementservice.placeAchievementProcessing(idachievement, "6l", iduser);
		
		assertEquals(1, actual.size());
		assertEquals(a1.getIdachievement(),actual.get(0).getIdachievement());
		assertEquals(a1.getName(), actual.get(0).getName());
	}

	
	@Test
	public void addOnePlaceReturnTwoAchievement() {
		Long iduser = 101l;
		UsersAchievInProgress p1 = new UsersAchievInProgress(iduser, 1l, "1l", Date.valueOf("2020-01-01"));
		UsersAchievInProgress p2 = new UsersAchievInProgress(iduser, 1l, "2l", Date.valueOf("2020-01-02"));
		this.inPorogressRepo.saveAll(Arrays.asList(p1, p2));
		
		List<Long> idachievement = new ArrayList<>();
		idachievement.add(1l);
		idachievement.add(3l);
		
		List<Achievement> actual = achievementservice.placeAchievementProcessing(idachievement, "6l", iduser);
		
		assertEquals(2, actual.size());
		
		assertEquals(a1.getIdachievement(),actual.get(0).getIdachievement());
		assertEquals(a1.getName(), actual.get(0).getName());
		assertEquals(a3.getIdachievement(),actual.get(1).getIdachievement());
		assertEquals(a3.getName(), actual.get(1).getName());
	}
	

}
