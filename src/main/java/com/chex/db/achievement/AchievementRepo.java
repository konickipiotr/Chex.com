package com.chex.db.achievement;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.achievement.Achievement;

public interface AchievementRepo extends JpaRepository<Achievement, Long> {
	
	boolean existsByName(String name);

}
