package org.springframework.samples.petclinic.profile;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProfileServiceTest {
	
	@Autowired
	protected ProfileService service;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected AchievementService achievementService;
	
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
		achievementService.save(ach);
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
		service.save(p);
		
		return p;
		
	}
	
	@Test
	@Transactional
	public void shouldInsertProfile() {
		int found = service.getProfiles().size();
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		service.save(p);
		assertThat(p.getId()).isNotEqualTo(0);
		assertThat(service.getProfiles().size()).isEqualTo(found+1);
	}
	
	@Test
	@Transactional
	public void shouldFindProfileById() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Integer id = p.getId();
		
		
		assertThat(service.getProfileById(id)).isEqualTo(p);
		
	}
	
	@Transactional
	@Test
	public void shouldDeleteProfileById() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Integer id = p.getId();
		
		assertThat(service.getProfiles().size()).isEqualTo(3);
		service.deleteProfileById(id);;
		assertThat(service.getProfiles().size()).isEqualTo(2);
		
		
	}
	
	@Test
	@Transactional
	public void shouldInitProfile() {
		
		User u = createUser("Jorge");		
		
		service.initProfile(u);
		
		
		assertThat(u.getProfile().getPlayedGames()!= null).isTrue();
	}
	
	@Test
	@Transactional
	public void shouldHaveAchievement() {
		
		
		Achievement acho = createAchievement("Liada Padre");
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		achievementService.giveAchievement(p, acho);
		
		assertThat(service.hasAchievement(acho, p) && p.getAchievements().contains(acho)).isTrue();
	}
	
	@Test
	@Transactional
	void shouldDeleteAchievement() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Achievement a = createAchievement("acho");
		
		achievementService.giveAchievement(p, a);
		service.deleteAchievement(a, p);
		assertThat(p.getAchievements().size()==0).isTrue();
		
	}
	
	@Test
	@Transactional
	void shouldUpdateGlobal() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		User user1 = createUser("Adrian");
		Profile p1 = createProfile(user1);
		
		Profile global = service.getProfileById(1);
		
		p.setMatches(3);
		p1.setMatches(5);
		p.setSteals(2);
		p1.setSteals(156);
		service.updateGlobal();
		
		assertThat(global.getMatches()==8 && global.getSteals()==158).isTrue();
		
	}
	
}