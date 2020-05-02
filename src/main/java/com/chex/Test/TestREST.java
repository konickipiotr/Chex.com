package com.chex.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chex.db.user.UserAuthRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.user.UserAuth;
import com.chex.user.place.AddPlaceService;
import com.chex.user.place.ReturnPlace;

@RestController
public class TestREST {

	@Autowired
	private AddPlaceService addplaceService;
	@Autowired
	private UserAuthRepo userAuthRepo;
	
	@GetMapping("/addplacetest")
	public ReturnPlace restTest() {
		UserAuth ua = userAuthRepo.findByUsername("Piotr");
		Coords coords = new Coords(50.736198, 15.739875);
		return addplaceService.addPlace(coords, ua.getIduser());
	}
}
