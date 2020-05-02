package com.chex.admin.addplace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chex.db.CategoryRepo;
import com.chex.db.place.PlaceDetailRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.model.Category;
import com.chex.model.place.Place;
import com.chex.model.place.PlaceDetails;

@Controller
@RequestMapping("/admin/addplace")
public class AdminAddPlaceController {

	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private PlaceDetailRepo placeDetailRepo;
	
	@GetMapping
	public String toSelectContinent(Model model) {
		List<Place> continets = placeRepo.getContinents();
		model.addAttribute("continents", continets);
		System.out.println("ut");
		System.err.println(continets);
		List<Place> cc = placeRepo.findAll();
		System.err.println(cc);
		return "admin/addplace/continents";
	}
	
	@GetMapping("/continent/{continentid}")
	public String toSelectCountry(@PathVariable("continentid") String continentid, 
									Model model, 
									ParentPlaceModel parentPlaceModel,
									String message) {
		continentid = continentid.substring(0, 2);
		List<Place> countries = placeRepo.getCountries(continentid);
		
		if(parentPlaceModel.getId() == null)
			parentPlaceModel = new ParentPlaceModel();
		
		model.addAttribute("countries", countries);
		model.addAttribute("continentid", continentid);
		model.addAttribute("parentPlaceModel", parentPlaceModel);
		model.addAttribute("message", message);
		return "admin/addplace/countries";
	}
	
	@GetMapping("/country/{countryid}")
	public String toSelectRegions(@PathVariable("countryid") String countryid, 
								Model model, 
								ParentPlaceModel parentPlaceModel,
								String message) {
		
		countryid = countryid.substring(0, 5);
		List<Place> regions = placeRepo.getRegions(countryid);
		
		if(parentPlaceModel.getId() == null)
			parentPlaceModel = new ParentPlaceModel();
		
		model.addAttribute("regions", regions);
		model.addAttribute("continentid", countryid);
		model.addAttribute("parentPlaceModel", parentPlaceModel);
		model.addAttribute("message", message);
		return "admin/addplace/regions";
	}
	
	@GetMapping("/region/{regionid}")
	public String toSelectRegion(@PathVariable("regionid") String regionid, 
									Model model, 
									ParentPlaceModel parentPlaceModel,
									String message) {
		regionid = regionid.substring(0, 8);
		List<Place> region = placeRepo.getRegion(regionid);
		
		if(parentPlaceModel.getId() == null)
			parentPlaceModel = new ParentPlaceModel();
		
		model.addAttribute("region", region);
		model.addAttribute("continentid", regionid);
		model.addAttribute("parentPlaceModel", parentPlaceModel);
		model.addAttribute("message", message);
		return "admin/addplace/region";
	}
	
	@GetMapping("/newplaceform/{id}")
	public String toNewPlaceFrom(@PathVariable("id") String id, 
			Model model, 
			NewPlaceForm newPlaceForm,
			String message) {
		Place parentPlace = placeRepo.findByIdplace(id);
		List<Category> categories = categoryRepo.findAll();
		
		if(newPlaceForm.getNewid() == null)
			newPlaceForm = new NewPlaceForm();

		model.addAttribute("parentPlace", parentPlace);
		model.addAttribute("categories", categories);
		model.addAttribute("newPlaceForm", newPlaceForm);
		return "admin/addplace/addnewplace";
	}
	
	@PostMapping("/newplace")
	public String addNewPlace(NewPlaceForm npf, RedirectAttributes ra) {
	
		String parantid = npf.getParentid().substring(0,11);
		
		String newid = parantid + npf.getNewid();
				
		if(placeRepo.existsByIdplace(newid)) {
			ra.addFlashAttribute(npf);
			ra.addAttribute("message", "Miejsce o taki id istnieje");
			return "redirect:/admin/addplace/newplace/" + npf.getParentid();
		}
		
		this.placeRepo.save(new Place(newid, npf.getName(), npf.getLatitude(), npf.getLongitude(), npf.getRadius(), npf.getCategory(), null, npf.getPoints(), npf.getDifficultylevel()));
		this.placeDetailRepo.save(new PlaceDetails(newid, npf.getDescription(), npf.getImgurl()));
		
		ra.addAttribute("message", "Pomyślnie dodano " + npf.getName());
		return "redirect:/admin/addplace/region/" + npf.getParentid();
	}
	
}
