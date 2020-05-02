package com.chex.db.achievement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.achievement.AchievementPlaces;

public interface AchievementPlacesRepo extends JpaRepository<AchievementPlaces, Long> {
	
	List<AchievementPlaces> findByIdachievement(Long idachievement);

}
