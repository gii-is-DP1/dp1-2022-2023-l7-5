package org.springframework.samples.petclinic.game;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {
	
	private final String  PLAY_SELECT = "games/play";
	private final String  GAME_JOIN = "games/joinGame";
	private final String  GAMES_LISTING_VIEW = "games/GamesListing";
    private final String  GAMES_FORM = "games/createOrUpdateGameForm";
    private final String  GAME_DETAIL = "games/gameDetails";

    List<String> modes = List.of("COMPETITIVE", "SOLO", "SURVIVAL");
    List<Integer> nPlayers = List.of(1, 2, 3, 4);

    private GameService service;
    private ScoreBoardService scoreboardService;
    private UserService userService;
    
    @Autowired
    public GameController(GameService service, ScoreBoardService scoreBoardService,  UserService userService) {
    	this.service = service;
    	this.scoreboardService = scoreBoardService;
    	this.userService = userService;
    }
    
    @GetMapping("")
    public ModelAndView showPlay() {
    	ModelAndView mav = new ModelAndView(PLAY_SELECT);
    	return mav;
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/GamesListing")
    public ModelAndView showGames() {
    	ModelAndView mav = new ModelAndView(GAMES_LISTING_VIEW);
    	mav.addObject("games", service.getGames());
    	return mav;
    }
    
    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deleteGame(@PathVariable int id){
        service.deleteGameById(id);        
        return showGames();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/{id}/edit")
    public ModelAndView editGame(@PathVariable int id){
    	Game game = service.getGameById(id);        
        ModelAndView result = new ModelAndView(GAMES_FORM);
        result.addObject("games", game);
        return result;
    }
    
    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveGame(@PathVariable int id, @Valid Game game, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(GAMES_FORM, br.getModel());
    	}
    	Game gameToBeUpdated = service.getGameById(id);
        BeanUtils.copyProperties(game, gameToBeUpdated, "id");
        service.save(gameToBeUpdated);
        return showGames();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/new")
    public String createGame(ModelMap model) {
    	Game game = new Game();
    	game.setFinished(false);
    	game.setDateOfCreation(LocalDate.now());
    	model.addAttribute(game);
    	model.addAttribute("nplayers", nPlayers);
    	model.addAttribute("modes", modes);
    	return GAMES_FORM;
    }
    
    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewGame(ModelMap model, @Valid Game game, BindingResult br, Principal principal, HttpServletResponse response) {
    	if (br.hasErrors()) {
    		ModelAndView mav = new ModelAndView(GAMES_FORM, br.getModel());
    		mav.addObject("nplayers", nPlayers);
    		mav.addObject("modes", modes);
        	return mav;
    	}
    	if (game.getMode().charAt(0)=='S') {
    		if(game.getNumberOfPlayers() != 1) {
    			ModelAndView mav = new ModelAndView(GAMES_FORM, br.getModel());
    			mav.addObject("nplayers", nPlayers);
        		mav.addObject("modes", modes);
        		mav.addObject("message", "The SURVIVAL/SOLO gamemode has to have only 1 player.");
            	return mav;
    		}
    	} else {
    		if (game.getNumberOfPlayers() == 1) {
    			ModelAndView mav = new ModelAndView(GAMES_FORM, br.getModel());
    			mav.addObject("nplayers", nPlayers);
        		mav.addObject("modes", modes);
        		mav.addObject("message", "The COMPETITIVE gamemode has to have more than 1 player.");
            	return mav;
    		}
    	}
        service.save(game);
        initPlayerToGame(principal.getName(), game);
        ModelAndView result = showGameDetails(model, game.getId(), response);
        result.addObject("message", "The game was created successfully.");
        return new ModelAndView("redirect:/games/"+game.getId()+"/view");
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/{id}/view")
    public ModelAndView showGameDetails(ModelMap model, @PathVariable int id, HttpServletResponse response) {
    	Game game = service.getGameById(id);
    	if (game.getMode().charAt(0) == 'C') {
    		response.addHeader("Refresh", "5");
    	}
    	List<ScoreBoard> sbs = scoreboardService.getScoreboardsById(id);
    	ModelAndView result = new ModelAndView(GAME_DETAIL);
    	result.addObject("game", game);
    	result.addObject("scoreboards", sbs);
		return result;
    }
    
    @Transactional
    public void initPlayerToGame(String username, Game game) {
    	ScoreBoard sb = new ScoreBoard();
    	User user = userService.findUser(username).get();
    	sb.setOrden(1);
    	sb.setScore(0);
    	sb.setUser(user);
    	sb.setGame(game);
    	scoreboardService.save(sb);
    }
    
}