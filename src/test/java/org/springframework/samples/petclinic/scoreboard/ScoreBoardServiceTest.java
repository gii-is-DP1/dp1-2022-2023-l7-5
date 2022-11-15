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
	protected ScoreBoardService serv;
	
	@Transactional
	@Test
	public void shouldInsertSB() {
		int found = this.serv.getScoreBoards().size();
		
		ScoreBoard s = new ScoreBoard();
		s.setOrden(3);
		s.setScore(2301);
		
		this.serv.save(s);
		assertThat(s.getId()).isNotEqualTo(0);
		assertThat(this.serv.getScoreBoards().size()).isEqualTo(found+1);
	}
	
	/*@Transactional
	@Test
	public void shouldFindProfileById() {
		
		ScoreBoard s = new ScoreBoard();
		s.setOrden(3);
		s.setScore(2301);
		Integer id = s.getId();
		
		assertThat(this.serv.getScoreBoardById(id)).isEqualTo(s);
		
	}
	
	@Transactional
	@Test
	public void shouldDeleteProfileById() {
		
		ScoreBoard s = new ScoreBoard();
		s.setOrden(3);
		s.setScore(2301);
		Integer id = s.getId();
		
		assertThat(this.serv.getScoreBoards().size()).isEqualTo(1);
		this.serv.deleteScoreBoardById(id);;
		assertThat(this.serv.getScoreBoards().size()).isEqualTo(0);
		
		
	}*/
	
}