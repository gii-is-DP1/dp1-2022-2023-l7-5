package org.springframework.samples.petclinic.cell;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CellServiceTests {
	
	@Autowired
	protected CellService cellService;
	
	@Transactional
	@Test
	public void shouldInsertCell() {
		int found = this.cellService.getCells().size();
		
		Cell cell = new Cell();
		cell.setId(1);
		cell.setIsBlocked(false);
		cell.setIsFlipped(false);
		cell.setPosition(1);
		
		this.cellService.save(cell);
		assertThat(cell.getId()).isNotEqualTo(0);
		assertThat(this.cellService.getCells().size()).isEqualTo(found+1);
		
	}
}
