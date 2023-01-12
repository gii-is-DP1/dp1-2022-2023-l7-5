package org.springframework.samples.petclinic.tile;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	

@Service
public class TileService {
	TileRepository repo;
	
	@Autowired TileService(TileRepository repo) {
		this.repo = repo;
	}
	
	public List<Tile> getTiles() {
		return repo.findAll();
	}
	
	public Tile getTileById(Integer id) {
    	return repo.findById(id).get();
    }
    
    public void deleteTileById(Integer id) {
    	repo.deleteById(id);
    }
    
    public void save(Tile tile) {
    	repo.save(tile);
    }
    
    public void createAllTiles() {
    	List<String> colors = List.of("red", "blue", "purple", "green", "yellow", "orange");
    	HashMap<String, List<String>> colorsImages = new HashMap<>(); 
    	colorsImages.put("red", List.of("https://imgur.com/9G8Pe0A.png?1", "https://imgur.com/QPPiSyd.png?1"));
    	colorsImages.put("blue", List.of("https://imgur.com/vuJZUUw.png?1", "https://imgur.com/Z1BhNUR.png?1"));
    	colorsImages.put("purple", List.of("https://imgur.com/WwELeLW.png?1", "https://imgur.com/K0e5pCB.png?1"));
    	colorsImages.put("green", List.of("https://imgur.com/kWun3bJ.png?1", "https://imgur.com/LFHtM1A.png?1"));
    	colorsImages.put("yellow", List.of("https://imgur.com/lPCw0o5.png?1", "https://imgur.com/eBtkb5g.png?1"));
    	colorsImages.put("orange", List.of("https://imgur.com/vVsXSra.png?1", "https://imgur.com/v9XOBYk.png?1"));
    	for (String startingColor : colors) {
    		for (String filledColor : colors) {
    			Tile tile = new Tile();
        		tile.setStartingSide(colorsImages.get(startingColor).get(0));
        		tile.setFilledSide(colorsImages.get(filledColor).get(1));
        		tile.setStartingSideColor(startingColor);
        		tile.setFilledSideColor(filledColor);
        		Tile tileDup = new Tile();
        		tileDup.setStartingSide(colorsImages.get(startingColor).get(0));
        		tileDup.setFilledSide(colorsImages.get(filledColor).get(1));
        		tileDup.setStartingSideColor(startingColor);
        		tileDup.setFilledSideColor(filledColor);
        		repo.save(tile);
        		repo.save(tileDup);
    		}
    	}
    }
}
