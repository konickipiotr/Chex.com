package com.chex.user.place;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.pattern.PathPattern.PathRemainingMatchInfo;

import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;
import com.chex.model.user.achievement.AchievementService;
import com.chex.utils.IdUtils;

@Service
public class AddPlaceService {
	
	@Autowired
	private CheckPlaceService checkPlaceService;
	@Autowired
	private UsersVisitedRepo usersVisitedRepo;
	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private AddPlaceUserService addPlaceUserService;

	public ReturnPlace addPlace(Coords currentCoords, Long iduser) {
		ReturnPlace rp = new ReturnPlace();
		rp.setPlace(checkPlaceService.checkPlace(currentCoords));
		
		if(rp.getPlace() == null) {
			rp.setPlaceStatus(AddPlaceStatus.NOTEXIST);
			return rp;
		}
		
		if(usersVisitedRepo.existsByIdplace(rp.getPlace().getIdplace())) {
			rp.setPlaceStatus(AddPlaceStatus.EXIST);
			return rp;
		}
		
		rp.setPlaceStatus(AddPlaceStatus.SUCCESS);
		rp.setParentPlaceList(getParentList(rp.getPlace().getIdplace()));
		
		savePlacesToDB(rp, iduser);
		rp.setAchievementList(getAchievement(rp, iduser));
		
		addPlaceUserService.addExp(rp, iduser);		
		return rp;
	}
	
	private void savePlacesToDB(ReturnPlace rp, Long iduser) {
		usersVisitedRepo.save(new UsersVisited(iduser, rp.getPlace().getIdplace(), Date.valueOf(LocalDate.now())));
		
		List<Place> parentL = rp.getParentPlaceList();
		if(parentL != null) {
			for(Place p : parentL) {
				usersVisitedRepo.save(new UsersVisited(iduser, p.getIdplace(), Date.valueOf(LocalDate.now())));
			}
		}
	}
	
	public List<Achievement> getAchievement(ReturnPlace rp,  Long iduser){
		List<Achievement> achievements = new ArrayList<>();
		
		List<Long> mainPlaceId = IdUtils.splitToLongList(rp.getPlace().getAchievements());
		if(mainPlaceId != null) {
			achievements.addAll(achievementService.placeAchievementProcessing(mainPlaceId, rp.getPlace().getIdplace(), iduser));
		}
		
		if(rp.getParentPlaceList() != null) {
			for(Place p : rp.getParentPlaceList()) {
				List<Long> list = IdUtils.splitToLongList(p.getAchievements());
				if(list != null)
					achievements.addAll(achievementService.placeAchievementProcessing(list, rp.getPlace().getIdplace(), iduser));
			}
		}
		return achievements;
	}
	
	public List<Place> getParentList(String idplace){
		List<Place> parentPlace = new ArrayList<>();
		
		Place continent = placeRepo.findByIdplace(IdUtils.getContinentId(idplace));
		if(!usersVisitedRepo.existsByIdplace(continent.getIdplace())) 
			parentPlace.add(continent);

		Place country = placeRepo.findByIdplace(IdUtils.getCountryId(idplace));
		if(!usersVisitedRepo.existsByIdplace(country.getIdplace())) 
			parentPlace.add(country);

		Place region = placeRepo.findByIdplace(IdUtils.getRegionId(idplace));
		if(!usersVisitedRepo.existsByIdplace(region.getIdplace()))
			parentPlace.add(region);
		
		Place subregion = placeRepo.findByIdplace(IdUtils.getSubregionId(idplace));
		if(!usersVisitedRepo.existsByIdplace(subregion.getIdplace()))
			parentPlace.add(subregion);

		return parentPlace;
	}

}
