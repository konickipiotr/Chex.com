package com.chex.consumers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.chex.GlobalSettings;
import com.chex.db.user.UserAuthRepo;
import com.chex.model.user.UserAuth;
import com.chex.user.achievement.AInProgressListWraper;
import com.chex.user.achievement.AchievemenListWraper;

@Controller
@RequestMapping("/achievement")
public class UserAchController {
	@Autowired
	private UserAuthRepo userAuthRepo;
	private final String BASICPATH = "/api/achievement";

    @GetMapping
    public String getUserAchievements(Principal principal, Model model){
    
    	UserAuth me = userAuthRepo.findByUsername(principal.getName());
    	
    	RestTemplate rest = new RestTemplate();
		rest.getInterceptors().add(new BasicAuthenticationInterceptor(me.getUsername(), me.getPassword()));
		
		String path = GlobalSettings.SERVERADDRESS+BASICPATH + "/" + me.getIduser();
		ResponseEntity<AchievemenListWraper> response;
		try {
			response = rest.getForEntity(path, AchievemenListWraper.class);
			 if(response.getStatusCode() == HttpStatus.OK) 
		        	model.addAttribute("achievementList", response.getBody().getList());
		}catch(HttpClientErrorException e) {
		}
      
        
        path = GlobalSettings.SERVERADDRESS+BASICPATH + "/ip/" + me.getIduser();
        ResponseEntity<AInProgressListWraper> response2;
        try {
        	response2 = rest.getForEntity(path, AInProgressListWraper.class);
        	if(response2.getStatusCode() == HttpStatus.OK)
            	model.addAttribute("aInPorgressList", response2.getBody().getList());
        }catch(ResponseStatusException e) {
        }
        
    	return "user/achievement/myachievements.html";

    }
}
