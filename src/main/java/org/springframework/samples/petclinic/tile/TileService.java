package org.springframework.samples.petclinic.tile;

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
    	
    	for (String startingColor : colors) {
    		for (String filledColor : colors) {
    			Tile tile = new Tile();
        		tile.setStartingSide(startingColor);
        		tile.setFilledSide(filledColor);
        		Tile tileDup = new Tile();
        		tileDup.setStartingSide(startingColor);
        		tileDup.setFilledSide(filledColor);
        		repo.save(tile);
        		repo.save(tileDup);
    		}
    	}
    }
    
}
