package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	GameRepository repository;
	
	@Autowired 
	UserService userService;
	@Autowired
	ScoreBoardService scoreboardService;

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
    
    @Transactional
    public void initPlayerToGame(String username, Game game) {
    	ScoreBoard sb = new ScoreBoard();
    	User user = userService.findUser(username).get();
    	sb.setOrden(1);
    	sb.setScore(0);
    	sb.setUser(user);
    	sb.setGame(game);
    	scoreboardService.save(sb);
    }
    
    @Transactional(rollbackFor = {TooManyPlayers.class, NotThisTypeOfGame.class})
    public void joinPlayerToGame(String username, Game game) throws TooManyPlayers, NotThisTypeOfGame {
    	if (game.getNumberCurrentPlayers() == game.getNumberOfPlayers()) {
    		throw new TooManyPlayers(); 
    	} else  if (game.getMode().charAt(0) != 'C'){
    		throw new NotThisTypeOfGame();
    	} else {
    		ScoreBoard sb = new ScoreBoard();
    		User user = userService.findUser(username).get();
    		Integer currentPlayers = game.getNumberCurrentPlayers();
    		game.setNumberCurrentPlayers(currentPlayers + 1);
    		save(game);
    		sb.setOrden(game.getNumberCurrentPlayers());
    		sb.setScore(0);
    		sb.setUser(user);
    		sb.setGame(game);
    		scoreboardService.save(sb);
    	}
    }
    
}