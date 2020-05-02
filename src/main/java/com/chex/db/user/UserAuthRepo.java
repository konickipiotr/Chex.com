package com.chex.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.user.UserAuth;

public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
	
	UserAuth findByUsername(String login);
}
