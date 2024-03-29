package org.springframework.samples.petclinic.game;

import java.security.Principal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.cell.CellService;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
import org.springframework.samples.petclinic.profile.Profile;
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
	
	private final String PLAY_SELECT = "games/play";
	private final String GAMES_LISTING_VIEW = "games/GamesListing";
    private final String GAMES_FORM = "games/createOrUpdateGameForm";
    private final String GAME_DETAIL = "games/gameDetails";
    private final String JOIN_LISTING_VIEW = "games/joinGamesListing";
    private final String PLAY_GAME = "play/play";
    private final String PLAY_GAME_COMPETITIVE = "play/playCompetitive";
    private final String RESTART_GAME = "games/restartGame";
    private final String WON_GAME = "games/wonGame";
    private final String FINISH_GAME = "games/finishGame";
    private final String FINISH_GAME_COMPETITIVE = "games/finishGameCompetitive";


    List<String> modes = List.of("COMPETITIVE", "SOLO", "SURVIVAL");
    List<Integer> nPlayers = List.of(1, 2, 3, 4);

    private GameService service;
    private ScoreBoardService scoreboardService;
    private UserService userService;
    private AchievementService achievementService;
    
    @Autowired
    public GameController(GameService service, ScoreBoardService scoreBoardService,  UserService userService, CellService cellService, AchievementService achievementService) {
    	this.service = service;
    	this.scoreboardService = scoreBoardService;
    	this.userService = userService;
    	this.achievementService = achievementService;
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
    	service.initGame(game.getId());
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
    	result.addObject("creator", sbs.get(0).getUser());
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
    public ModelAndView playGame(@PathVariable int id, HttpServletResponse response, Principal principal) {
    	Game game = service.getGameById(id);
    	if (game.getMode().charAt(0) == 'C') {
    		response.addHeader("Refresh", "5");
    	}
    	List<ScoreBoard> sbs = scoreboardService.getScoreboardsByGameId(id);
    	ModelAndView mav = null;
    	String mode = game.getMode();
    	if(mode.charAt(0) == 'C') {
    		mav = new ModelAndView(PLAY_GAME_COMPETITIVE);
    	} else {
    		mav = new ModelAndView(PLAY_GAME);
    	}
    	mav.addObject("scoreboards", sbs);
    	mav.addObject("game", game);
    	mav.addObject("username", principal.getName());
    	mav.addObject("cells", game.getCells());
    	for (Cell cell : game.getCells()) {
    		String cellid = "cell" + String.valueOf(cell.getId());
        	mav.addObject(cellid , cell);
    	}
    	User user = userService.findUser(principal.getName()).get();
    	mav.addObject("user", user);
    	ScoreBoard sb = sbs.stream().filter(i -> i.getUser().getUsername().equals(user.getUsername())).findFirst().get();
    	mav.addObject("handCondition",(sb.getScore()==0 && user.getTiles().size()==0) || (user.getTiles().size() < sb.getScore()));
    	Boolean full = game.getCells().stream().allMatch(c -> c.getTile() != null || c.getIsBlocked());
    	Boolean empty = game.getCells().stream().allMatch(c -> c.getTile() == null);
		Boolean emptyHands = sbs.stream().map(s -> s.getUser()).allMatch(u -> u.getTiles().isEmpty());
    	if (game.getMode().charAt(0) == 'S') {
    		if (empty) {
        		return new ModelAndView("redirect:/games/{id}/play/wonGame");
        	} else if(full || (game.getBag().isEmpty() && emptyHands)) {
        		return new ModelAndView("redirect:/games/{id}/play/finishGame");
        	} 
    	}
    	if (game.getMode().charAt(0) == 'C') {
    		if(full || (game.getBag().isEmpty() && emptyHands)) {
    			ModelAndView finishedCompetitive = new ModelAndView("redirect:/games/{id}/play/finishGame");
    			finishedCompetitive.addObject("game", game);
    			finishedCompetitive.addObject("scoreboards", sbs);
        		return finishedCompetitive;
        	} 
    	}
    
    	return mav;
    }
    
    @GetMapping("/{id}/play/stealToken")
    public ModelAndView stealToken(@PathVariable int id, Principal principal) {
    	Game game = service.getGameById(id);
    	User user = userService.findUser(principal.getName()).get();
    	service.stealToken(game, user);
    	service.incrementTurn(game);
    	return new ModelAndView("redirect:/games/"+id+"/play");
    }
    
    @GetMapping("/{id}/play/playTile/{tileId}/{cellId}")
    public ModelAndView playTile(@PathVariable int id, Principal principal, @PathVariable("tileId") int tileId, @PathVariable("cellId") int cellId) throws AlreadyTileOnCell {
    	User user = userService.findUser(principal.getName()).get();
    	service.playTile(cellId, tileId, user, id);
    	Game game = service.getGameById(id);
    	service.incrementTurn(game);
    	return new ModelAndView("redirect:/games/"+id+"/play/");
    }
    
    @GetMapping("{id}/play/skipTurn")
    public ModelAndView skipTurn(@PathVariable int id) {
 	   Game game = service.getGameById(id);
 	   service.incrementTurn(game);
 	  return new ModelAndView("redirect:/games/"+id+"/play");
    }
    
    @GetMapping("{id}/play/restartGame")
    public ModelAndView restartGame(@PathVariable int id, Principal principal) {
    	ModelAndView mav = new ModelAndView(RESTART_GAME);
    	Game game = service.getGameById(id);
    	User user = userService.findUser(principal.getName()).get();
    	mav.addObject("game", game);
    	mav.addObject("user", user);
    	return mav;
    }
    
    @GetMapping("{id}/play/restart")
    public ModelAndView restart(@PathVariable int id, Principal principal) {
    	Game game = service.getGameById(id);
    	User user = userService.findUser(principal.getName()).get();
    	service.restartGame(game, user);
    	service.save(game);
    	return new ModelAndView("redirect:/games/"+id+"/play");
    }
    
   @GetMapping("{id}/play/finishGame")
   public ModelAndView finishGame(@PathVariable int id, Principal principal) {
	   Game game = service.getGameById(id);
   	   User user = userService.findUser(principal.getName()).get();
   	   List<ScoreBoard> sbs = scoreboardService.getScoreboardsByGameId(id);
	   ModelAndView mav = null;
	   if(game.getMode().charAt(0) == 'C') {
		   mav = new ModelAndView(FINISH_GAME_COMPETITIVE);
		   List<ScoreBoard> sbsSorted = sbs.stream()
				   .sorted(Comparator.comparingInt(ScoreBoard::getScore)
						   .thenComparing(Comparator.comparing(ScoreBoard::getOrden))
						   .reversed())
				   .collect(Collectors.toList());
		   User winner = sbsSorted.get(0).getUser();
		   Profile p = winner.getProfile();
		   if (winner.getUsername() == principal.getName()) {
			   p.setWins(p.getWins()+1);
		   }
	   	   userService.saveUser(winner);
	   	   achievementService.updateAchievements(p);
		   mav.addObject("winner", winner);
		   mav.addObject("sbssorted", sbsSorted);
	   } else {
		   mav = new ModelAndView(FINISH_GAME);
	   }
   	   service.finishGame(game, user);
	   mav.addObject("game", game);
	   mav.addObject("user", user);
	   mav.addObject("scoreboards", sbs);
	   mav.addObject("scoreBoard", sbs.get(0));
	   Profile p = user.getProfile();
	   mav.addObject("profile", p);
	   return mav;
   }
   
   @GetMapping("{id}/play/wonGame")
   public ModelAndView wonGame(@PathVariable int id, Principal principal) {
	   ModelAndView mav = new ModelAndView(WON_GAME);
	   Game game = service.getGameById(id);
   	   User user = userService.findUser(principal.getName()).get();
   	   Profile p = user.getProfile();
   	   p.setWins(p.getWins()+1);
   	   userService.saveUser(user);
   	   achievementService.updateAchievements(p);
   	   List<ScoreBoard> sbs = scoreboardService.getScoreboardsByGameId(id);
   	   service.finishGame(game, user);
	   mav.addObject("game", game);
	   mav.addObject("user", user);
	   mav.addObject("scoreboards", sbs);
	   return mav;
   }
   
   @GetMapping("{username}")
   public ModelAndView myGames(Principal principal) {
	   ModelAndView mav = new ModelAndView(GAMES_LISTING_VIEW);
	   User user = userService.findUser(principal.getName()).get();
	   List<Game> myGames = service.getGamesByUser(user);
	   System.out.println(myGames);
	   mav.addObject("games", myGames);
	   return mav;
	   
   }
   
}