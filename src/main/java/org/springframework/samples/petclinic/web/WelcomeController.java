package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Map;	

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public ModelAndView welcome(Map<String, Object> model, Principal principal) {
		  ModelAndView mav = new ModelAndView("welcome");
		  if (principal != null) {
			  mav.addObject("username", principal.getName());
		  }
		  return mav;
	  }
}