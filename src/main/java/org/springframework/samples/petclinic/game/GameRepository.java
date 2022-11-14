package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
	List<Game> findAll();
	
	@Query("SELECT mode FROM Mode mode ORDER BY mode.name")
	List<Mode> findModes() throws DataAccessException;
}