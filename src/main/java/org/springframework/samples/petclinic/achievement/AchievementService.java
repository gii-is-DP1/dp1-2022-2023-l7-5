package org.springframework.samples.petclinic.achievement;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

		if (p.getWins() >= 1) {

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
		
	}
	
	public void updateGlobalAchievements() {
		
		Achievement winner = repo.findById(6).get();
		Comparator<Profile> Cwins = Comparator.comparing(Profile::getWins);
		List<Profile> wins = profileServ.getProfiles().stream().sorted(Cwins.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxWin = wins.get(0);
		for (Profile p : wins) {
			
			if(profileServ.hasAchievement(winner, p)) profileServ.deleteAchievement(winner, p);
			
		}
		if (maxWin.getWins()>0) giveAchievement(maxWin, winner);
		
		Achievement thief = repo.findById(8).get();
		Comparator<Profile> Csteals = Comparator.comparing(Profile::getSteals);
		List<Profile> steals = profileServ.getProfiles().stream().sorted(Csteals.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxSteal = steals.get(0);
		for (Profile p : steals) {
			
			if(profileServ.hasAchievement(thief, p)) profileServ.deleteAchievement(thief, p);
			
		}
		if (maxSteal.getSteals()>0) giveAchievement(maxSteal, thief);
		
		Achievement old = repo.findById(9).get();
		Comparator<Profile> CplayedGames = Comparator.comparing(Profile::getPlayedGames);
		List<Profile> playedGames = profileServ.getProfiles().stream().sorted(CplayedGames.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxGames = playedGames.get(0);
		for (Profile p : playedGames) {
			
			if(profileServ.hasAchievement(old, p)) profileServ.deleteAchievement(old, p);
			
		}
		if (maxGames.getPlayedGames()>0) giveAchievement(maxGames, old);
		
		Achievement matcher = repo.findById(10).get();
		Comparator<Profile> Cmatches = Comparator.comparing(Profile::getMatches);
		List<Profile> matches = profileServ.getProfiles().stream().sorted(Cmatches.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxMatch = matches.get(0);
		for (Profile p : matches) {
			
			if(profileServ.hasAchievement(matcher, p)) profileServ.deleteAchievement(matcher, p);
			
		}
		if (maxMatch.getMatches()>0) giveAchievement(maxMatch, matcher);
		
	}

}