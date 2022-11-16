package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {
	
	@Autowired
	protected GameService gameService;
	
	@Test
	@Transactional
	public void shouldInsertGame() {
		int found = this.gameService.getGames().size();
		
		Game game = new Game();
		game.setMode("survival");
		game.setFinished(true);
		game.setNumberOfPlayers(1);
		game.setDateOfCreation(LocalDate.now());
		
		this.gameService.save(game);
		assertThat(game.getId()).isNotNull();
		assertThat(this.gameService.getGames().size()).isEqualTo(found+1);
	}
	
	@Test
	void shouldFindGameById() {
		Game game = new Game();
		game.setMode("survival");
		game.setFinished(true);
		game.setNumberOfPlayers(1);
		game.setDateOfCreation(LocalDate.now());
		this.gameService.save(game);
		Integer id = game.getId();
		
		assertThat(this.gameService.getGameById(id)).isEqualTo(game);
	}
	
	@Test
	void shouldDeleteGameById() {
		int found = this.gameService.getGames().size();
		assertThat(found).isEqualTo(0);
		
		Game game = new Game();
		game.setMode("survival");
		game.setFinished(true);
		game.setNumberOfPlayers(1);
		game.setDateOfCreation(LocalDate.now());
		this.gameService.save(game);
		Integer id = game.getId();
		
		assertThat(this.gameService.getGames().size()).isEqualTo(1);

		this.gameService.deleteGameById(id);
		assertThat(this.gameService.getGames().size()).isEqualTo(0);
	}
}