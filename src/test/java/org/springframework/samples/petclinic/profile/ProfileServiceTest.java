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
	protected ProfileService serv;
	
	@Autowired
	protected UserService serv2;
	
	@Autowired
	protected AchievementService serv3;
	
	public User createUser(String username) {
		
		User user = new User();
		user.setUsername(username);
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		serv2.saveUser(user);
		return user;
		
	}
	public Achievement createAchievement(String name) {
		
		Achievement ach = new Achievement();
		ach.setName(name);
		ach.setDescription("Description");
		ach.setBadgeImage("Manue");
		ach.setThreshold(1.);
		ach.setBlockedImage("Julian");
		serv3.save(ach);
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
		serv.save(p);
		
		return p;
		
	}
	
	@Test
	@Transactional
	public void shouldInsertProfile() {
		int found = this.serv.getProfiles().size();
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		this.serv.save(p);
		assertThat(p.getId()).isNotEqualTo(0);
		assertThat(this.serv.getProfiles().size()).isEqualTo(found+1);
	}
	
	@Test
	@Transactional
	public void shouldFindProfileById() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Integer id = p.getId();
		
		
		assertThat(this.serv.getProfileById(id)).isEqualTo(p);
		
	}
	
	@Transactional
	@Test
	public void shouldDeleteProfileById() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Integer id = p.getId();
		
		assertThat(this.serv.getProfiles().size()).isEqualTo(2);
		this.serv.deleteProfileById(id);;
		assertThat(this.serv.getProfiles().size()).isEqualTo(1);
		
		
	}
	
	@Test
	@Transactional
	public void shouldInitProfile() {
		
		User u = createUser("Jorge");		
		
		this.serv.initProfile(u);
		
		
		assertThat(u.getProfile()!= null);
	}
	
	@Test
	@Transactional
	public void shouldHaveAchievement() {
		
		
		Achievement acho = createAchievement("Liada Padre");
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		serv3.giveAchievement(p, acho);
		
		assertThat(this.serv.hasAchievement(acho, p) && p.getAchievements().contains(acho));
	}
	
	@Test
	@Transactional
	void shouldDeleteAchievement() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		
		Achievement a = createAchievement("acho");
		
		serv3.giveAchievement(p, a);
		serv.deleteAchievement(a, p);
		assertThat(p.getAchievements().size()==0);
		
	}
	
	@Test
	@Transactional
	void shouldUpdateGlobal() {
		
		User user = createUser("Jorge");
		Profile p = createProfile(user);
		User user1 = createUser("Adrian");
		Profile p1 = createProfile(user1);
		
		Profile global = serv.getProfileById(1);
		
		p.setMatches(3);
		p1.setMatches(5);
		p.setSteals(2);
		p1.setSteals(156);
		serv.updateGlobal();
		
		assertThat(global.getMatches()==8 && global.getSteals()==158);
		
	}
	
}