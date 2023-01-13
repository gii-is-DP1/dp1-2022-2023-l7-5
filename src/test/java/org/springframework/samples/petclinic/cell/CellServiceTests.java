package org.springframework.samples.petclinic.cell;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.cell.exception.AlreadyTileOnCell;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.tile.Tile;
import org.springframework.samples.petclinic.tile.TileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CellServiceTests {
	
	@Autowired
	protected CellService cellService;
	
	@Autowired
	protected TileService tileService;
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected GameService gameService;

	@Autowired
	protected ProfileService profileService;
	
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
		gameService.save(game);
		return game;
	}
	
	public Profile createProfile(User user) {
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		profileService.save(p);
		return p;
	}
	
	@Test 
	public void shouldGetCellById() {
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(22);
		cellService.save(cell);
		assertThat(cellService.getCellById(cell.getId()).getPosition()).isEqualTo(22);
	}
	
	@Test
	public void shouldInsertCell() {
		int found = cellService.getCells().size();
		
		Cell cell = new Cell();
		cell.setId(1);
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		
		cellService.save(cell);
		assertThat(cell.getId()).isNotEqualTo(0);
		assertThat(cellService.getCells().size()).isEqualTo(found+1);
	}
	
	@Test
	public void shouldDeleteCell() {
		int found = cellService.getCells().size();
		
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		
		cellService.save(cell);
		assertThat(cell.getId()).isNotEqualTo(0);
		assertThat(cellService.getCells().size()).isEqualTo(found+1);
		cellService.deleteCellById(cell.getId());
		assertThat(cellService.getCells().size()).isEqualTo(found);
	}
	
	@Test
	public void shouldPutTileOnCell() throws AlreadyTileOnCell {
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		cellService.save(cell);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		tileService.save(tile);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		assertThat(cell.getTile().getId().equals(tile.getId())).isTrue();
	}
	
	@Test
	public void shouldNotPutTileOnCell() throws AlreadyTileOnCell {
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		cellService.save(cell);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		tileService.save(tile);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		assertThrows(AlreadyTileOnCell.class, () -> cellService.putTileOnCell(cell.getId(), tile.getId()));
	}
	
	@Test
	public void shouldDetectMatchAndChain() throws AlreadyTileOnCell {
		Game game = createGame("SOLO");
		User user = createUser("test");
		profileService.initProfile(user);
		gameService.initPlayerToGame(user.getUsername(), game);
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		Cell cell2 = new Cell();
		cell2.setIsBlocked(false);
		cell2.setIsFlipped(false);
		cell2.setPosition(2);
		Cell cell3 = new Cell();
		cell3.setIsBlocked(false);
		cell3.setIsFlipped(false);
		cell3.setPosition(2);	
		cellService.save(cell);
		cellService.save(cell2);
		cellService.save(cell3);
		List<Cell> adjCell1 = new ArrayList<>();
		adjCell1.add(cell2);
		adjCell1.add(cell3);
		cell.setAdjacents(adjCell1);
		cellService.save(cell);
		List<Cell> adjCell2 = new ArrayList<>();
		adjCell2.add(cell);
		adjCell2.add(cell3);
		cell2.setAdjacents(adjCell2);
		cellService.save(cell2);
		List<Cell> adjCell3 = new ArrayList<>();
		adjCell3.add(cell);
		adjCell3.add(cell2);
		cell3.setAdjacents(adjCell3);
		cellService.save(cell3);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		Tile tile2 = new Tile();
		tile2.setFilledSide("red");
		tile2.setStartingSide("red");
		tile2.setFilledSideColor("red");
		tile2.setStartingSideColor("red");
		Tile tile3 = new Tile();
		tile3.setFilledSide("red");
		tile3.setStartingSide("red");
		tile3.setFilledSideColor("red");
		tile3.setStartingSideColor("red");
		tileService.save(tile);
		tileService.save(tile2);
		tileService.save(tile3);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		cellService.putTileOnCell(cell2.getId(), tile2.getId());
		cellService.putTileOnCell(cell3.getId(), tile3.getId());
		Set<Cell> match = cellService.detectMatch(cell.getId(), user, game);
		assertThat(match.size()==3).isTrue();
		assertThat(match.stream().allMatch(c -> c.getIsFlipped() == false)).isTrue();
		assertThat(match.stream().allMatch(c -> c.getTile() == null)).isTrue();

	}
	
	@Test
	public void shouldResolveMatchAndNoChain() throws AlreadyTileOnCell {
		Game game = createGame("SOLO");
		User user = createUser("test");
		profileService.initProfile(user);
		gameService.initPlayerToGame(user.getUsername(), game);
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		Cell cell2 = new Cell();
		cell2.setIsBlocked(false);
		cell2.setIsFlipped(false);
		cell2.setPosition(2);
		Cell cell3 = new Cell();
		cell3.setIsBlocked(false);
		cell3.setIsFlipped(false);
		cell3.setPosition(2);	
		cellService.save(cell);
		cellService.save(cell2);
		cellService.save(cell3);
		List<Cell> adjCell1 = new ArrayList<>();
		adjCell1.add(cell2);
		adjCell1.add(cell3);
		cell.setAdjacents(adjCell1);
		cellService.save(cell);
		List<Cell> adjCell2 = new ArrayList<>();
		adjCell2.add(cell);
		adjCell2.add(cell3);
		cell2.setAdjacents(adjCell2);
		cellService.save(cell2);
		List<Cell> adjCell3 = new ArrayList<>();
		adjCell3.add(cell);
		adjCell3.add(cell2);
		cell3.setAdjacents(adjCell3);
		cellService.save(cell3);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		Tile tile2 = new Tile();
		tile2.setFilledSide("red");
		tile2.setStartingSide("red");
		tile2.setFilledSideColor("red");
		tile2.setStartingSideColor("red");
		Tile tile3 = new Tile();
		tile3.setFilledSide("red");
		tile3.setStartingSide("red");
		tile3.setFilledSideColor("blue");
		tile3.setStartingSideColor("red");
		tileService.save(tile);
		tileService.save(tile2);
		tileService.save(tile3);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		cellService.putTileOnCell(cell2.getId(), tile2.getId());
		cellService.putTileOnCell(cell3.getId(), tile3.getId());
		Set<Cell> match = cellService.detectMatch(cell.getId(), user, game);
		assertThat(match.size()==3).isTrue();
		assertThat(match.stream().allMatch(c -> c.getIsFlipped() == true)).isTrue();
		assertThat(match.stream().allMatch(c -> c.getTile() == null)).isFalse();
	}
	
	@Test
	public void shouldNoClusterOnSurvival() throws AlreadyTileOnCell {
		Game game = createGame("SURVIVAL");
		User user = createUser("test");
		profileService.initProfile(user);
		gameService.initPlayerToGame(user.getUsername(), game);
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		Cell cell2 = new Cell();
		cell2.setIsBlocked(false);
		cell2.setIsFlipped(false);
		cell2.setPosition(2);
		Cell cell3 = new Cell();
		cell3.setIsBlocked(false);
		cell3.setIsFlipped(false);
		cell3.setPosition(2);	
		cellService.save(cell);
		cellService.save(cell2);
		cellService.save(cell3);
		List<Cell> adjCell1 = new ArrayList<>();
		adjCell1.add(cell2);
		adjCell1.add(cell3);
		cell.setAdjacents(adjCell1);
		cellService.save(cell);
		List<Cell> adjCell2 = new ArrayList<>();
		adjCell2.add(cell);
		adjCell2.add(cell3);
		cell2.setAdjacents(adjCell2);
		cellService.save(cell2);
		List<Cell> adjCell3 = new ArrayList<>();
		adjCell3.add(cell);
		adjCell3.add(cell2);
		cell3.setAdjacents(adjCell3);
		cellService.save(cell3);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		Tile tile2 = new Tile();
		tile2.setFilledSide("red");
		tile2.setStartingSide("red");
		tile2.setFilledSideColor("red");
		tile2.setStartingSideColor("red");
		Tile tile3 = new Tile();
		tile3.setFilledSide("red");
		tile3.setStartingSide("red");
		tile3.setFilledSideColor("blue");
		tile3.setStartingSideColor("red");
		tileService.save(tile);
		tileService.save(tile2);
		tileService.save(tile3);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		cellService.putTileOnCell(cell2.getId(), tile2.getId());
		cellService.putTileOnCell(cell3.getId(), tile3.getId());
		Set<Cell> match = cellService.detectMatch(cell.getId(), user, game);
		assertThat(match.size()==3).isTrue();
		assertThat(match.stream().allMatch(c -> c.getIsFlipped() == false)).isTrue();
		assertThat(match.stream().allMatch(c -> c.getTile() != null)).isTrue();
	}
	
	@Test
	public void shouldBlockCells() throws AlreadyTileOnCell {
		Game game = createGame("SURVIVAL");
		User user = createUser("test");
		profileService.initProfile(user);
		gameService.initPlayerToGame(user.getUsername(), game);
		Cell cell = new Cell();
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		Cell cell1 = new Cell();
		cell1.setIsBlocked(false);
		cell1.setIsFlipped(false);
		cell1.setPosition(2);
		Cell cell2 = new Cell();
		cell2.setIsBlocked(false);
		cell2.setIsFlipped(false);
		cell2.setPosition(2);
		Cell cell3 = new Cell();
		cell3.setIsBlocked(false);
		cell3.setIsFlipped(false);
		cell3.setPosition(2);	
		cellService.save(cell);
		cellService.save(cell1);
		cellService.save(cell2);
		cellService.save(cell3);
		List<Cell> adjCell = new ArrayList<>();
		adjCell.add(cell1);
		adjCell.add(cell2);
		adjCell.add(cell3);
		cell.setAdjacents(adjCell);
		cellService.save(cell);
		List<Cell> adjCell1 = new ArrayList<>();
		adjCell1.add(cell);
		adjCell1.add(cell2);
		cell1.setAdjacents(adjCell1);
		cellService.save(cell1);
		List<Cell> adjCell2 = new ArrayList<>();
		adjCell2.add(cell);
		adjCell2.add(cell1);
		adjCell2.add(cell3);
		cell2.setAdjacents(adjCell2);
		cellService.save(cell2);
		List<Cell> adjCell3 = new ArrayList<>();
		adjCell3.add(cell);
		adjCell3.add(cell2);
		cell3.setAdjacents(adjCell3);
		cellService.save(cell3);
		Tile tile = new Tile();
		tile.setFilledSide("red");
		tile.setStartingSide("red");
		tile.setFilledSideColor("red");
		tile.setStartingSideColor("red");
		Tile tile2 = new Tile();
		tile2.setFilledSide("red");
		tile2.setStartingSide("red");
		tile2.setFilledSideColor("red");
		tile2.setStartingSideColor("red");
		Tile tile3 = new Tile();
		tile3.setFilledSide("red");
		tile3.setStartingSide("red");
		tile3.setFilledSideColor("blue");
		tile3.setStartingSideColor("red");
		tileService.save(tile);
		tileService.save(tile2);
		tileService.save(tile3);
		cellService.putTileOnCell(cell.getId(), tile.getId());
		cellService.putTileOnCell(cell1.getId(), tile2.getId());
		cellService.putTileOnCell(cell3.getId(), tile3.getId());
		Set<Cell> match = cellService.detectMatch(cell.getId(), user, game);
		assertThat(cell.getAdjacents().stream().allMatch(c -> c.getIsBlocked() == true)).isTrue();
	}
}