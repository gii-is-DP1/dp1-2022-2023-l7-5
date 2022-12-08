package org.springframework.samples.petclinic.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	ProfileRepository repo;
	
	@Autowired
	AchievementService achievementServ;
	
	@Autowired
	UserService userServ;
	
	@Autowired ProfileService(ProfileRepository repo){
		this.repo= repo;
	}
	
	List<Profile> getProfiles(){
		
		return repo.findAll();
	}
	
	public Profile getProfileById(Integer id) {
		
		return repo.findById(id).get();
	}
	
	public void deleteProfileById(Integer id) {
		
		repo.deleteById(id);
	}
	
	public void save(Profile profile) {
		
		repo.save(profile);
	}
	
	public void initProfile(User u) {
		
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(u);
		p.setAchievements(new ArrayList<Achievement>());
		achievementServ.giveFirstAchieve(p);
		repo.save(p);
		u.setProfile(p);
		userServ.saveUser(u);
		
	}
	
	
}
