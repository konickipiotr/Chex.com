package com.chex.consumers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;
import com.chex.model.user.UserAuth;
import com.chex.user.place.AddPlaceService;
import com.chex.user.place.ReturnPlace;
@Controller
@RequestMapping("/places")
public class UserPlacesController {

	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private UserAuthRepo userAuthRepo;
	@Autowired
	private UsersVisitedRepo usersVisitedRepo;
	@Autowired
	private AddPlaceService addPlaceService;
	
	
	
	@GetMapping
	public String toMyPlaces(Principal principal, Model model) {
		UserAuth ua = userAuthRepo.findByUsername(principal.getName());
		List<Place> allplaces = placeRepo.findAll();
		
		List<UsersVisited> userVisited= usersVisitedRepo.findByIduser(ua.getIduser());
		System.out.println("dd "+ userVisited);
		List<Long> ids = userVisited.stream().map(i -> i.getId()).collect(Collectors.toList());
		
		List<Place> myPlaces = new ArrayList<>();
		for(UsersVisited uv : userVisited) {
			myPlaces.add(placeRepo.findByIdplace(uv.getIdplace()));
		}

		
		model.addAttribute("allplaces", allplaces);
		model.addAttribute("myPlaces", myPlaces);
		model.addAttribute("id", ua.getIduser());
		return "user/myplaces/myplaces";
	}
	
	@PostMapping
	public String addNewPlace(@RequestParam("id") Long id, @RequestParam("place") String place) {
		System.out.println(">> " + id);
		System.out.println(">> " +place);
		Place newplace = placeRepo.findByIdplace(place);
		Coords coords = new Coords(newplace.getLatitude(), newplace.getLongitude());
		ReturnPlace rp =  addPlaceService.addPlace(coords, id);
		
		return "redirect:/places";
		
	}
	
}
