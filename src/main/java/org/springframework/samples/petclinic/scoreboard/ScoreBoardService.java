package org.springframework.samples.petclinic.scoreboard;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;	

@Service
public class ScoreBoardService {

	ScoreBoardRepository repo;

	@Autowired 
	protected UserService userService;

	
	@Autowired ScoreBoardService(ScoreBoardRepository repo) {
		this.repo = repo;
	}
	
	List<ScoreBoard> getScoreBoards() {
		return repo.findAll();    
	}
	
	public ScoreBoard getScoreBoardById(Integer id) {
    	return repo.findById(id).get();
    }
    
    public void deleteScoreBoardById(Integer id) {
    	repo.deleteById(id);
    }
    
    public ScoreBoard getScoreBoardByGameIdByUser(String username, Integer id) {
    	return repo.getScoreboardByGameIdByUser(username, id);
    }
	
	public void save(ScoreBoard scores) {
    	repo.save(scores);
    }
	    
	public List<ScoreBoard> getScoreboardsByGameId(Integer id) {
	    return repo.getScoreboardsByGameId(id);
	}
	
	public List<ScoreBoard> getScoreBoardByUser(String username) {
		return repo.getScoreboardsByUser(username);
	}
	
	@Transactional
	public void increaseScore(Integer i, String username, Game game) {
		ScoreBoard sb = repo.getScoreboardByGameIdByUser(username, game.getId());
		sb.setScore(i + sb.getScore());
		repo.save(sb);
	}
	
}