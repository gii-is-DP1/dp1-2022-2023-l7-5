package org.springframework.samples.petclinic.cell;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends CrudRepository<Cell, Integer>{
	List<Cell> findAll();
}
