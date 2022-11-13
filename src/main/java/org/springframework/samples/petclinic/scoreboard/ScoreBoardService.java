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
	
	List<ScoreBoard> getScoreBoard() {
		return repo.findAll();
	}
	
	public ScoreBoard getScoreBoardById(Integer id) {
		return repo.findById(id).get();
	}
	
    public void save(ScoreBoard scores) {
    	repo.save(scores);
    }
	
}
