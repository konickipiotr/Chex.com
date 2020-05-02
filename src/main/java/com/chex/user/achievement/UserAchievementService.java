package com.chex.user.achievement;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.db.achievement.UsersAchievementsRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.AchievementPlaces;
import com.chex.model.achievement.UsersAchievInProgress;
import com.chex.model.achievement.UsersAchievements;
import com.chex.model.place.Place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAchievementService {

    private AchievementRepo achievementRepo;
    private UsersAchievInProgressRepo userAchInProgerssRepo;
    private UsersAchievementsRepo usersAchRepo;
    private AchievementPlacesRepo achPlacesRepo;
    private PlaceRepo placeRepo;
   

    public AchievemenListWraper getUserAchievement(Long iduser){
        List<UsersAchievements> ua = usersAchRepo.findByIduser(iduser);
        List<Achievement> ach_List = new ArrayList<>();
        for(UsersAchievements u : ua) {
        	Achievement ach = achievementRepo.findById(u.getIdachievement()).get();
        	ach.setDate(u.getDate());
        	ach_List.add(ach);
        }
        return new AchievemenListWraper(ach_List);
    }
    
    @Autowired
    public UserAchievementService(AchievementRepo achRepo, UsersAchievInProgressRepo userAchInProgerssRepo,
			UsersAchievementsRepo usersAchRepo, AchievementPlacesRepo achPlacesRepo, PlaceRepo placeRepo) {
		this.achievementRepo = achRepo;
		this.userAchInProgerssRepo = userAchInProgerssRepo;
		this.usersAchRepo = usersAchRepo;
		this.achPlacesRepo = achPlacesRepo;
		this.placeRepo = placeRepo;
	}

	public AInProgressListWraper getAchievementsInProgress(Long iduser){
        List<UsersAchievInProgress> allInProgres = userAchInProgerssRepo.findByIduser(iduser);
        List<Long> ids = allInProgres.stream().map(i -> i.getIdachievement()).collect(Collectors.toList());
        Set<Long> uq_ids = new HashSet<>(ids);

        List<AchievementView> ip_list = new ArrayList<>();
        for(Long l : uq_ids){
            Achievement a = achievementRepo.findById(l).get();
            AchievementView ip = new AchievementView(a);
            List<UsersAchievInProgress> uaIn = userAchInProgerssRepo.findByIduserAndIdachievement(l, iduser);
            List<AchievementPlaces> allplaces = achPlacesRepo.findByIdachievement(l);
            ip.setPlaces(getPlacesList(allplaces, uaIn));
            ip.setCurrent(uaIn.size());
            ip.setMax(allplaces.size());
            ip_list.add(ip);
        }
        return new AInProgressListWraper(ip_list);
    }
    
    private  List<PlaceAchievement> getPlacesList( List<AchievementPlaces> allplaces, List<UsersAchievInProgress> uaIn){
    	List<PlaceAchievement>  pla_list = new ArrayList<PlaceAchievement>();
        for(AchievementPlaces ap : allplaces) {
        	
        	PlaceAchievement p = new PlaceAchievement();
        	Place place = placeRepo.findByIdplace(ap.getIdplace());
        	p.setIdplace(place.getIdplace());
        	p.setName(place.getName());
        	p.setAchieved(false);
        	
        	for(UsersAchievInProgress inp : uaIn) {
        		if(ap.getIdachievement().equals(inp.getIdachievement())) {
        			p.setAchieved(true);
        		}
        	}
        	pla_list.add(p);
        }
        return pla_list;
    }
}
