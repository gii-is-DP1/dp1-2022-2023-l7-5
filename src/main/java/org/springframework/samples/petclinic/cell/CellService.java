package org.springframework.samples.petclinic.cell;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.tile.Tile;
import org.springframework.samples.petclinic.tile.TileService;
import org.springframework.samples.petclinic.user.User;


@Service
public class CellService {
	CellRepository repo;
	
	@Autowired
	TileService tileService;
	
	@Autowired
	ScoreBoardService scoreBoardService;
	
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
	
	@Transactional
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
	
	@Transactional
	public Set<Cell> detectMatch(Integer cellId, User user, Game game) {
		Cell cell = repo.findById(cellId).get();
		Set<Cell> match = new HashSet<Cell>();
		if(cell.getTile()!=null) {
			String color = cell.getTile().getStartingSide();
			if (cell.getIsFlipped()) {
				color = cell.getTile().getFilledSide();
			}
			List<Cell> adjacents = cell.getAdjacents();
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
		}
		if (match.size() >= 3) {
			resolveMatch(match, user, game);
			this.scoreBoardService.increaseScore(match.size()-2, user.getUsername(), game);
		}
		return match;
	}
	
	@Transactional
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

	@Transactional
	public void resolveMatch(Set<Cell> match, User user, Game game) {
			for (Cell cell : match) {
				if (cell.getIsFlipped()) {
					cell.setTile(null);
					this.scoreBoardService.increaseScore(1, user.getUsername(), game);
				}
				cell.setIsFlipped(!cell.getIsFlipped());
				repo.save(cell);
			}
			for (Cell cell : repo.findAll()) {
				detectMatch(cell.getId(), user, game);
			}
		}
}
