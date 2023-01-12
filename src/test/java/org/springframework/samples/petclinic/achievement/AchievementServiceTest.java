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
	protected AchievementService service;
	@Autowired
	ProfileService profileService;
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
		
		Achievement a = new Achievement();
		a.setName(name);
		a.setDescription("Description");
		a.setBadgeImage("Manue");
		a.setThreshold(1.);
		a.setBlockedImage("Julian");
		service.save(a);
		return a;
		
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
	@Transactional
	public void shouldInsertAchie() {
		
		int found = service.getAchievements().size();
				
		Achievement a = createAchievement("Shoot from the hip");
		assertThat(a.getId()).isNotEqualTo(0);
		assertThat(service.getAchievements().size()).isEqualTo(found+1);
		
	}
	
	@Test
	@Transactional
	void shouldFindAchievementWithCorrectId() {
		
		Achievement a = createAchievement("Fire Steal");
		
		Achievement a2 = service.getAchievementById(a.getId());
		assertThat(a2.getName()).isEqualTo("Fire Steal");

	}
	
	@Test
	@Transactional
	void shouldNotFindAchievementWithCorrectId() {
		
		
		Achievement a = createAchievement("Jorge?");
		
		service.save(a);
		assertThat(service.getAchievements().size()).isEqualTo(11);
		service.deleteAchievementById(a.getId());
		assertThat(service.getAchievements().size()).isEqualTo(10);

	}
	
	@Test
	@Transactional
	void shouldGiveAchievement() {
		
		User user = createUser("Alejandro");
		Profile p = createProfile(user);
		Achievement a = createAchievement("Achievement");
		
		service.save(a);
		service.giveAchievement(p, a);
		assertThat(p.getAchievements().contains(a)).isTrue();

	}
	
	@Test
	@Transactional
	void shouldUpdateAchievement() {
		
		User user = createUser("Marta");
		Profile p = createProfile(user);
		
		p.setPlayedGames(1);
		
		service.updateAchievements(p);
		assertThat(p.getAchievements().size() == 1);
		
		p.setSteals(3);
		
		service.updateAchievements(p);
		service.updateGlobalAchievements();
		//primer steal, max games y max steal
		assertThat(p.getAchievements().size() == 3).isTrue();
		
	}
	
	@Test
	@Transactional
	void shouldGiveFirstAchievement() {
		
		User user = createUser("Pedro");
		Profile p = createProfile(user);
		
		
		service.giveFirstAchieve(p);
		
		assertThat(p.getAchievements().size() == 1).isTrue();

	}
	
	@Test
	@Transactional
	void shouldUpdateGlobalAchievements() {
		
		User user = createUser("Marcos");
		Profile p = createProfile(user);
		
		User user1 = createUser("Laura");
		Profile p1 = createProfile(user1);
		
		p.setWins(2);
		p.setSteals(2);
		service.updateAchievements(p);
		service.updateAchievements(p1);
		service.updateGlobalAchievements();
		assertThat(p.getAchievements().size()).isEqualTo(4);
		
		p1.setWins(6);
		service.updateAchievements(p);
		service.updateAchievements(p1);
		service.updateGlobalAchievements();
		assertThat(p.getAchievements().size() == 3 && p1.getAchievements().size()==2).isTrue();
		
	}
	

}
