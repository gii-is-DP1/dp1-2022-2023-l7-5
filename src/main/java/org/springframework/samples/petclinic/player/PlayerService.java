package org.springframework.samples.petclinic.player;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;	


@Service
public class PlayerService {
	
PlayerRepository repo;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;
	
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
    	userService.saveUser(player.getUsername());
    	authoritiesService.saveAuthorities(player.getUsername().getUsername(), "player");
    }

}
