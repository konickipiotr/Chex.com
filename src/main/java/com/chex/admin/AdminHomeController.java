package com.chex.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chex.db.place.PlaceRepo;
import com.chex.model.place.Place;

@Controller
public class AdminHomeController {
	
	@Autowired
	private PlaceRepo placeRepo;
	
	@GetMapping("/admin")
	public String adminhome(Model model) {
		List<Place> places = placeRepo.findAll();
		model.addAttribute("places", places);
		return "admin/home";
	}
}
// 51.110200, 17.044407