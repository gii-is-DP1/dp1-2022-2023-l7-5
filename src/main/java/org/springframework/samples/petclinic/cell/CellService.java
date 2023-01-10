package org.springframework.samples.petclinic.cell;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;

import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;

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

	@Autowired
	AchievementService achievementServ;

	@Autowired
	ProfileService profileService;

	@Autowired
	CellService(CellRepository repo) {
		this.repo = repo;
	}

	public List<Cell> getCells() {
		return repo.findAll();
	}

	public Cell getCellById(Integer id) {
		return repo.findById(id).get();
	}
	
	public Cell getCellByPosition(Integer pos) {
		return repo.findByPosition(pos);
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
		if (cell.getTile() == null) {
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
		if (cell.getTile() != null) {
			String color = cell.getTile().getStartingSideColor();
			if (cell.getIsFlipped()) {
				color = cell.getTile().getFilledSideColor();
			}
			List<Cell> adjacents = cell.getAdjacents();
			match.add(cell);
			for (Cell cellAdj : adjacents) {
				if (cellAdj.getTile() == null) {
					continue;
				} else if (cellAdj.getIsFlipped() && color == cellAdj.getTile().getFilledSideColor()) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				} else if (!cellAdj.getIsFlipped() && color == cellAdj.getTile().getStartingSideColor()) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				}
			}
		}
		
		Boolean cluster = true;
		Set<Cell> matchCopy = new HashSet<>();
		matchCopy.addAll(match);
		for (Cell mt : match) {
			Set<Cell> others = matchCopy;
			others.remove(mt);
			cluster = cluster && mt.getAdjacents().containsAll(others);
		}
		if (match.size() >= 3) {
			if (!(game.getMode().charAt(1) == 'U') || match.size() > 3) {
				resolveMatch(match, user, game);
				this.scoreBoardService.increaseScore(match.size() - 2, user.getUsername(), game);
			} else if (!cluster) {
				resolveMatch(match, user, game);
				this.scoreBoardService.increaseScore(match.size() - 2, user.getUsername(), game);
			}
		}
		return match;
	}
	
	@Transactional
	private void blockEsquinas(Set<Cell> match, Game game) {
		List<Cell> cells = this.repo.findAll();
        List<Cell> corners = cells.stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
        for (Cell corner : corners) {
        	if(match.contains(corner)) {
        		corner.setIsBlocked(true);
        		corner.setTile(null);
        		this.repo.save(corner);
        		List<Cell> adjacents = corner.getAdjacents();
        		for (Cell adjacent : adjacents) {
        			adjacent.setIsBlocked(true);
        			adjacent.setIsFlipped(false);
        			adjacent.setTile(null);
            		this.repo.save(adjacent);
        		}
        	}
        }
	}

	@Transactional
	public Set<Cell> detectNextCellMatch(Cell cell, List<Cell> adjacents, Set<Cell> match, String color) {
		for (Cell cellAdj : adjacents) {
			if (cellAdj.getTile() == null) {
				continue;
			} else if (cellAdj.getIsFlipped() && color == cellAdj.getTile().getFilledSideColor()) {
				match.add(cellAdj);
			} else if (!cellAdj.getIsFlipped() && color == cellAdj.getTile().getStartingSideColor()) {
				match.add(cellAdj);
			}
		}
		return match;
	}

	@Transactional
	public void resolveMatch(Set<Cell> match, User user, Game game) {
		Profile p = user.getProfile();
		p.setMatches(p.getMatches() + 1);
		achievementServ.updateAchievements(p);
		for (Cell cell : match) {
			if (cell.getIsFlipped()) {
				cell.setTile(null);
				this.scoreBoardService.increaseScore(1, user.getUsername(), game);
			}
			cell.setIsFlipped(!cell.getIsFlipped());
			repo.save(cell);
		}
		if (game.getMode().charAt(1) == 'U') {
			blockEsquinas(match, game);
		}
		for (Cell cell : repo.findAll()) {
			detectMatch(cell.getId(), user, game);
		}
		
	}
	
}
