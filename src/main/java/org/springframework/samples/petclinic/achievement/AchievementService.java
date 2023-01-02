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


	public void giveFirstAchieve(Profile p) {

		Achievement a = repo.findById(1).get();
		giveAchievement(p, a);

	}

	public void updateAchievements(Profile p) {

		if (p.getPlayedGames() == 5) {

			Achievement a = repo.findById(2).get();
			if (profileServ.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}

		if (p.getPlayedGames() == 10) {

			Achievement a = repo.findById(3).get();
			if (profileServ.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}

		}

		if (p.getWins() == 1) {

			Achievement a = repo.findById(4).get();
			if (profileServ.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}

		}
		if (p.getSteals() >= 1) {

			Achievement a = repo.findById(5).get();
			if (profileServ.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}
		
		if (p.getMatches() >= 1) {

			Achievement a = repo.findById(7).get();
			if (profileServ.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}
		
		if (profileServ.isMaxWinner(p)) {

			Achievement a = repo.findById(6).get();
			if (profileServ.hasAchievement(a, p) == false) {
				
				giveAchievement(p, a);

			}
		}
		
		if (profileServ.isMaxWinner(p) == false && profileServ.hasAchievement(repo.findById(6).get(), p)) {

			profileServ.deleteAchievement(repo.findById(6).get(), p);
			
			
		}
		
		if (profileServ.isMaxThief(p)) {

			Achievement a = repo.findById(8).get();
			if (profileServ.hasAchievement(a, p) == false) {
				
				giveAchievement(p, a);

			}
		}
		
		if (profileServ.isMaxThief(p) == false && profileServ.hasAchievement(repo.findById(8).get(), p)) {

			profileServ.deleteAchievement(repo.findById(8).get(), p);
			
			
		}
		
		if (profileServ.isMaxOlder(p)) {

			Achievement a = repo.findById(9).get();
			if (profileServ.hasAchievement(a, p) == false) {
				
				giveAchievement(p, a);

			}
		}
		
		if (profileServ.isMaxOlder(p) == false && profileServ.hasAchievement(repo.findById(9).get(), p)) {

			profileServ.deleteAchievement(repo.findById(9).get(), p);
			
			
		}
		
		if (profileServ.isMaxMatcher(p)) {

			Achievement a = repo.findById(10).get();
			if (profileServ.hasAchievement(a, p) == false) {
				
				giveAchievement(p, a);

			}
		}
		
		if (profileServ.isMaxMatcher(p) == false && profileServ.hasAchievement(repo.findById(10).get(), p)) {

			profileServ.deleteAchievement(repo.findById(10).get(), p);
			
			
		}
		
	}

}