package org.springframework.samples.petclinic.tile;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends CrudRepository<Tile, Integer> {
	List<Tile> findAll();
}
