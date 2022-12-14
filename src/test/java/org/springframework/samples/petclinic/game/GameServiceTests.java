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
import org.springframework.samples.petclinic.game.exception.NotThisTypeOfGame;
import org.springframework.samples.petclinic.game.exception.TooManyPlayers;
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
	
	public User createUser(String username) {
		User user = new User();
		user.setUsername(username);
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
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
		this.gameService.save(game);
		return game;
	}
	
	@Test
	@Transactional
	public void shouldInsertGame() {
		int found = this.gameService.getGames().size();
		
		createGame("SURVIVAL");
		
		assertThat(this.gameService.getGames().size()).isEqualTo(found+1);
	}
	
	@Test
	void shouldFindGameById() {
		Game game = createGame("SURVIVAL");
		Integer id = game.getId();
		
		assertThat(this.gameService.getGameById(id)).isEqualTo(game);
	}
	
	@Test
	void shouldDeleteGameById() {
		Game game = createGame("SURVIVAL");
		Integer id = game.getId();
		
		assertThat(this.gameService.getGames().size()).isEqualTo(1);

		this.gameService.deleteGameById(id);
		assertThat(this.gameService.getGames().size()).isEqualTo(0);
	}
	
	@Test
	void shouldInitPlayerToGame() {
		User user = createUser("manuelEjemplo2");
		
		Game game = createGame("SURVIVAL");
		
		String username = user.getUsername();
		this.gameService.initPlayerToGame(username, game);
		
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
		this.gameService.save(game);
		this.gameService.initPlayerToGame(username1, game);
		try {
			this.gameService.joinPlayerToGame(username2, game);
		} catch (TooManyPlayers | NotThisTypeOfGame e) {
			e.printStackTrace();
		}
		assertThat(game.getNumberCurrentPlayers()).isEqualTo(2);
	}
	
	@Test
	void shouldInitGame() {
		Game game = createGame("SURVIVAL");
		this.gameService.initGame(game.getId());
		
		assertThat(this.gameService.getGames().size()>0);
	}
	
	@Test
	void shouldInitSolitarieGame() {
		Game game = createGame("SURVIVAL");
		this.tileService.createAllTiles();
		List<Tile> bag = this.tileService.getTiles();
    	game.setBag(bag);
		this.gameService.save(game);
		this.gameService.initSolitarieGame(game);

		assertThat(game.getBag().size()<72);
	}
	
	/*@Test
	void shouldPlayTile() {
		
		Game game = new Game();
		game.setMode("survival");
		game.setFinished(true);
		game.setNumberOfPlayers(4);
		game.setNumberCurrentPlayers(0);
		game.setDateOfCreation(LocalDate.now());
		List<Tile> bag = this.tileService.getTiles();
    	game.setBag(bag);
    	Integer tilesInBag = bag.size();
		this.gameService.save(game);
		
		User user = new User();
		user.setUsername("manuelEjemplo2");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		this.userService.saveUser(user);
		
		this.gameService.initSolitarieGame(game);
		this.gameService.initPlayerToGame(user.getUsername(), game);
		this.gameService.stealToken(game, user);
		Integer tileId = user.getTiles().get(0).getId();
		Integer cellId = game.getCells().stream().filter(cell -> cell.getTile()==null)
				.collect(Collectors.toList()).get(0).getId();
		this.gameService.playTile(cellId, tileId, user);
		
		assertThat(game.getBag().size() == tilesInBag - 1);
		
	}*/
}