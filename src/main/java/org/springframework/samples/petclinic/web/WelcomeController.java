package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.cell.CellService;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.tile.TileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@Autowired
	TileService tileService;
	@Autowired
	CellService cellService;
	
	@GetMapping({"/","/welcome"})
	public ModelAndView welcome(Map<String, Object> model, Principal principal) {
		ModelAndView mav = new ModelAndView("welcome");
		if (principal != null) {
			mav.addObject("username", principal.getName());
		}
		return mav;
	}
	
	@GetMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		if (tileService.getTiles().size()==0) {
			tileService.createAllTiles();
		}
		System.out.println(cellService.detectMatch(11));
		for (Cell adjacent : cellService.detectMatch(11)) {
			System.out.println(adjacent.getId() + adjacent.getTile().getStartingSide());
		}
		
		return mav;
	}
}