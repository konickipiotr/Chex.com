package com.chex.db.achievement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.achievement.UsersAchievements;

public interface UsersAchievementsRepo extends JpaRepository<UsersAchievements, Long> {

	List<UsersAchievements> findByIduser(Long iduser);
}
