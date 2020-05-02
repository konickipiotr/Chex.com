package com.chex.user.achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/achievement")
public class UserAchievementController {

    @Autowired
    private UserAchievementService userAchievementService;

    @GetMapping("/{userid}")
    public ResponseEntity<AchievemenListWraper> getUserAchievement(@PathVariable("userid") Long userid){
    	AchievemenListWraper wraper = userAchievementService.getUserAchievement(userid);
        if(!wraper.isEmpty())
        	return new ResponseEntity<AchievemenListWraper>(wraper, HttpStatus.OK);
        return new ResponseEntity<AchievemenListWraper>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ip/{userid}")
    public  ResponseEntity<AInProgressListWraper> getAchievementInProgerss(@PathVariable("userid") Long userid){
    	AInProgressListWraper list = userAchievementService.getAchievementsInProgress(userid);
        if(!list.isEmpty())
        	return new ResponseEntity<AInProgressListWraper>(list, HttpStatus.OK);
        return new ResponseEntity<AInProgressListWraper>(HttpStatus.ACCEPTED);
    }
}
