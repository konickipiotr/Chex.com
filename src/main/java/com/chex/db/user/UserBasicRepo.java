package com.chex.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.user.UserBasic;

public interface UserBasicRepo extends JpaRepository<UserBasic, Long> {

}
