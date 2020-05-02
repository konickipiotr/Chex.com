package com.chex.admin.addplace;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chex.db.place.PlaceDetailRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.model.place.Place;
import com.chex.model.place.PlaceDetails;

@Controller
@RequestMapping("/admin/addplace")
@Transactional
public class AdminAddParentPlaceController {

	private PlaceRepo placeRepo;
	private PlaceDetailRepo placeDetailRepo;
	
	@Autowired
	public AdminAddParentPlaceController(PlaceRepo placeRepo, PlaceDetailRepo placeDetailRepo) {
		this.placeRepo = placeRepo;
		this.placeDetailRepo = placeDetailRepo;
	}
	

	@PostMapping("/parentplace")
	public String addParentPlace(ParentPlaceModel ppm, RedirectAttributes ra) {
		Place newPlace = new Place();
	
		String newid = ppm.getParentid() + ppm.getId();
		newid =newid.toUpperCase();
		
		String redirect = "redirect:/admin/addplace";
		String message = "";
		switch (ppm.getCategory()) {
		case "country":{
			newid+="0000000000";
			redirect+="/continent/" + ppm.getParentid() + "0000000000000";
			newPlace.setPoints(1000);
			newPlace.setDifficultylevel(0);
			message = "Kraj ";
		}break;
		case "regions":{
			newid+="0000000";
			redirect+="/country/" + ppm.getParentid() + "0000000000";
			newPlace.setPoints(500);
			newPlace.setDifficultylevel(0);
			message = "Region ";
		}break;
		case "region":{
			newid+="0000";
			redirect+="/region/" + ppm.getParentid() + "0000000";
			newPlace.setPoints(500);
			newPlace.setDifficultylevel(0);
			message = "Miasto ";
		}break;
		default:
			break;
		}

		if(placeRepo.existsByIdplace(newid)) {
			ra.addFlashAttribute(ppm);
			ra.addAttribute("message", message + "o takim id istnieje");
			return redirect;
		}
		
		newPlace.setIdplace(newid);
		newPlace.setName(ppm.getName());
		
		placeRepo.save(newPlace);
		placeDetailRepo.save(new PlaceDetails(newid, ppm.getDescription(), ""));
		
		ra.addFlashAttribute(new ParentPlaceModel());
		ra.addAttribute("message", "Dodano: " + ppm.getName());
		return redirect;
	}
}
