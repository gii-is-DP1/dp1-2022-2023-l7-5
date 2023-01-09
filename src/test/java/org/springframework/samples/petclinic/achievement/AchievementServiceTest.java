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
	
	public User createUser(String username) {
		
		User user = new User();
		user.setUsername(username);
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
		return user;
		
	}
	public Achievement createAchievement(String name) {
		
		Achievement ach = new Achievement();
		ach.setName(name);
		ach.setDescription("Description");
		ach.setBadgeImage("Manue");
		ach.setThreshold(1.);
		ach.setBlockedImage("Julian");
		this.serv.save(ach);
		return ach;
		
	}
	
	public Profile createProfile(User user) {
		
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		pServ.save(p);
		
		return p;
		
	}
	
	@Test
	@Transactional
	public void shouldInsertAchie() {
		
		int found = this.serv.getAchievements().size();
				
		Achievement ach = createAchievement("Shoot from the hip");
		assertThat(ach.getId()).isNotEqualTo(0);
		assertThat(this.serv.getAchievements().size()).isEqualTo(found+1);
		
	}
	
	@Test
	@Transactional
	void shouldFindAchievementWithCorrectId() {
		
		Achievement acho = createAchievement("Fire Steal");
		
		Achievement achos = this.serv.getAchievementById(acho.getId());
		assertThat(achos.getName()).isEqualTo("Fire Steal");

	}
	
	@Test
	@Transactional
	void shouldNotFindAchievementWithCorrectId() {
		
		
		Achievement acho = createAchievement("Jorge?");
		
		this.serv.save(acho);
		assertThat(this.serv.getAchievements().size()).isEqualTo(11);
		this.serv.deleteAchievementById(acho.getId());
		assertThat(this.serv.getAchievements().size()).isEqualTo(10);

	}
	
	@Test
	@Transactional
	void shouldGiveAchievement() {
		
		User user = createUser("Alejandro");
		Profile p = createProfile(user);
		Achievement acho = createAchievement("lol?");
		
		this.serv.save(acho);
		this.serv.giveAchievement(p, acho);
		assertThat(p.getAchievements().contains(acho));

	}
	
	@Test
	@Transactional
	void shouldUpdateAchievement() {
		
		User user = createUser("Marta");
		Profile p = createProfile(user);
		
		p.setPlayedGames(1);
		
		this.serv.updateAchievements(p);
		assertThat(p.getAchievements().size() == 1);
		
		p.setSteals(3);
		
		this.serv.updateAchievements(p);
		this.serv.updateGlobalAchievements();
		//Primer game, primer steal, max games y max steal
		assertThat(p.getAchievements().size() == 4);
		
	}
	
	@Test
	@Transactional
	void shouldGiveFirstAchievement() {
		
		User user = createUser("Pedro");
		Profile p = createProfile(user);
		
		
		this.serv.giveFirstAchieve(p);
		
		assertThat(p.getAchievements().size() == 1);

	}
	
	@Test
	@Transactional
	void shouldUpdateGlobalAchievements() {
		
		User user = createUser("Marcos");
		Profile p = createProfile(user);
		
		User user1 = createUser("Laura");
		Profile p1 = createProfile(user1);
		
		p.setWins(2);
		this.serv.updateAchievements(p);
		this.serv.updateAchievements(p1);
		this.serv.updateGlobalAchievements();
		assertThat(p.getAchievements().size() == 2 && p1.getAchievements().size()==0);
		
		p1.setWins(6);
		this.serv.updateAchievements(p);
		this.serv.updateAchievements(p1);
		this.serv.updateGlobalAchievements();
		assertThat(p.getAchievements().size() == 1 && p1.getAchievements().size()==2);
		
	}
	

}
