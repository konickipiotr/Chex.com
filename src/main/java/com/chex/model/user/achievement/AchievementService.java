package com.chex.model.user.achievement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.db.achievement.UsersAchievementsRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.UsersAchievInProgress;
import com.chex.model.achievement.UsersAchievements;

@Service
public class AchievementService {

	
	private UsersAchievInProgressRepo inProgressRepo;
	private AchievementPlacesRepo achievplaceRepo;
	private AchievementRepo achievmentRepo;
	private UsersAchievementsRepo usersAchievementsRepo;

	
	@Autowired
	public AchievementService(UsersAchievInProgressRepo inProgressRepo, AchievementPlacesRepo achievplaceRepo,
			AchievementRepo achievmentRepo, UsersAchievementsRepo usersAchievementsRepo) {
		super();
		this.inProgressRepo = inProgressRepo;
		this.achievplaceRepo = achievplaceRepo;
		this.achievmentRepo = achievmentRepo;
		this.usersAchievementsRepo = usersAchievementsRepo;
	}

	//######################################################################################

	public List<Achievement> placeAchievementProcessing(List<Long> idachievement, String idplace, Long iduser) {
		List<Achievement> achievement_list = new ArrayList<>();
		if(idachievement.isEmpty())
			return achievement_list;

		saveToInProgress(idachievement, idplace, iduser);
		List<Long> achievement_completed = areAchievementsCompleate(idachievement, iduser);
		
		if(!achievement_completed.isEmpty()) {
			achievement_list = this.achievmentRepo.findAllById(achievement_completed);
		}
		
		return achievement_list;
	}
	
	//######################################################################################
	
	private List<Long> areAchievementsCompleate(List<Long> idachievement,Long iduser){
		List<Long> achievement_completed = new ArrayList<>();

		for(Long aid : idachievement) {
			int allAchievementPlacesSize = achievplaceRepo.findByIdachievement(aid).size();
			int currentAchievemenInProgresSize = inProgressRepo.findByIduserAndIdachievement(iduser, aid).size();

			if(allAchievementPlacesSize == currentAchievemenInProgresSize) {
				achievement_completed.add(aid);
				this.usersAchievementsRepo.save(new UsersAchievements(iduser, aid, Date.valueOf(LocalDate.now())));
			}
		}
		return achievement_completed;
	}
	
	//######################################################################################
	
	private void saveToInProgress(List<Long> idachievement, String idplace,  Long iduser) {
		for(Long l : idachievement) {
			inProgressRepo.save(new UsersAchievInProgress(iduser, l, idplace, Date.valueOf(LocalDate.now())));
		}
	}
}
