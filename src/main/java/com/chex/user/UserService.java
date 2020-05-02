package com.chex.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;

@Service
public class UserService {

	private UserAuthRepo userAuthRepo;
	private UserBasicRepo userBasicRepo;
	
	@Autowired
	public UserService(UserAuthRepo userAuthRepo, UserBasicRepo userBasicRepo) {
		this.userAuthRepo = userAuthRepo;
		this.userBasicRepo = userBasicRepo;
	}
	
	public ResponseEntity<UserAuth> getUserAuth(String username) { 
		UserAuth userAuth = userAuthRepo.findByUsername(username);
		if(userAuth == null)
			return new ResponseEntity<UserAuth>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<UserAuth>(userAuth, HttpStatus.OK);
	}
	
	public ResponseEntity<UserBasic> getUserbasicInfo(Long id) {
		Optional<UserBasic> userbasicoptional = userBasicRepo.findById(id);
	
		if(userbasicoptional.isEmpty())
			return new ResponseEntity<UserBasic>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<UserBasic>(userbasicoptional.get(), HttpStatus.OK);
	}
}
