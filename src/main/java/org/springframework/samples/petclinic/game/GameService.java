package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	private GameRepository repository;

	@Autowired 
	public GameService(GameRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Mode> findModes() throws DataAccessException {
		return repository.findModes();
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