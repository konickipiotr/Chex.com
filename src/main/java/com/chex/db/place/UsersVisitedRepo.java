package com.chex.db.place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.place.UsersVisited;

public interface UsersVisitedRepo extends JpaRepository<UsersVisited, Long> {

	Boolean existsByIdplace(String idplace);
	List<UsersVisited> findByIduser(Long iduser);

}
