package com.chex.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/userlogin")
	public ResponseEntity<UserAuth> getUserAuth(Principal principal){
		return userService.getUserAuth(principal.getName());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserBasic> getUserBasic(@PathVariable Long id){
		return userService.getUserbasicInfo(id);
	}
}
