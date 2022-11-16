package org.springframework.samples.petclinic.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private final UserService userService;
	
	private final AuthoritiesService authService;

	@Autowired
	public UserController(UserService userService, AuthoritiesService authService) {
		this.userService = userService;
		this.authService = authService;
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
	public String processCreationForm(@Valid User user, Authorities auth, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_FORM;
		}
		else {
			user.setEnabled(true);
			auth.setUser(user);
			auth.setAuthority("player");
			this.userService.saveUser(user);
			this.authService.saveAuthorities(auth);
			return "redirect:/";
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
    @GetMapping("users/{id}/delete")
    public ModelAndView deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);        
        return showPlayers();
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

}
