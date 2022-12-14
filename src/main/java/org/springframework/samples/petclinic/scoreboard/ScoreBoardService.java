package org.springframework.samples.petclinic.scoreboard;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;	

@Service
public class ScoreBoardService {

	ScoreBoardRepository repo;

	@Autowired 
	UserService userService;

	
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
	
	public void save(ScoreBoard scores) {
    	repo.save(scores);
    }
	    
	public List<ScoreBoard> getScoreboardsByGameId(Integer id) {
	    return repo.getScoreboardsByGameId(id);
	}
	
	public ScoreBoard getScoreBoardByUser(String username) {
		return repo.getScoreboardsByUser(username);
	}
	
	public void increaseScore(Integer i, String username) {
		ScoreBoard sb = getScoreBoardByUser(username);
		sb.setScore(i + sb.getScore());
		repo.save(sb);
	}
	
}