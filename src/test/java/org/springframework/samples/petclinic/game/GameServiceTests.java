package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;





import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.cell.CellService;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.tile.Tile;
import org.springframework.samples.petclinic.tile.TileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {
	
	@Autowired
	protected GameService gameService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected CellService cellService;
	
	@Autowired
	protected TileService tileService;
	
	@Autowired
	protected ScoreBoardService scoreboardService;
	
	@Autowired
	protected ProfileService profileService;
	
	public User createUser(String username) {
		User user = new User();
		user.setUsername(username);
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		user.setTiles(new ArrayList<>());
		userService.saveUser(user);
		return user;
	}
	
	public Game createGame(String mode) {
		Game game = new Game();
		game.setMode(mode);
		game.setFinished(true);
		game.setNumberOfPlayers(1);
		game.setDateOfCreation(LocalDate.now());
		game.setNumberCurrentPlayers(0);
		tileService.createAllTiles();
		List<Tile> bag = tileService.getTiles();
    	game.setBag(bag);
    	List<Cell> cells = cellService.getCells();
    	game.setCells(cells);
		gameService.save(game);
		return game;
	}

	
	@Test
	@Transactional
	public void shouldInsertGame() {
		int found = gameService.getGames().size();
		
		createGame("SURVIVAL");
		
		assertThat(gameService.getGames().size()).isEqualTo(found+1);
	}
	
	@Test
	void shouldFindGameById() {
		Game game = createGame("SURVIVAL");
		Integer id = game.getId();
		
		assertThat(gameService.getGameById(id)).isEqualTo(game);
	}
	
	@Test
	void shouldDeleteGameById() {
		Game game = createGame("SURVIVAL");
		Integer id = game.getId();
		
		assertThat(gameService.getGames().size()).isEqualTo(1);

		gameService.deleteGameById(id);
		assertThat(gameService.getGames().size()).isEqualTo(0);
	}
	
	@Test
	void shouldInitPlayerToGame() {
		User user = createUser("manuelEjemplo2");
		
		Game game = createGame("SURVIVAL");
		
		String username = user.getUsername();
		gameService.initPlayerToGame(username, game);
		
		assertThat(game.getNumberCurrentPlayers()).isEqualTo(1);
	}
	
	@Test
	void shouldJoinPlayerToGame() {
		User user = createUser("manuelEjemplo2");
		String username1 = user.getUsername();
		
		User user2 = createUser("manuelEjemplo3");
		String username2 = user2.getUsername();
		
		Game game = createGame("COMPETITIVE");
		game.setNumberOfPlayers(2);
		gameService.save(game);
		gameService.initPlayerToGame(username1, game);
		
		try {
			gameService.joinPlayerToGame(username2, game);
		} catch (TooManyPlayers | NotThisTypeOfGame e) {
			e.printStackTrace();
		}
		assertThat(game.getNumberCurrentPlayers()).isEqualTo(2);
	}
	
	@Test
	void shouldInitGame() {
		Game game = createGame("SURVIVAL");
		gameService.initGame(game.getId());
		
		assertThat(gameService.getGames().size()>0).isTrue();
	}
	
	@Test
	void shouldInitSolitarieGame() {
		Game game = createGame("SOLITARIO");
		gameService.initSolitarieGame(game);
		
		List<Cell> cells = cellService.getCells();
	     List<Cell> corners = cells.stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
	     Boolean res = true;
	     for(Cell corner : corners) {
	    	 if(corner.getTile() == null && corner.getIsFlipped() == true){
	    		 res = false;
	    	 }
	     }
	     
	     assertThat(res).isTrue();
	}
	
	@Test
	void shouldStealStoken() {
		Game game = createGame("SURVIVAL");
		User user = createUser("manuel");
		user.setTiles(new ArrayList<>());
		userService.saveUser(user);
		profileService.initProfile(user);
		userService.saveUser(user);
		gameService.stealToken(game, user);
		
		Tile tile = user.getTiles().get(0);
		
		assertThat(!game.getBag().contains(tile)).isTrue();
	}

	
	@Test
	void shouldDeleteTilesSurvival() {
		Game game = createGame("SURVIVAL");
		gameService.deleteTilesSurvival(game);
		Tile tile = tileService.getTiles().stream().filter(r -> r.getStartingSideColor()==r.getFilledSideColor())
				.findFirst().get();
		
		assertThat(!game.getBag().contains(tile)).isTrue();
	}
	
	@Test
	void shouldInitSurvivalGame() {
		Game game = createGame("SURVIVAL");
		List<Cell> cells = cellService.getCells();
		game.setCells(cells);
		gameService.save(game);
		gameService.initSurvivalGame(game);
		
	     List<Cell> corners = game.getCells().stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
	     Boolean res = true;
	     for(Cell corner : corners) {
	    	 if(corner.getTile() == null && corner.getIsFlipped() == false){
	    		 res = false;
	    	 }
	     }
		
		assertThat(res).isTrue();
	}
	
	@Test
	void shouldFinishGame() {
		Game game = createGame("SOLITARIO");
		List<Cell> cells = cellService.getCells();
		game.setCells(cells);
		gameService.save(game);
		User user = createUser("Jorge");

		gameService.initSolitarieGame(game);
		profileService.initProfile(user);
		userService.saveUser(user);
		ScoreBoard sb = new ScoreBoard();
		sb.setOrden(1);
		sb.setScore(0);
		sb.setUser(user);
		sb.setGame(game);
		scoreboardService.save(sb);
		List<ScoreBoard> sbs = new ArrayList<>();
		sbs.add(sb);
		game.setScoreboards(sbs);
		gameService.save(game);
		
		gameService.finishGame(game, user);
		
		Boolean res = true;
		for(Cell c : cells) {
			if(!(c.getTile() == null && c.getIsFlipped() == false)) {
				res = false;
			}
		}
		if(!(user.getTiles() == null && user.getProfile().getPlayedGames() > 0)) {
			res = false;
		}
		assertThat(res && game.getFinished()).isTrue();
	}
	
	@Test
	void shouldRestartGameSolitarie() {
		Game game = createGame("SOLITARIO");
		gameService.save(game);
		User user = createUser("manuel");
		gameService.initPlayerToGame(user.getUsername(), game);
		
		List<Cell> cellsT = cellService.getCells();
	    List<Cell> corners = cellsT.stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
	    List<Cell> tablero = cellsT.stream().filter(c -> !corners.contains(c)).collect(Collectors.toList());
		
	    tablero.get(0).setTile(game.getBag().get(0));
	    game.getBag().remove(0);
	    gameService.save(game);
	    corners.get(0).setIsFlipped(true);
	    gameService.save(game);
	    
		gameService.restartGame(game, user);
		
		Boolean res = true;
		for(Cell c : tablero) {
			if(!(c.getTile() == null)) {
				res = false;
			}
		}
		
		for(Cell c : corners) {
			if(c.getIsFlipped() == true) {
				res = false;
			}
		}
		assertThat(res).isTrue();
	}
	
	@Test
	void shouldRestartGameSurvival() {
		Game game = createGame("SURVIVAL");
		User user = createUser("manuel");
		gameService.initPlayerToGame(user.getUsername(), game);
		
		List<Cell> cellsT = cellService.getCells();
	    List<Cell> corners = cellsT.stream().filter(c -> c.getAdjacents().size()==3).collect(Collectors.toList());
	    List<Cell> tablero = cellsT.stream().filter(c -> !corners.contains(c)).collect(Collectors.toList());
		
	    tablero.get(0).setTile(game.getBag().get(0));
	    game.getBag().remove(0);
	    gameService.save(game);
	    corners.get(0).setIsBlocked(true);;
	    gameService.save(game);
	    
	    gameService.restartGame(game, user);
	    
	    Boolean res = true;
		for(Cell c : tablero) {
			if(!(c.getTile() == null)) {
				res = false;
			}
		}
		
		for(Cell c : corners) {
			if(c.getIsBlocked() == true) {
				res = false;
			}
		}
		assertThat(res).isTrue();
	}
	
	@Test
	void shouldIncrementTurn() {
		Game game = createGame("COMPETITIVE");
		game.setNumberCurrentPlayers(2);
		gameService.save(game);
		game.setTurn(1);
		gameService.save(game);
		gameService.incrementTurn(game);
		
		assertThat(game.getTurn()).isEqualTo(2);
	}
	
	@Test
	void  shouldPlayTile() throws AlreadyTileOnCell {
		Game game = createGame("SOLITARIO");
		User user = createUser("manuel");
		gameService.initSolitarieGame(game);
		profileService.initProfile(user);
		userService.saveUser(user);
		ScoreBoard sb = new ScoreBoard();
		sb.setOrden(1);
		sb.setScore(0);
		sb.setUser(user);
		sb.setGame(game);
		scoreboardService.save(sb);
		List<ScoreBoard> sbs = new ArrayList<>();
		sbs.add(sb);
		game.setScoreboards(sbs);
		gameService.save(game);
		
		gameService.save(game);
		gameService.stealToken(game, user);
		Integer tileId = user.getTiles().get(0).getId();
		Cell cell = game.getCells().stream().filter(c -> c.getTile() == null)
				.findFirst().get();
		gameService.playTile(cell.getId(), tileId, user, game.getId());
		
		assertThat(cell.getTile() != null).isTrue();
	}
	
	@Test
	void shouldGetGamesByUser() throws TooManyPlayers, NotThisTypeOfGame {
		Game game = createGame("COMPETITIVO");
		game.setNumberOfPlayers(2);
		gameService.save(game);
		Game game2 = createGame("COMPETITIVO");
		game2.setNumberOfPlayers(2);
		gameService.save(game2);
		User user = createUser("manuel");
		gameService.joinPlayerToGame(user.getUsername(), game);
		gameService.joinPlayerToGame(user.getUsername(), game2);
		List<Game> games = gameService.getGamesByUser(user);
		
		assertThat(games.size()).isEqualTo(2);
	}
}