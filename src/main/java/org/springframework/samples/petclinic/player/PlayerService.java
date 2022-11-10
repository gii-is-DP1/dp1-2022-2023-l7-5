package org.springframework.samples.petclinic.player;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	


@Service
public class PlayerService {
	
PlayerRepository repo;
	//Servicio
	@Autowired 
	PlayerService(PlayerRepository repo) {
		this.repo = repo;
	}
	
	List<Player> getPlayers() {
		return repo.findAll();
	}
	
	public Player getPlayerById(Integer id) {
    	return repo.findById(id).get();
    }
    
    public void deletePlayerById(Integer id) {
    	repo.deleteById(id);
    }
    
    public void save(Player player) {
    	repo.save(player);
    }

}
