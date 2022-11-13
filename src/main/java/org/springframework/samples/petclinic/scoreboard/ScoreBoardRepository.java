package org.springframework.samples.petclinic.scoreboard;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ScoreBoardRepository extends CrudRepository<ScoreBoard, Integer> {
	List<ScoreBoard> findAll();
}

