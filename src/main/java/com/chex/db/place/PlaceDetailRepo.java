package com.chex.db.place;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chex.model.place.PlaceDetails;

public interface PlaceDetailRepo extends JpaRepository<PlaceDetails, Long> {
	
	PlaceDetails findByIdplace(String idplace);

}
