package org.springframework.samples.petclinic.tile;

import static org.assertj.core.api.Assertions.assertThat;





import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class TileServiceTest {

	@Autowired
	protected TileService tileService;
	
	@Transactional
	@Test
	public void shouldInsertTile() {
		int found = tileService.getTiles().size();
		
		Tile tile = new Tile();
		tile.setStartingSide("blue");;
		tile.setFilledSide("green");
		tile.setStartingSideColor("blue");;
		tile.setFilledSideColor("green");
		tileService.save(tile);
		assertThat(tile.getId()).isNotEqualTo(0);
		assertThat(tileService.getTiles().size()).isEqualTo(found+1);
	}
	
	@Test
	public void shouldFindTileById() {
		
		Tile tile = new Tile();
		tile.setStartingSide("blue");;
		tile.setFilledSide("green");
		tile.setStartingSide("blue");;
		tile.setFilledSide("green");
		tile.setStartingSideColor("blue");;
		tile.setFilledSideColor("green");
		tileService.save(tile);
		Integer id = tile.getId();
		
		assertThat(tileService.getTileById(id)).isEqualTo(tile);
	}
	
	
	@Test
	public void shouldDeleteTileById() {
		int found = tileService.getTiles().size();
		
		Tile tile = new Tile();
		tile.setStartingSide("blue");;
		tile.setFilledSide("green");
		tile.setStartingSide("blue");;
		tile.setFilledSide("green");
		tile.setStartingSideColor("blue");;
		tile.setFilledSideColor("green");
		tileService.save(tile);
		Integer id = tile.getId();
		
		assertThat(tileService.getTiles().size()).isEqualTo(found + 1);
		
		tileService.deleteTileById(id);
		assertThat(tileService.getTiles().size()).isEqualTo(found);
				
	}
	
	@Test
	public void shouldCreateAllTiles() {
		int found = tileService.getTiles().size();
		tileService.createAllTiles();
		assertThat(tileService.getTiles().size()).isEqualTo(found+72);
	}
}
