package org.springframework.samples.petclinic.cell;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.tile.Tile;
import org.springframework.samples.petclinic.tile.TileService;


@Service
public class CellService {
	CellRepository repo;
	
	@Autowired
	TileService tileService;
	
	@Autowired CellService(CellRepository repo) {
		this.repo = repo;
	}
	
	public List<Cell> getCells() {
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
	
	public void putTileOnCell(Integer cellId, Integer tileId) throws AlreadyTileOnCell {
		Cell cell = repo.findById(cellId).get();
		if (cell.getTile()==null) {
		Tile tile = tileService.getTileById(tileId);
		cell.setTile(tile);
		repo.save(cell);
		} else {
			throw new AlreadyTileOnCell();
		}
	}
	
	public Set<Cell> detectMatch(Integer cellId) {
		Cell cell = repo.findById(cellId).get();
		String color = cell.getTile().getStartingSide();
		List<Cell> adjacents = cell.getAdjacents();
		Set<Cell> match = new HashSet();
		match.add(cell);
		for (Cell cellAdj : adjacents) {
			if (cellAdj.getTile() == null) {
				continue;
			} else if (cellAdj.getTile().getFilledSide() != color &&
					cellAdj.getTile().getStartingSide() != color) {
				continue;
			} else {
				if (cellAdj.getIsFlipped() && cellAdj.getTile().getFilledSide() == color) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				} else if (!cellAdj.getIsFlipped() && cellAdj.getTile().getStartingSide() == color) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				}
			}
		}
		return match;
	}
	
	public Set<Cell> detectNextCellMatch(Cell cell, List<Cell> adjacents, Set<Cell> match, String color) {
		for (Cell cellAdj : adjacents) {
			if (cellAdj.getTile() == null) {
				continue;
			} else if (cellAdj.getTile().getFilledSide() != color &&
					cellAdj.getTile().getStartingSide() != color) {
				continue;
			} else {
				if (cellAdj.getIsFlipped() && cellAdj.getTile().getFilledSide() == color) {
					match.add(cellAdj);
				} else if (!cellAdj.getIsFlipped() && cellAdj.getTile().getStartingSide() == color) {
					match.add(cellAdj);
				}
			}
		}
		return match;
	}
}
