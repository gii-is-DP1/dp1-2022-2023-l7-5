package org.springframework.samples.petclinic.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
	AchievementRepository repo;

	@Autowired
	ProfileService profileServ;

	@Autowired
	public AchievementService(AchievementRepository repo) {
		this.repo = repo;
	}

	public List<Achievement> getAchievements() {

		return repo.findAll();
	}

	public Achievement getAchievementById(Integer id) {

		return repo.findById(id).get();
	}

	public void deleteAchievementById(Integer id) {

		repo.deleteById(id);
	}

	public void save(Achievement achievement) {

		repo.save(achievement);
	}

	public void giveAchievement(Profile p, Achievement a) {

		p.getAchievements().add(a);
		profileServ.save(p);

	}
	
	public void createAchievements() {
		
		Achievement a = new Achievement();
		a.setBadgeImage("https://media.istockphoto.com/illustrations/happy-baby-bee-illustration-id92717135?k=6&m=92717135&s=170667a&w=0&h=rT8nF4gaMA1bjExYyX4jTlTERmNtuVhVihHE5bmavIc=");
		a.setName("Baby Bee");
		a.setDescription("Enter your first game");
		a.setThreshold(2.);
		repo.save(a);
		Achievement a2 = new Achievement();
		a2.setBadgeImage("abejorro.jpg");
		a2.setName("BumbleBee");
		a2.setDescription("Play 5 games");
		a2.setThreshold(2.);
		repo.save(a2);
		Achievement a3 = new Achievement();
		a3.setBadgeImage("shorturl.at/tDX08");
		a3.setName("Queen Bee");
		a3.setDescription("Play 10 games");
		a3.setThreshold(2.);
		repo.save(a3);
		Achievement a4 = new Achievement();
		a4.setBadgeImage("winner.jpg");
		a4.setName("Queen of the Hive");
		a4.setDescription("Win your first game");
		a4.setThreshold(2.);
		repo.save(a4);
		Achievement a5 = new Achievement();
		a5.setBadgeImage("ladron.jpg");
		a5.setName("Winnie The Pooh");
		a5.setDescription("Steal your first tile");
		a5.setThreshold(2.);
		repo.save(a5);
		
	}

	public void giveFirstAchieve(Profile p) {

		Achievement a = repo.findById(1).get();
		giveAchievement(p, a);

	}
	

	public void updateAchievements(Profile p) {

		if (p.getPlayedGames() >= 5) {

			Achievement a = repo.findById(2).get();
			giveAchievement(p, a);

		}

		if (p.getPlayedGames() >= 10) {

			Achievement a = repo.findById(3).get();
			giveAchievement(p, a);

		}

		if (p.getWins() >= 1) {

			Achievement a = repo.findById(4).get();
			giveAchievement(p, a);

		}
		if (p.getSteals() >= 1) {

			Achievement a = repo.findById(5).get();
			giveAchievement(p, a);

		}

	}

}