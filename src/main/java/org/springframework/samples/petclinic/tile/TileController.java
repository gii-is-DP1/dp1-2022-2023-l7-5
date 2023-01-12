package org.springframework.samples.petclinic.tile;

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
@RequestMapping("/tiles")
public class TileController {
    private final String  TILES_LISTING_VIEW = "/tiles/TilesListing";
    private final String  TILES_FORM = "/tiles/createOrUpdateTilesForm";

    
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
    
    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deleteTile(@PathVariable int id){
        service.deleteTileById(id);        
        return showTiles();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/{id}/edit")
    public ModelAndView editTile(@PathVariable int id){
        Tile tile = service.getTileById(id);        
        ModelAndView result = new ModelAndView(TILES_FORM);
        result.addObject("tiles", tile);
        return result;
    }
    
    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveTile(@PathVariable int id, @Valid Tile tile, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(TILES_FORM, br.getModel());
    	}
        Tile tileToBeUpdated=service.getTileById(id);
        BeanUtils.copyProperties(tile,tileToBeUpdated,"id");
        service.save(tileToBeUpdated);
        return showTiles();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/new")
    public ModelAndView createTile() {
    	Tile tile = new Tile();
    	ModelAndView mav = new ModelAndView(TILES_FORM);
    	mav.addObject(tile);
    	return mav;
    }
    
    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewTile(@Valid Tile tile, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(TILES_FORM, br.getModel());
    	}
        service.save(tile);
        ModelAndView result=showTiles();
        result.addObject("message", "The tile was created successfully");
        return result;
    }
}
