package com.chex.consumers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.chex.db.user.PostRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;
import com.chex.user.post.Post;
import com.chex.user.post.display.PostListWraper;
import com.chex.user.post.display.PostView;

@Controller
public class UserHomeController {

	@Autowired
	private UserBasicRepo userBasicRepo;
	@Autowired
	private UserAuthRepo userAuthRepo;
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/init")
	public String userHome(Principal principal, HttpSession session) {
		UserAuth userAuth = userAuthRepo.findByUsername(principal.getName());
		UserBasic user = userBasicRepo.findById(userAuth.getIduser()).get();
		
		session.setAttribute("me", user);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String home(@RequestHeader HttpHeaders header,Model model,Principal principal) {
		List<UserBasic> ubl = userBasicRepo.findAll();
		UserAuth user = userAuthRepo.findByUsername(principal.getName());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().clear();
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user.getUsername(), user.getPassword()));
        ResponseEntity<PostListWraper> response = restTemplate.getForEntity("http://localhost:8080/api", PostListWraper.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			PostListWraper plwraper = response.getBody();
			List<PostView> posts = plwraper.getPosts();
			model.addAttribute("posts", posts);
		}
		
		model.addAttribute("ub", user);
		return "user/home";
	}
}
