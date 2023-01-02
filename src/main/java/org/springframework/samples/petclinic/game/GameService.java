package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.Achievement;
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
	GameRepository repository;
	
	@Autowired
	protected TileService tileService;
	
	@Autowired 
	protected CellService cellService;
	
	@Autowired 
	UserService userService;
	@Autowired
	ScoreBoardService scoreboardService;
	@Autowired
	ProfileService profileService;
	@Autowired
	AchievementService achievementServ;

	@Autowired GameService(GameRepository repository) {
		this.repository = repository;
	}
	
	List<Game> getGames() {
		return repository.findAll();
	}
	
	public Game getGameById(Integer id) {
    	return repository.findById(id).get();
    }
    
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
    
    @Transactional(rollbackFor = {TooManyPlayers.class, NotThisTypeOfGame.class})
    public void joinPlayerToGame(String username, Game game) throws TooManyPlayers, NotThisTypeOfGame {
    	if (game.getNumberCurrentPlayers() == game.getNumberOfPlayers()) {
    		throw new TooManyPlayers(); 
    	} else  if (game.getMode().charAt(0) != 'C'){
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
        	this.tileService.createAllTiles();
    	}
    	List<Tile> bag = this.tileService.getTiles();
    	game.setBag(bag);
    	List<Cell> cells = this.cellService.getCells();
    	game.setCells(cells);
    	game.setTurn(1);
    	this.repository.save(game);
    	if (game.getMode().charAt(0)=='S') {
    		initSolitarieGame(game);
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
    	p.setSteals(p.getSteals()+1);
    	achievementServ.updateAchievements(p);
    	repository.save(game);
    	userService.saveUser(user);
    }
    

    @Transactional 
    public void initSolitarieGame(Game game) {
    	Set<String> colors = new HashSet<String>();
    	List<String> colorsString = List.of("red", "blue", "green", "purple", "orange", "yellow");
    	colors.addAll(colorsString);
    	List<Cell> cells = cellService.getCells();
    	List<Cell> corners = cells.stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
    	for (Cell corner : corners) {
    		Tile tile = null;
        	while(tile == null) {
            	int size = game.getBag().size();
            	Random random = new Random(System.currentTimeMillis());
                tile = game.getBag().get(random.nextInt(size));
                if (colors.contains(tile.getStartingSide())) {
                	continue;
                } else {
                	tile = null;
                }
        	}
        	colors.remove(tile.getStartingSide());
    		game.getBag().remove(tile);
        	corner.setTile(tile);
        	cellService.save(corner);
        	repository.save(game);
    	}
    }
    
    @Transactional(rollbackFor = {AlreadyTileOnCell.class})
    public void playTile(Integer cellId, Integer tileId, User user, Integer gameId) throws AlreadyTileOnCell {
    	this.cellService.putTileOnCell(cellId, tileId);
    	List<Tile> tiles = user.getTiles();
    	tiles.remove(this.tileService.getTileById(tileId));
    	user.setTiles(tiles);
    	userService.saveUser(user);
    	Game game = repository.findById(gameId).get();
    	Set<Cell> match = this.cellService.detectMatch(cellId, user, game);
    	
    }
    
    @Transactional
    public void restartGame(Game game, User user) {
    	user.setTiles(new ArrayList<>());
    	List<Cell> cells = game.getCells();
    	for(Cell c : cells) {
    		c.setTile(null);
    		c.setIsFlipped(false);
    		this.cellService.save(c);
    	}
    	game.setBag(this.tileService.getTiles());
    	game.setFinished(false);
    	ScoreBoard sb = this.scoreboardService.getScoreBoardByGameIdByUser(user.getUsername(), game.getId());
    	sb.setScore(0);
    	this.scoreboardService.save(sb);
    	this.userService.saveUser(user);
    	repository.save(game);
    	initSolitarieGame(game);
    }
    
    @Transactional
    public void finishGame(Game game, User user) {
    	game.setFinished(true);
    	user.setTiles(null);
    	List<Cell> cells = game.getCells();
    	for(Cell c : cells) {
    		c.setTile(null);
    		c.setIsFlipped(false);
    		this.cellService.save(c);
    	}
    	game.setCells(null);
    	game.setBag(null);
    	repository.save(game);
    	this.userService.saveUser(user);
    	Profile p = user.getProfile();
    	p.setPlayedGames(p.getPlayedGames()+1);
    	achievementServ.updateAchievements(p);
    	this.profileService.save(p);
    }
    
    @Transactional
    public void incrementTurn(Game game) {
    	Integer t = game.getTurn();
    	if(t==game.getNumberCurrentPlayers()) {
    		t = 1;
    	} else {
    		t++;
    	}
    	game.setTurn(t);
    	repository.save(game);
    }
}