package com.chex.admin.achievement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.AchievementPlaces;
import com.chex.model.place.Place;

@Controller
@RequestMapping("/admin/achievement/add")
@Transactional
public class AdminAchievementController {
	
	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private AchievementRepo achievementRepo;
	@Autowired
	private AchievementPlacesRepo achPlacesRepo;
	
	
	@PostMapping
	public String addAchievement(Achievement achievement, HttpSession session, RedirectAttributes ra) {
		List<Place> achievement_places = (List<Place>) session.getAttribute("achievement_places");
		if(achievement_places == null || achievement_places.isEmpty()) {
			ra.addFlashAttribute(achievement);
			ra.addAttribute("message", "Osiągnięcie musi mieć przynajmniej 1 miejsce");
			return "redirect:/admin/achievement/add";
		}
		
		if(achievementRepo.existsByName(achievement.getName())) {
			ra.addFlashAttribute(achievement);
			ra.addAttribute("message", "Osiągnięcie o takiej nazwie już istnieje");
			return "redirect:/admin/achievement/add";
		}
		achievementRepo.save(achievement);
		
		for(Place p : achievement_places) {
			achPlacesRepo.save(new AchievementPlaces(achievement.getIdachievement(), p.getIdplace()));
			p.addAchievement(achievement.getIdachievement());
		}
		
		this.placeRepo.saveAll(achievement_places);	
		ra.addAttribute("message", "Pomyślnie dodano osiągnięcie");
		return "redirect:/admin/achievement/clear";
	}

	@GetMapping
	public String newAchievement(Model model, 
							HttpSession session, 
							Achievement achievement,
							String message) {
		
		List<Place> achievement_places = (List<Place>) session.getAttribute("achievement_places");
		if(achievement_places == null) {
			achievement_places = new ArrayList<>();
		}
	
		List<Place> places = placeRepo.findAll();
		List<PlaceListModel> all_places = new ArrayList<>();

		for(Place p : places) {
			boolean exist = false;
			for(Place a : achievement_places) {
				if(a.getIdplace().equals(p.getIdplace())) {
					exist = true;
					break;
				}
			}
			if(exist == false)
				all_places.add(new PlaceListModel(p.getIdplace(), p.getName(), p.getCategory()));
		}
		
		for(Place p : places) {
			all_places.add(new PlaceListModel(p.getIdplace(), p.getName(), p.getCategory()));
		}

		model.addAttribute("message", message);
		model.addAttribute("all_places", all_places);
		model.addAttribute("achievement_places", achievement_places);
		model.addAttribute("achievement", achievement);
		return "admin/newachievement";
	}
	
	@GetMapping("/{idplace}")
	public String addplaceToList(@PathVariable("idplace") String idplace, 
						HttpSession session,
						RedirectAttributes ra) {
		List<Place> achievement_places = (List<Place> )session.getAttribute("achievement_places");
		if(achievement_places == null) {
			achievement_places = new ArrayList<>();
		}
		
		Place place = placeRepo.findByIdplace(idplace);
		
		achievement_places.add(place);
		session.setAttribute("achievement_places", achievement_places);
		return "redirect:/admin/achievement/add";
	}
	
	@GetMapping("/clear")
	public String clearAchievement(HttpSession session, String message, RedirectAttributes ra) {
		session.removeAttribute("achievement_places");
		ra.addFlashAttribute(new Achievement());
		ra.addFlashAttribute("message", message);
		return "redirect:/admin/achievement/add";
	}
	
	@GetMapping("/remove/{idplace}")
	public String removeFromList(@PathVariable("idplace") String idplace, HttpSession session) {
		List<Place> achievement_places = (List<Place> )session.getAttribute("achievement_places");
		Iterator<Place> it = achievement_places.iterator();
		while (it.hasNext()) {
			if(it.next().getIdplace().equals(idplace)) {
				it.remove();
				break;
			}
		}
		
		session.setAttribute("achievement_places", achievement_places);
		return "redirect:/admin/achievement/add";
	}
}
