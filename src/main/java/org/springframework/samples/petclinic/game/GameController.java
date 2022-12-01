package org.springframework.samples.petclinic.game;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
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
    private final String  JOIN_LISTING_VIEW = "games/joinGamesListing";
    private final String PLAY_GAME = "play/play";


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
    	game.setNumberCurrentPlayers(1);
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
        service.initPlayerToGame(principal.getName(), game);
    	service.initGame(game.getId());
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
    	List<ScoreBoard> sbs = scoreboardService.getScoreboardsByGameId(id);
    	ModelAndView result = new ModelAndView(GAME_DETAIL);
    	result.addObject("game", game);
    	result.addObject("scoreboards", sbs);
		return result;
    }
    
    @Transactional
    @GetMapping("/join/{id}/{username}")
    public ModelAndView joinPlayerToGame(@PathVariable int id, @PathVariable String username) {
    	Game game = service.getGameById(id);
    	try {
			service.joinPlayerToGame(username, game);
		} catch (TooManyPlayers e) {
			e.printStackTrace();
		} catch (NotThisTypeOfGame e) {
			e.printStackTrace();
		}
        return new ModelAndView("redirect:/games/"+game.getId()+"/view");
    }
    
    @GetMapping("/join")
    public ModelAndView joinGameListing(Principal principal) {
    	ModelAndView mav = new ModelAndView(JOIN_LISTING_VIEW);
    	List<Game> games = service.getGames().stream().filter(g -> g.getMode().charAt(0) == 'C' && !g.getFinished()).collect(Collectors.toList());
    	mav.addObject("games", games);
    	mav.addObject("username", principal.getName());
    	return mav;
    }
    
    @GetMapping("/{id}/play")
    public ModelAndView playGame(@PathVariable int id) {
    	Game game = service.getGameById(id);
    	List<ScoreBoard> sbs = scoreboardService.getScoreboardsByGameId(id);
    	ModelAndView mav = new ModelAndView(PLAY_GAME);
    	System.out.println(sbs);
    	mav.addObject("scoreboards", sbs);
    	return mav;
    }
}