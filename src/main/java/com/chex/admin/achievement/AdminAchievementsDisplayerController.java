package com.chex.admin.achievement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.AchievementPlaces;
import com.chex.model.place.Place;
import com.chex.user.achievement.AchievementView;
import com.chex.user.achievement.PlaceAchievement;

@Controller
public class AdminAchievementsDisplayerController {

	@Autowired
	private AchievementRepo achievementRepo;
	@Autowired
	private AchievementPlacesRepo achievementPlacesRepo;
	@Autowired
	private PlaceRepo placeRepo;
	
	@GetMapping("/admin/achievement")
	public String displayAchievent(Model model) {
		
		List<Achievement> allAchievement = achievementRepo.findAll();
		List<AchievementView> achievementView_list = new ArrayList<>();
		
		for(Achievement a : allAchievement) {
			AchievementView av = new AchievementView(a);
			List<AchievementPlaces> ap_list = achievementPlacesRepo.findByIdachievement(a.getIdachievement());
			
			av.setPlaces(getPlaceAchievement(ap_list));
			av.setMax(ap_list.size());
			achievementView_list.add(av);
		}
		
		model.addAttribute("achievementView_list", achievementView_list);
		return "admin/allachievements";
	}
	
	private List<PlaceAchievement> getPlaceAchievement(List<AchievementPlaces> ap_list){
		List<PlaceAchievement> places = new ArrayList<>();
		for(AchievementPlaces ap : ap_list) {
			PlaceAchievement placeA = new PlaceAchievement();
			Place place = placeRepo.findByIdplace(ap.getIdplace());
			placeA.setIdplace(place.getIdplace());
			placeA.setName(place.getName());
			places.add(placeA);
		}
		return places;
	}
	
}
