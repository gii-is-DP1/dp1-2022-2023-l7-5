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
	
	@Test
	public void shouldInsertProfile() {
		int found = this.serv.getProfiles().size();
		
		Profile p = new Profile();
		p.setPlayedGames(200);
		p.setMatches(2301);
		p.setWins(0);
		p.setSteals(543);
		
		User u = new User();
		u.setEmail("pepe@gmail.com");
		u.setUsername("Juan");
		u.setPassword("p");
		u.setEnabled(true);
		serv2.saveUser(u);
		
		p.setUser(u);
		
		this.serv.save(p);
		assertThat(p.getId()).isNotEqualTo(0);
		assertThat(this.serv.getProfiles().size()).isEqualTo(found+1);
	}
	
	@Test
	public void shouldFindProfileById() {
		
		Profile p = new Profile();
		p.setPlayedGames(200);
		p.setMatches(2301);
		p.setWins(0);
		p.setSteals(543);
		
		User u = new User();
		u.setEmail("pepe@gmail.com");
		u.setUsername("Juan");
		u.setPassword("p");
		u.setEnabled(true);
		serv2.saveUser(u);
		p.setUser(u);
		serv.save(p);
		
		Integer id = p.getId();
		
		
		assertThat(this.serv.getProfileById(id)).isEqualTo(p);
		
	}
	
	@Transactional
	@Test
	public void shouldDeleteProfileById() {
		
		Profile p = new Profile();
		p.setPlayedGames(200);
		p.setMatches(2301);
		p.setWins(0);
		p.setSteals(543);
		
		User u = new User();
		u.setEmail("pepe@gmail.com");
		u.setUsername("Juan");
		u.setPassword("p");
		u.setEnabled(true);
		serv2.saveUser(u);
		p.setUser(u);
		serv.save(p);
		
		Integer id = p.getId();
		
		assertThat(this.serv.getProfiles().size()).isEqualTo(1);
		this.serv.deleteProfileById(id);;
		assertThat(this.serv.getProfiles().size()).isEqualTo(0);
		
		
	}
	
	@Test
	public void shouldInitProfile() {
		
		User u = new User();
		u.setEmail("pepe@gmail.com");
		u.setUsername("Juan");
		u.setPassword("p");
		u.setEnabled(true);
		serv2.saveUser(u);
		
		
		this.serv.initProfile(u);
		
		
		assertThat(u.getProfile()!= null);
	}
	
	@Test
	public void shouldHaveAchievement() {
		
		
		Achievement acho = new Achievement();
		acho.setName("Manolin Enfadao");
		acho.setDescription("Manolin te dijo acho enfadado porque le miraste a Elenita");
		acho.setBadgeImage("Ale");
		acho.setThreshold(8.);
		acho.setBlockedImage("Pep");
		
		Profile p = new Profile();
		p.setPlayedGames(200);
		p.setMatches(2301);
		p.setWins(0);
		p.setSteals(543);
		p.setAchievements(new ArrayList<Achievement>());
		User u = new User();
		u.setEmail("pepe@gmail.com");
		u.setUsername("Juan");
		u.setPassword("p");
		u.setEnabled(true);
		serv2.saveUser(u);
		p.setUser(u);
		serv.save(p);
		
		serv3.giveAchievement(p, acho);
		
		assertThat(this.serv.hasAchievement(acho, p));
	}
	
}