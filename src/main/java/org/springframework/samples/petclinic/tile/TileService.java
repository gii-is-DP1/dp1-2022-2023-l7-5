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
	
	List<Tile> getTiles() {
		return repo.findAll();
	}
}
