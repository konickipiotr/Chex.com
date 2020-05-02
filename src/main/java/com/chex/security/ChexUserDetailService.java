package com.chex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chex.db.user.UserAuthRepo;
import com.chex.model.user.UserAuth;

@Service
public class ChexUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserAuthRepo userAuthRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAuth user = userAuthRepo.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User: "+ username + "doesn't exist");
		return new ChexUserDetails(user);
	}
}



