package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {
	
	private final String  GAMES_LISTING_VIEW = "games/GamesListing";
    private final String  GAMES_FORM = "games/createOrUpdateGameForm";

    List<String> modes = List.of("COMPETITIVE", "SOLO", "SURVIVAL");
    List<Integer> nPlayers = List.of(1, 2, 3, 4);

    private GameService service;
    
    @Autowired
    public GameController(GameService service) {
    	this.service = service;
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
    public ModelAndView createGame() {
    	Game game = new Game();
    	game.setFinished(false);
    	game.setDateOfCreation(LocalDate.now());
    	ModelAndView mav = new ModelAndView(GAMES_FORM);
    	mav.addObject(game);
    	mav.addObject("nplayers", nPlayers);
    	mav.addObject("modes", modes);
    	return mav;
    }
    
    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewGame(@Valid Game game, BindingResult br) {
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
        ModelAndView result = showGames();
        result.addObject("message", "The game was created successfully.");
        return result;
    }
}