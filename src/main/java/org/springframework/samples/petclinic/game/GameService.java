package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	GameRepository repository;

	@Autowired GameService(GameRepository repository) {
		this.repository = repository;
	}
	
	List<Game> getGames() {
		return repository.findAll();
	}
	
	public Game getGameById(Integer id) {
    	return repository.findById(id).get();
    }
    
    public void deleteGameById(Integer id) {
    	repository.deleteById(id);
    }
    
    public void save(Game game) {
    	repository.save(game);
    }
}