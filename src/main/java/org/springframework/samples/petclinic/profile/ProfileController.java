package org.springframework.samples.petclinic.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
	
	private final String PROFILE_LISTING_VIEW= "profiles/ProfilesListing";
	
	protected ProfileService service;
	
	@Autowired
	private ProfileController(ProfileService service) {
		
		this.service= service;
	}
	
	@GetMapping(value= "/profiles/ProfilesListing")
	public ModelAndView showProfiles() {
		ModelAndView mav= new ModelAndView(PROFILE_LISTING_VIEW);
		mav.addObject("profiles", service.getProfiles());
		return mav;
	}
}
