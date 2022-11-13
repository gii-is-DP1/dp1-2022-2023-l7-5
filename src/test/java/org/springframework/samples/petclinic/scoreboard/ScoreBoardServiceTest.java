package org.springframework.samples.petclinic.scoreboard;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ScoreBoardServiceTest {

	@Autowired
	protected ScoreBoardService scoreboardService;
	
	@Transactional
	@Test
	public void shouldInsertScoreBoard() {
		int found = this.scoreboardService.getScoreBoard().size();
		
		ScoreBoard scoreboard = new ScoreBoard();
		scoreboard.setOrden(1);
		scoreboard.setScore(100);
		
		this.scoreboardService.save(scoreboard);
		assertThat(scoreboard.getId()).isNotEqualTo(0);
		assertThat(this.scoreboardService.getScoreBoard().size()).isEqualTo(found+1);
	}
}
