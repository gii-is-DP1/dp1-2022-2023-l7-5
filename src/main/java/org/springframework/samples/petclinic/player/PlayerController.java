package org.springframework.samples.petclinic.player;

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
@RequestMapping("/players")
public class PlayerController {
	
	private final String  PLAYERS_LISTING_VIEW = "/players/PlayersListing";
    private final String  PLAYERS_FORM = "/players/createOrUpdatePlayersForm";
    
    private PlayerService service;
    
    @Autowired
    public PlayerController(PlayerService service) {
    	this.service = service;
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/PlayersListing")
    public ModelAndView showPlayers() {
    	ModelAndView mav = new ModelAndView(PLAYERS_LISTING_VIEW);
    	mav.addObject("players", service.getPlayers());
    	return mav;
    }

    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deletePlayer(@PathVariable int id){
        service.deletePlayerById(id);        
        return showPlayers();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/{id}/edit")
    public ModelAndView editPlayer(@PathVariable int id){
        Player player = service.getPlayerById(id);        
        ModelAndView result = new ModelAndView(PLAYERS_FORM);
        result.addObject("players", player);
        return result;
    }
    
    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView savePlayer(@PathVariable int id, @Valid Player player, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(PLAYERS_FORM, br.getModel());
    	}
        Player playerToBeUpdated=service.getPlayerById(id);
        BeanUtils.copyProperties(player,playerToBeUpdated,"id");
        service.save(playerToBeUpdated);
        return showPlayers();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/new")
    public ModelAndView createPlayer() {
    	Player player = new Player();
    	ModelAndView mav = new ModelAndView(PLAYERS_FORM);
    	mav.addObject(player);
    	return mav;
    }
    
    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewPlayer(@Valid Player player, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(PLAYERS_FORM, br.getModel());
    	}
        service.save(player);
        ModelAndView result=showPlayers();
        result.addObject("message", "The player was created successfully");
        return result;
    }

}
