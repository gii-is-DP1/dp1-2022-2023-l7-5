package org.springframework.samples.petclinic.tile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tiles")
public class TileController {
    private final String  TILES_LISTING_VIEW = "/tiles/TilesListing";
    
    private TileService service;
    
    @Autowired
    public TileController(TileService service) {
    	this.service = service;
    }
    @Transactional(readOnly = true)
    @GetMapping("/TilesListing")
    public ModelAndView showTiles() {
    	ModelAndView mav = new ModelAndView(TILES_LISTING_VIEW);
    	mav.addObject("tiles", service.getTiles());
    	return mav;
    }
    
    
}
