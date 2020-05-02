package com.chex.consumers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.chex.db.user.UserAuthRepo;
import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;
import com.chex.user.post.display.PostListWraper;
import com.chex.user.post.display.PostPlaceView;

@Controller
public class PostViewController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserAuthRepo userAuthRepo;
	
	@GetMapping("/post/{id}")
	public String post(@PathVariable("id") Long id, Model model, Principal principal) {
		String path = "http://localhost:8080/api/post/" +id;

		UserAuth me = userAuthRepo.findByUsername(principal.getName());
		restTemplate.getInterceptors().clear();
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(principal.getName(), me.getPassword()));
        ResponseEntity<PostPlaceView> response = restTemplate.getForEntity(path, PostPlaceView.class);
        
		if(response.getStatusCode() == HttpStatus.OK) {
			PostPlaceView ppv = response.getBody();
			model.addAttribute("ppv", ppv);
		}
		
		return "user/post";
	}
	
}
