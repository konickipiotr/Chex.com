package com.chex.user.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chex.db.user.UserBasicRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.place.Place;
import com.chex.model.user.UserBasic;

@Service
public class AddPlaceUserService {
	
	@Autowired
	private UserBasicRepo userBasicRepo;

	public void addExp(ReturnPlace rp, Long iduser) {
		
		int points = 0;
		
		points += rp.getPlace().getPoints();
		points += addPointsForParentPlace(rp);
		points += addPointsForAchievements(rp);
		
		UserBasic user = userBasicRepo.findById(iduser).orElse(null);
		if(user != null) {
			user.setExp(user.getExp() + points);
			userBasicRepo.save(user);
		}
		
	}
	
	private int addPointsForAchievements(ReturnPlace rp) {
		int points = 0;
		List<Achievement> achievements = rp.getAchievementList();
		if(achievements != null) {
			for(Achievement achievement : achievements) {
				points += achievement.getPoints();
			}
		}
		return points;
	}
	
	private int addPointsForParentPlace(ReturnPlace rp) {
		int points = 0;
		List<Place> parentPlaces = rp.getParentPlaceList();
		if(parentPlaces != null) {
			for(Place place : parentPlaces) {
				points += place.getPoints();
			}
		}
		return points;
	}
}
