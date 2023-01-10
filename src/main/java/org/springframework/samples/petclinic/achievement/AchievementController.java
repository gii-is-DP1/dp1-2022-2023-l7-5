package org.springframework.samples.petclinic.achievement;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/achievements")
public class AchievementController {
	private final String ACHIEVEMENTS_LISTING_VIEW = "achievements/AchievementsListing";
	private final String ACHIEVEMENTS_FORM = "achievements/createOrUpdateAchievementsForm";

	private AchievementService service;

	@Autowired
	public AchievementController(AchievementService service) {

		this.service = service;
	}

	@GetMapping(value = "/AchievementsListing")
	public ModelAndView showAchievements() {
		ModelAndView mav = new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
		List<Achievement> a = service.getAchievements();
		mav.addObject("achievements", a);
		return mav;
	}

	@GetMapping("/new")
	public String createAchievement(ModelMap mp) {
		mp.put("achievement", new Achievement());
		return ACHIEVEMENTS_FORM;
	}

	@PostMapping("/new")
	public String saveNewAchievement(@Valid Achievement achievement, BindingResult br, ModelMap mp) {
		if (br.hasErrors()) {
			return ACHIEVEMENTS_FORM;
		} else {
			Achievement a= new Achievement();
			BeanUtils.copyProperties(achievement, a, "id");
			service.save(a);
			return "redirect:/achievements/AchievementsListing";
		}
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView updateAchievement(@PathVariable int id) {
		Achievement achievement= service.getAchievementById(id);
		ModelAndView res= new ModelAndView(ACHIEVEMENTS_FORM);
		res.addObject(achievement);
		return res;
	}

	@PostMapping("/{id}/edit")
	public ModelAndView saveUpdatedAchievement(@PathVariable int id, @Valid Achievement achievement, BindingResult br) {
		if (br.hasErrors()) {
			return new ModelAndView();
		} else {
			Achievement a= service.getAchievementById(id);
			BeanUtils.copyProperties(achievement, a, "id");
			service.save(a);
			return showAchievements();
		}
	}
}
