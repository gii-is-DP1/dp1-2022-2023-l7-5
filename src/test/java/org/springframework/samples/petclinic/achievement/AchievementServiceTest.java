package org.springframework.samples.petclinic.achievement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementServiceTest {
	
	@Autowired
	protected AchievementService serv;
	@Autowired
	ProfileService pServ;
	@Autowired
	UserService userService;
	
	@Test
	@Transactional
	public void shouldInsertAchie() {
		
		int found = this.serv.getAchievements().size();
		
		Achievement ach = new Achievement();
		ach.setName("Shoot from the hip");
		ach.setDescription("Kill an enemy which is out of your vision");
		ach.setBadgeImage("Manue");
		ach.setThreshold(1.);
		ach.setBlockedImage("Julian");
		
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
		acho.setBlockedImage("No");
		
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
		acho.setBlockedImage("Jej");
		
		this.serv.save(acho);
		assertThat(this.serv.getAchievements().size()).isEqualTo(6);
		this.serv.deleteAchievementById(acho.getId());
		assertThat(this.serv.getAchievements().size()).isEqualTo(5);

	}
	
	@Test
	@Transactional
	void shouldGiveAchievement() {
		
		User user = new User();
		user.setUsername("manuelEjemplo");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		pServ.save(p);
		Achievement acho = new Achievement();
		acho.setName("Manolin Enfadao");
		acho.setDescription("Manolin te dijo acho enfadado porque le miraste a Elenita");
		acho.setBadgeImage("Ale");
		acho.setThreshold(8.);
		acho.setBlockedImage("Pep");
		
		this.serv.save(acho);
		this.serv.giveAchievement(p, acho);
		assertThat(p.getAchievements().contains(acho));

	}
	
	@Test
	@Transactional
	void shouldUpdateAchievement() {
		
		User user = new User();
		user.setUsername("manuelEjemplo");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		pServ.save(p);
		
		p.setPlayedGames(1);
		
		this.serv.updateAchievements(p);
		assertThat(p.getAchievements().size() == 1);

	}
	
	@Test
	@Transactional
	void shouldGiveFirstAchievement() {
		
		User user = new User();
		user.setUsername("manuelEjemplo");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		pServ.save(p);
		
		
		this.serv.giveFirstAchieve(p);
		
		assertThat(p.getAchievements().size() == 1);

	}
	

}
