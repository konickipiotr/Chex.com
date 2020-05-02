package com.chex.Test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.place.Coords;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;
import com.chex.model.user.UserBasic;
import com.chex.user.place.AddPlaceService;
import com.chex.user.place.ReturnPlace;

@Controller
@RequestMapping("/user/addplace")
public class TestAddPlaceController {

	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private UsersVisitedRepo usersVisitedRepo;
	@Autowired
	private UserBasicRepo userBasicRepo;
	@Autowired
	private AddPlaceService addPlaceService;
	
	@GetMapping
	public String toaddPlace(Model model) {
		List<UserBasic> ubl = userBasicRepo.findAll();
		UserBasic ub = ubl.get(0);
		
		setModel(model, ub);
		return "test/addplace";
	}
	
	@PostMapping
	public String addplace(@RequestParam("place") String placeid, Model model) {
		List<UserBasic> ubl = userBasicRepo.findAll();
		UserBasic ub = ubl.get(0);
		System.out.println(placeid);
		Place place = placeRepo.findByIdplace(placeid);
		
		Coords coord = new Coords(place.getLatitude(), place.getLongitude());
		ReturnPlace returnPlace = addPlaceService.addPlace(coord, ub.getIduser());
		
		model.addAttribute("os_list", returnPlace.getAchievementList());
		
		setModel(model, ub);
		return "test/addplace";
	}
	
	private void setModel(Model model, UserBasic ub) {
		List<Place> places = placeRepo.returnAllByLastLevelPlace();
		List<UsersVisited> user_visited = usersVisitedRepo.findByIduser(ub.getIduser());
		model.addAttribute("user_visited", user_visited);
		model.addAttribute("places", places);
	}
}
