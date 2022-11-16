package org.springframework.samples.petclinic.scoreboard;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	

@Service
public class ScoreBoardService {

ScoreBoardRepository repo;
	
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
	    
	public List<ScoreBoard> getScoreboardsById(Integer id) {
	    return repo.getScoreboardsByGameId(id);
	}
	
}