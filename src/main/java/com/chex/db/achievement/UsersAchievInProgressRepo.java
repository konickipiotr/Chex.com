package com.chex.db.achievement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.achievement.UsersAchievInProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersAchievInProgressRepo extends JpaRepository<UsersAchievInProgress, Long> {
	
	List<UsersAchievInProgress> findByIduser(Long iduser);
	List<UsersAchievInProgress> findByIdachievement(Long idachievement);
	List<UsersAchievInProgress> findByIduserAndIdachievement(Long l, Long m);

	@Query(value = "select count(*) from UsersAchievInProgress where iduser=:idu and idachievement=:ida")
	Integer numberOfAchievementPlaces(@Param("ida") Long ida, @Param("idu") Long idu);

}



