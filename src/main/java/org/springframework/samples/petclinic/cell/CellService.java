package org.springframework.samples.petclinic.cell;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			String color = cell.getTile().getStartingSide();
			if (cell.getIsFlipped()) {
				color = cell.getTile().getFilledSide();
			}
			List<Cell> adjacents = cell.getAdjacents();
			match.add(cell);
			for (Cell cellAdj : adjacents) {
				if (cellAdj.getTile() == null) {
					continue;
				} else if (cellAdj.getIsFlipped() && aux(color, cellAdj.getTile().getFilledSide())) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				} else if (!cellAdj.getIsFlipped() && aux(color, cellAdj.getTile().getStartingSide())) {
					match.add(cellAdj);
					match = detectNextCellMatch(cellAdj, cellAdj.getAdjacents(), match, color);
				}
			}
		}

		if (match.size() >= 3) {
			resolveMatch(match, user, game);
			this.scoreBoardService.increaseScore(match.size() - 2, user.getUsername(), game);
		}
		return match;
	}

	@Transactional
	public Set<Cell> detectNextCellMatch(Cell cell, List<Cell> adjacents, Set<Cell> match, String color) {
		for (Cell cellAdj : adjacents) {
			if (cellAdj.getTile() == null) {
				continue;
			} else if (cellAdj.getIsFlipped() && aux(color, cellAdj.getTile().getFilledSide())) {
				match.add(cellAdj);
			} else if (!cellAdj.getIsFlipped() && aux(color, cellAdj.getTile().getStartingSide())) {
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
		for (Cell cell : repo.findAll()) {
			detectMatch(cell.getId(), user, game);
		}
	}
	
	@Transactional
	public Boolean aux(String color, String colorAdj){
		Boolean res= false;
		// blue
		if (color.equals("https://imgur.com/Z1BhNUR.png?1") && colorAdj.equals("https://imgur.com/vuJZUUw.png?1")
			|| color == colorAdj ||
			colorAdj.equals("https://imgur.com/Z1BhNUR.png?1") && color.equals("https://imgur.com/vuJZUUw.png?1")) {
			res=true;
		}
		// green
		else if (color.equals("https://imgur.com/LFHtM1A.png?1") && colorAdj.equals("https://imgur.com/kWun3bJ.png?1")
				|| color == colorAdj ||
				colorAdj.equals("https://imgur.com/LFHtM1A.png?1") && color.equals("https://imgur.com/kWun3bJ.png?1")) {
			res=true;
		}
		// orange
		else if (color.equals("https://imgur.com/v9XOBYk.png?1") && colorAdj.equals("https://imgur.com/vVsXSra.png?1")
				|| color == colorAdj ||
				colorAdj.equals("https://imgur.com/v9XOBYk.png?1") && color.equals("https://imgur.com/vVsXSra.png?1")) {
			res=true;
		}
		// purple
		else if (color.equals("https://imgur.com/K0e5pCB.png?1") && colorAdj.equals("https://imgur.com/WwELeLW.png?1")
				|| color == colorAdj ||
				colorAdj.equals("https://imgur.com/K0e5pCB.png?1") && color.equals("https://imgur.com/WwELeLW.png?1")) {
			res=true;
		}
		// red
		else if (color.equals("https://imgur.com/QPPiSyd.png?1") && colorAdj.equals("https://imgur.com/9G8Pe0A.png?1")
				|| color == colorAdj ||
				colorAdj.equals("https://imgur.com/QPPiSyd.png?1") && color.equals("https://imgur.com/9G8Pe0A.png?1")) {
			res=true;
		}
		// yellow
		else if (color.equals("https://imgur.com/eBtkb5g.png?1") && colorAdj.equals("https://imgur.com/lPCw0o5.png?1")
				|| color == colorAdj ||
				colorAdj.equals("https://imgur.com/eBtkb5g.png?1") && color.equals("https://imgur.com/lPCw0o5.png?1")) {
			res=true;
		}
		return res;
	}
}
