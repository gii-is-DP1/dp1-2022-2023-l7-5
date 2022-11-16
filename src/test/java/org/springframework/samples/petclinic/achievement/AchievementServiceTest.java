package org.springframework.samples.petclinic.achievement;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementServiceTest {
	
	@Autowired
	protected AchievementService serv;
	
	@Test
	@Transactional
	public void shouldInsertAchie() {
		
		int found = this.serv.getAchievements().size();
		
		Achievement ach = new Achievement();
		ach.setName("Shoot from the hip");
		ach.setDescription("Kill an enemy which is out of your vision");
		ach.setBadgeImage("Manue");
		ach.setThreshold(1.);
		
		this.serv.save(ach);
		assertThat(ach.getId()).isNotEqualTo(0);
		assertThat(this.serv.getAchievements().size()).isEqualTo(found+1);
		
	}
	
	@Test
	@Transactional
	void shouldFindAchievementWithCorrectId() {
		
		Achievement acho = new Achievement();
		acho.setName("Fire Steal");
		acho.setDescription("Kill the fire giant while the enemy team have reduced its HP by 75%");
		acho.setBadgeImage("Jorge");
		acho.setThreshold(75.);
		
		this.serv.save(acho);
		
		Achievement achos = this.serv.getAchievementById(acho.getId());
		assertThat(achos.getName()).isEqualTo("Fire Steal");

	}
	
	@Test
	@Transactional
	void shouldNotFindAchievementWithCorrectId() {
		
		
		Achievement acho = new Achievement();
		acho.setName("Manolin Enfadao");
		acho.setDescription("Manolin te dijo acho enfadado porque le miraste a Elenita");
		acho.setBadgeImage("Ale");
		acho.setThreshold(8.);
		
		this.serv.save(acho);
		assertThat(this.serv.getAchievements().size()).isEqualTo(1);
		this.serv.deleteAchievementById(acho.getId());
		assertThat(this.serv.getAchievements().size()).isEqualTo(0);

	}
	

}
