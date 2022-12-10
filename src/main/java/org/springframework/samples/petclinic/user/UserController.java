package org.springframework.samples.petclinic.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private final String VIEWS_PLAYER_CREATE_FORM = "users/createPlayerForm";
	private final String PLAYERS_LISTING_VIEW = "/players/PlayersListing";
	private final String PLAYERS_DETAIL = "players/PlayerDetails";
	private final String PLAYER_ACHIEVEMENTS = "players/PlayerAchievements";
	
	private final UserService userService;
	private final AuthoritiesService authService;
	private final AchievementService achService;

	@Autowired
	public UserController(UserService userService, AuthoritiesService authService, AchievementService achService) {
		this.userService = userService;
		this.authService = authService;
		this.achService = achService;
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		Authorities auth = new Authorities();
		user.setEnabled(false);
		auth.setAuthority("player");
		model.put("user", user);
		model.put("auth", auth);
		return VIEWS_PLAYER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public ModelAndView processCreationForm(@Valid User user, Authorities auth, BindingResult br) {
		if (br.hasErrors()) {
			ModelAndView result = new ModelAndView(VIEWS_PLAYER_CREATE_FORM, br.getModel());
			result.addObject(user);
			return result;
		}
		else {
			user.setEnabled(true);
			auth.setUser(user);
			auth.setAuthority("player");
			this.userService.saveUser(user);
			this.authService.saveAuthorities(auth);
			return new ModelAndView("redirect:/");
		}
	}
	
	@Transactional(readOnly = true)
    @GetMapping("/users")
    public ModelAndView showPlayers() {
    	ModelAndView mav = new ModelAndView(PLAYERS_LISTING_VIEW);
    	List<User> users = userService.findAllUsers();
    	mav.addObject("players", users);
    	return mav;
    }
	
	@Transactional()
    @GetMapping("users/{username}/delete")
    public ModelAndView deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);        
        return new ModelAndView("redirect:/users");
    }
	
	@GetMapping(value = "/player/{username}/edit")
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
		User user = this.userService.findUser(username).get();
		model.addAttribute(user);
		return VIEWS_PLAYER_CREATE_FORM;
	}
	
	@PostMapping(value = "/player/{username}/edit")
	public String processUpdateUserForm(@Valid User user, BindingResult result,
			@PathVariable("username") String username, Model model) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_FORM;
		}
		else {
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/player/{username}")
	public String viewUser(@PathVariable("username") String username, Model model) {
		User user = this.userService.findUser(username).get();
		model.addAttribute(user);
		return PLAYERS_DETAIL;
	}
	
	@GetMapping(value = "/player/{username}/achievements")
	public ModelAndView viewUserAchievements(@PathVariable("username") String username, Model model) {
		ModelAndView mav = new ModelAndView(PLAYER_ACHIEVEMENTS);
		User user = this.userService.findUser(username).get();
		List<Achievement> achievements = achService.getAchievements();
		mav.addObject("user",user);
		mav.addObject("achievements",achievements);
		return mav;
	}

}
