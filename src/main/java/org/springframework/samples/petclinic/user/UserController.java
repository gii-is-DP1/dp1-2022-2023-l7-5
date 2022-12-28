package org.springframework.samples.petclinic.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
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
	private final String GLOBAL = "global";
	
	private final UserService userService;
	private final AuthoritiesService authService;
	private final AchievementService achService;
	private final ProfileService pService;

	@Autowired
	public UserController(UserService userService, AuthoritiesService authService, AchievementService achService, ProfileService pService) {
		this.userService = userService;
		this.authService = authService;
		this.achService = achService;
		this.pService = pService;
	}

	@GetMapping(value = "/users/new")
	public ModelAndView initCreationForm() {
		ModelAndView result = new ModelAndView(VIEWS_PLAYER_CREATE_FORM);
		User user = new User();
		Authorities auth = new Authorities();
		result.addObject("user", user);
		result.addObject("auth", auth);
		return result;
	}

	@PostMapping(value = "/users/new")
	public ModelAndView processCreationForm(@Valid User user, @Valid Authorities auth, BindingResult br) {
		if (br.hasErrors()) {
			ModelAndView result = new ModelAndView(VIEWS_PLAYER_CREATE_FORM, br.getModel());
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
	
	@GetMapping(value = "/player/honey")
	public ModelAndView viewGlobal(Model model) {
		
		ModelAndView mav = new ModelAndView(GLOBAL);
		User user = this.userService.findUser("honey").get();
		user.setProfile(pService.getProfileById(1));
		Profile pWinner = this.pService.getProfiles().stream().filter(p -> pService.isMaxWinner(p)).collect(Collectors.toList()).get(0);
		User u1 = pWinner.getUser();
		Profile pSteals = this.pService.getProfiles().stream().filter(p -> pService.isMaxThief(p)).collect(Collectors.toList()).get(0);
		User u2 = pSteals.getUser();
		Profile pMatches = this.pService.getProfiles().stream().filter(p -> pService.isMaxMatcher(p)).collect(Collectors.toList()).get(0);
		User u3 = pMatches.getUser();
		Profile pPlayedGames = this.pService.getProfiles().stream().filter(p -> pService.isMaxOlder(p)).collect(Collectors.toList()).get(0);
		User u4 = pPlayedGames.getUser();
		mav.addObject("user",user);
		mav.addObject("user1",u1);
		mav.addObject("user2",u2);
		mav.addObject("user3",u3);
		mav.addObject("user4",u4);

		return mav;
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
