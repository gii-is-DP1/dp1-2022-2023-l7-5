package org.springframework.samples.petclinic.achievement;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/achievements")
public class AchievementController {
	private final String ACHIEVEMENTS_LISTING_VIEW= "achievements/AchievementsListing";
	private final String ACHIEVEMENTS_FORM= "achievements/createOrUpdateAchievementsForm";
	private static final String WELCOME= "welcome";
	
	private AchievementService service;
	
	@Autowired
	public AchievementController(AchievementService service) {
		
		this.service= service;
	}
	
	@GetMapping(value= "/AchievementsListing")
	public ModelAndView showAchievements() {
		ModelAndView mav= new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
		List<Achievement> a = service.getAchievements();
		mav.addObject("achievements", a);
		return mav;
	}
	
	@GetMapping(value= "/new")
	public ModelAndView createAchievement(ModelMap mp) {
		mp.addAttribute("achievement", new Achievement());
		return null;
		
	}
	
	@PostMapping(path= "/new")
	public String createAchievement(@Valid Achievement achievement, BindingResult br, ModelMap mp) {
		
		if(br.hasErrors()) {
			service.save(achievement);
			mp.addAttribute("message", "Achievement saved succesfully");
			return WELCOME;
		}else {
			mp.addAttribute("achievement", achievement);
		}
		return ACHIEVEMENTS_FORM;
	}
}
