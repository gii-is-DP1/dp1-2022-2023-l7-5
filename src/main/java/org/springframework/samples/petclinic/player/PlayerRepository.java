package org.springframework.samples.petclinic.player;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repositorio
@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
	List<Player> findAll();

}
