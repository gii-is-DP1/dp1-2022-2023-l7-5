package org.springframework.samples.petclinic.cell;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CellService {
	CellRepository repo;
	
	@Autowired CellService(CellRepository repo) {
		this.repo = repo;
	}
	
	List<Cell> getCells() {
		return repo.findAll();
	}
	
	public Cell getCellById(Integer id) {
		return repo.findById(id).get();
	}
	
	public void deleteCellById(Integer id) {
		repo.deleteById(id);
	}
	
	public void save(Cell cell) {
		repo.save(cell);
	}
}
