package org.springframework.samples.petclinic.achievement;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
	
	protected AchievementRepository repo;

	@Autowired
	protected ProfileService profileService;

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

	public Achievement save(Achievement achievement) {

		return this.repo.save(achievement);
	}

	public void giveAchievement(Profile p, Achievement a) {

		p.getAchievements().add(a);
		profileService.save(p);

	}


	public void giveFirstAchieve(Profile p) {

		Achievement a = repo.findById(1).get();
		giveAchievement(p, a);

	}

	public void updateAchievements(Profile p) {

		if (p.getPlayedGames() == 5) {

			Achievement a = repo.findById(2).get();
			if (profileService.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}

		if (p.getPlayedGames() == 10) {

			Achievement a = repo.findById(3).get();
			if (profileService.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}

		}

		if (p.getWins() >= 1) {

			Achievement a = repo.findById(4).get();
			if (profileService.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}

		}
		if (p.getSteals() >= 1) {

			Achievement a = repo.findById(5).get();
			if (profileService.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}
		
		if (p.getMatches() >= 1) {

			Achievement a = repo.findById(7).get();
			if (profileService.hasAchievement(a, p) == false) {

				giveAchievement(p, a);

			}
		}
		
	}
	
	public void updateGlobalAchievements() {
		
		Achievement winnerAchievement = repo.findById(6).get();
		Comparator<Profile> Cwins = Comparator.comparing(Profile::getWins);
		List<Profile> wins = profileService.getProfiles().stream().sorted(Cwins.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxWin = wins.get(0);
		for (Profile p : wins) {
			
			if(profileService.hasAchievement(winnerAchievement, p)) profileService.deleteAchievement(winnerAchievement, p);
			
		}
		if (maxWin.getWins()>0) giveAchievement(maxWin, winnerAchievement);
		
		Achievement stealAchievement = repo.findById(8).get();
		Comparator<Profile> Csteals = Comparator.comparing(Profile::getSteals);
		List<Profile> steals = profileService.getProfiles().stream().sorted(Csteals.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxSteal = steals.get(0);
		for (Profile p : steals) {
			
			if(profileService.hasAchievement(stealAchievement, p)) profileService.deleteAchievement(stealAchievement, p);
			
		}
		if (maxSteal.getSteals()>0) giveAchievement(maxSteal, stealAchievement);
		
		Achievement gamesAchievement = repo.findById(9).get();
		Comparator<Profile> CplayedGames = Comparator.comparing(Profile::getPlayedGames);
		List<Profile> playedGames = profileService.getProfiles().stream().sorted(CplayedGames.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxGames = playedGames.get(0);
		for (Profile p : playedGames) {
			
			if(profileService.hasAchievement(gamesAchievement, p)) profileService.deleteAchievement(gamesAchievement, p);
			
		}
		if (maxGames.getPlayedGames()>0) giveAchievement(maxGames, gamesAchievement);
		
		Achievement matchesAchievement = repo.findById(10).get();
		Comparator<Profile> Cmatches = Comparator.comparing(Profile::getMatches);
		List<Profile> matches = profileService.getProfiles().stream().sorted(Cmatches.reversed()).filter(p -> p.getId()!=1).collect(Collectors.toList());;
		Profile maxMatch = matches.get(0);
		for (Profile p : matches) {
			
			if(profileService.hasAchievement(matchesAchievement, p)) profileService.deleteAchievement(matchesAchievement, p);
			
		}
		if (maxMatch.getMatches()>0) giveAchievement(maxMatch, matchesAchievement);
		
	}

}