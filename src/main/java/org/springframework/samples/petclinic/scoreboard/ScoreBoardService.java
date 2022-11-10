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
	
	ScoreBoard getScoreBoard() {
		return repo.findAll().get(0);    //Solo hay un marcador entonces no se como coger el único
	}
	
	/*public ScoreBoard getScoreBoardById(Integer id) {
    	return repo.findById(id).get();
    }
    
    public void deleteScoreBoardById(Integer id) {
    	repo.deleteById(id);
    }*/
	
	//No veo más sentido que actualizar
    
    public void save(ScoreBoard scores) {
    	repo.save(scores);
    }
	
}
