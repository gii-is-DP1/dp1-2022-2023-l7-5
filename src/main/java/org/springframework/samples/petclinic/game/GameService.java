package org.springframework.samples.petclinic.game;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.cell.CellService;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.tile.Tile;
import org.springframework.samples.petclinic.tile.TileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	protected GameRepository repository;

	@Autowired
	protected TileService tileService;

	@Autowired
	protected CellService cellService;

	@Autowired
	protected UserService userService;
	@Autowired
	protected  ScoreBoardService scoreboardService;
	@Autowired
	protected ProfileService profileService;
	@Autowired
	protected AchievementService achievementServ;

	@Autowired
	GameService(GameRepository repository) {
		this.repository = repository;
	}

	List<Game> getGames() {
		return repository.findAll();
	}

	public Game getGameById(Integer id) {
    	return repository.findById(id).get();
    }
    
	@Transactional
    public void deleteGameById(Integer id) {
    	repository.deleteById(id);
    }
    
    public void save(Game game) {
    	repository.save(game);
    }

	@Transactional
	public void initPlayerToGame(String username, Game game) {
		game.setNumberCurrentPlayers(1); 
		repository.save(game);
		ScoreBoard sb = new ScoreBoard();
		User user = userService.findUser(username).get();
		repository.save(game);
		sb.setOrden(1);
		sb.setScore(0);
		sb.setUser(user);
		sb.setGame(game);
		scoreboardService.save(sb);
		if (user.getProfile() == null) {
			profileService.initProfile(user);
		}
	}

	@Transactional(rollbackFor = { TooManyPlayers.class, NotThisTypeOfGame.class })
	public void joinPlayerToGame(String username, Game game) throws TooManyPlayers, NotThisTypeOfGame {
		if (game.getNumberCurrentPlayers() == game.getNumberOfPlayers()) {
			throw new TooManyPlayers();
		} else if (game.getMode().charAt(0) != 'C') {
			throw new NotThisTypeOfGame();
		} else {
			ScoreBoard sb = new ScoreBoard();
			User user = userService.findUser(username).get();
			Integer currentPlayers = game.getNumberCurrentPlayers();
			game.setNumberCurrentPlayers(currentPlayers + 1);
			sb.setOrden(game.getNumberCurrentPlayers());
			sb.setScore(0);
			sb.setUser(user);
			sb.setGame(game);
			scoreboardService.save(sb);
			if (user.getProfile() == null) {
				profileService.initProfile(user);
			}
			repository.save(game);
		}
	}

	@Transactional
	public void initGame(Integer id) {
		Game game = getGameById(id);
		if (tileService.getTiles().isEmpty()) {
			tileService.createAllTiles();
		}
		List<Tile> bag = tileService.getTiles();
		game.setBag(bag);
		List<Cell> cells = cellService.getCells();
		game.setCells(cells);
		game.setTurn(1);
		repository.save(game);
		if (game.getMode().charAt(2) == 'L') {
			initSolitarieGame(game);
		} else if (game.getMode().charAt(1) == 'U') {
			initSurvivalGame(game);
		}
	}

	@Transactional
	public void stealToken(Game game, User user) {
		int size = game.getBag().size();
		Random random = new Random(System.currentTimeMillis());
		Tile tile = game.getBag().get(random.nextInt(size));
		List<Tile> tiles = user.getTiles();
		tiles.add(tile);
		user.setTiles(tiles);
		game.getBag().remove(tile);
		Profile p = user.getProfile();
		p.setSteals(p.getSteals() + 1);
		achievementServ.updateAchievements(p);
		repository.save(game);
		userService.saveUser(user);
	}

	@Transactional
	public void deleteTilesSurvival(Game game) {
		for (Tile t : tileService.getTiles()) {
			String starting = t.getStartingSideColor();
			String filled = t.getFilledSideColor();
			if (starting == filled) {
				game.getBag().remove(t);
				repository.save(game);
			}
		}
	}

	@Transactional
	public void initSolitarieGame(Game game) {
		Set<String> colors = new HashSet<String>();
		List<String> colorsString = List.of("red", "blue", "green", "purple", "orange", "yellow");
		colors.addAll(colorsString);
		List<Cell> cells = cellService.getCells();
		List<Cell> corners = cells.stream().filter(c -> c.getAdjacents().size() == 3).collect(Collectors.toList());
		for (Cell corner : corners) {
			Tile tile = null;
			while (tile == null) {
				int size = game.getBag().size();
				Random random = new Random(System.currentTimeMillis());
				tile = game.getBag().get(random.nextInt(size));
				if (colors.contains(tile.getStartingSideColor())) {
					continue;
				} else {
					tile = null;
				}
			}
			colors.remove(tile.getStartingSideColor());
			game.getBag().remove(tile);
			corner.setTile(tile);
			cellService.save(corner);
			repository.save(game);
		}
	}

	@Transactional
	public void initSurvivalGame(Game game) {
		deleteTilesSurvival(game);
		Set<String> colors = new HashSet<String>();
		List<String> colorsString = List.of("red", "blue", "green", "purple", "orange", "yellow");
		colors.addAll(colorsString);
		List<Cell> cells = cellService.getCells();
		List<Cell> corners = cells.stream().filter(c -> c.getAdjacents().size() == 3).collect(Collectors.toList());
		repository.save(game);
		for (Cell corner : corners) {
			Tile tile = null;
			while (tile == null) {
				int size = game.getBag().size();
				Random random = new Random(System.currentTimeMillis());
				tile = game.getBag().get(random.nextInt(size));
				if (colors.contains(tile.getFilledSideColor())) {
					continue;
				} else {
					tile = null;
				}
			}
			colors.remove(tile.getFilledSideColor());
			game.getBag().remove(tile);
			corner.setTile(tile);
			cellService.save(corner);
			corner.setIsFlipped(true);
			cellService.save(corner);
			repository.save(game);
		}
	}

	@Transactional(rollbackFor = { AlreadyTileOnCell.class })
	public void playTile(Integer cellId, Integer tileId, User user, Integer gameId) throws AlreadyTileOnCell {
		cellService.putTileOnCell(cellId, tileId);
		List<Tile> tiles = user.getTiles();
		tiles.remove(tileService.getTileById(tileId));
		user.setTiles(tiles);
		userService.saveUser(user);
		Game game = repository.findById(gameId).get();
		cellService.detectMatch(cellId, user, game);
	}

	@Transactional
	public void restartGame(Game game, User user) {
		user.setTiles(new ArrayList<>());
		List<Cell> cells = game.getCells();
		for (Cell c : cells) {
			c.setTile(null);
			c.setIsFlipped(false);
			c.setIsBlocked(false);
			cellService.save(c);
		}
		game.setBag(tileService.getTiles());
		game.setFinished(false);
		ScoreBoard sb = scoreboardService.getScoreBoardByGameIdByUser(user.getUsername(), game.getId());
		sb.setScore(0);
		scoreboardService.save(sb);
		userService.saveUser(user);
		repository.save(game);
		if (game.getMode().charAt(2) == 'L') {
			initSolitarieGame(game);
		} else if (game.getMode().charAt(1) == 'U') {
			initSurvivalGame(game);
		}
	}

	@Transactional
	public void finishGame(Game game, User user) {
		game.setFinished(true);
		user.setTiles(null);
		List<Cell> cells = game.getCells();
		for (Cell c : cells) {
			c.setTile(null);
			c.setIsBlocked(false);
			c.setIsFlipped(false);
			cellService.save(c);
		}
		game.setCells(null);
		game.setBag(null);
		repository.save(game);
		userService.saveUser(user);
		Profile p = user.getProfile();
		p.setPlayedGames(p.getPlayedGames() + 1);
		achievementServ.updateAchievements(p);
		achievementServ.updateGlobalAchievements();
		profileService.updateGlobal();
		ScoreBoard sb = game.getScoreboards().get(0);
		sb.setNewRecord(false);
		Integer puntuacion = sb.getScore();
		if (game.getMode().charAt(1)=='U') {
			if (p.getRecord() == null) {
				p.setRecord(puntuacion);
				sb.setNewRecord(true);
			} else {
				if (puntuacion > p.getRecord()) {
					p.setRecord(puntuacion);
					sb.setNewRecord(true);
				}
			}
		}
		profileService.save(p);
		scoreboardService.save(sb);
	}

	@Transactional
	public void incrementTurn(Game game) {
		Integer t = game.getTurn();
		if (t == game.getNumberCurrentPlayers()) {
			t = 1;
		} else {
			t++;
		}
		game.setTurn(t);
		repository.save(game);
	}
	
	@Transactional(readOnly = true)
	public List<Game> getGamesByUser(User user) {
		List<ScoreBoard> sbs = scoreboardService.getScoreBoardByUser(user.getUsername());
		return sbs.stream().map(sb -> repository.findById(sb.getGame().getId()).get()).collect(Collectors.toList());
	}
	
}