package org.springframework.samples.petclinic.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	
	protected ProfileRepository repo;

	@Autowired
	protected AchievementService achievementService;

	@Autowired
	protected UserService userService;

	@Autowired
	private ProfileService(ProfileRepository repo) {
		this.repo = repo;
	}

	public List<Profile> getProfiles() {

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
		achievementService.giveFirstAchieve(p);
		repo.save(p);
		u.setProfile(p);
		userService.saveUser(u);

	}

	public Boolean hasAchievement(Achievement a, Profile p) {

		return p.getAchievements().contains(a);

	}


	public void updateGlobal() {

		Profile global = getProfileById(1);
		global.setMatches(repo.findAll().stream().filter(p -> p.getId() != 1).map(Profile::getMatches)
				.collect(Collectors.summingInt(Integer::intValue)));
		global.setSteals(repo.findAll().stream().filter(p -> p.getId() != 1).map(Profile::getSteals)
				.collect(Collectors.summingInt(Integer::intValue)));
		global.setPlayedGames(repo.findAll().stream().filter(p -> p.getId() != 1).map(Profile::getPlayedGames)
				.collect(Collectors.summingInt(Integer::intValue)));

	}

	public void deleteAchievement(Achievement a, Profile p) {

		p.getAchievements().remove(a);

	}
	
	public Profile getProfileByUsername(String username) {
		return this.repo.getProfileByUser(username);
	}

}
