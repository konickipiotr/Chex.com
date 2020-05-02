package com.chex.achievement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.model.achievement.UsersAchievInProgress;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UsersAchievInProgressRepoTest {
	
	@Autowired
	private UsersAchievInProgressRepo inProgressRepo;
	
	@BeforeEach
	private void setUp() {
		inProgressRepo.deleteAll();
	}

	@Test
	public void ifIdNotExistGetEmpyList() {
		List<UsersAchievInProgress> actual =  inProgressRepo.findByIdachievement(1l);
		assertEquals(0, actual.size());
		
		actual =  inProgressRepo.findByIduser(1l);
		assertEquals(0, actual.size());
	}
	
	@Test
	public void elementExist() {
		UsersAchievInProgress uaip1 = new UsersAchievInProgress(1001l, 20l, "1l", Date.valueOf("2020-03-01"));
		uaip1.setId(9l);
		inProgressRepo.save(uaip1);
		
		List<UsersAchievInProgress> actual =  inProgressRepo.findByIdachievement(20l);
		assertEquals(1, actual.size());
		assertEquals(20l, actual.get(0).getIdachievement());
		
		actual =  inProgressRepo.findByIduser(1001l);
		assertEquals(1, actual.size());
		assertEquals(1001l, actual.get(0).getIduser());
	}
	
	@Test
	public void findByUseridAndAchievementidreturnEmptyList() {
		List<UsersAchievInProgress> actual =  inProgressRepo.findByIduserAndIdachievement(1l, 1l);
		assertEquals(0, actual.size());
	}
	
	@Test
	public void findByUseridAndAchievementidreturnAppropriateList() {
		UsersAchievInProgress p1 = new UsersAchievInProgress(1001l, 20l, "1l", Date.valueOf("2020-03-01"));
		UsersAchievInProgress p2 = new UsersAchievInProgress(1001l, 20l, "2l", Date.valueOf("2020-03-02"));
		UsersAchievInProgress p3 = new UsersAchievInProgress(1001l, 20l, "3l", Date.valueOf("2020-03-03"));
		UsersAchievInProgress p4 = new UsersAchievInProgress(1002l, 20l, "1l", Date.valueOf("2020-03-01"));
		inProgressRepo.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		List<UsersAchievInProgress> actual =  inProgressRepo.findByIduserAndIdachievement(1001l, 20l);
		assertEquals(3, actual.size());
		
		assertEquals(1001l, actual.get(0).getIduser());
		assertEquals(20l, actual.get(0).getIdachievement());
		assertEquals("1l", actual.get(0).getIdplace());
		assertEquals(Date.valueOf("2020-03-01"), actual.get(0).getDate());
		
		assertEquals(20l, actual.get(1).getIdachievement());
		assertEquals("2l", actual.get(1).getIdplace());
		assertEquals(Date.valueOf("2020-03-02"), actual.get(1).getDate());
		
		assertEquals(20l, actual.get(2).getIdachievement());
		assertEquals("3l", actual.get(2).getIdplace());
		assertEquals(Date.valueOf("2020-03-03"), actual.get(2).getDate());
	}
	


}
