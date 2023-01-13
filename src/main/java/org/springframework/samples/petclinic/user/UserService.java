/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;



import java.util.ArrayList;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	protected GameService gameService;
	
	@Autowired
	protected ProfileService profileService;

	@Autowired
	protected ScoreBoardService scoreboardService;
			
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
	
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public Page<User> findAllUserPageable(Integer page, Integer size) {
		return userRepository.getAllPaginated(PageRequest.of(page, size));
	}
	
	@Transactional
	public void deleteUser(String username) {
		User user = userRepository.findById(username).get();
		if (user.getProfile()!=null) {
			user.setProfile(null);
			userRepository.save(user);
			Profile p = profileService.getProfileByUsername(username);
			profileService.deleteProfileById(p.getId());
		}
		List<ScoreBoard> sbs = scoreboardService.getScoreBoardByUser(username);
		List<ScoreBoard> allScoreboards = new ArrayList<>();
		List<Integer> allGamesId = new ArrayList<>();
		for (ScoreBoard scoreBoard : sbs) {
			Game game = gameService.getGameById(scoreBoard.getGame().getId());
			allScoreboards.add(scoreBoard);
			allGamesId.add(scoreBoard.getGame().getId());
			List<ScoreBoard> sbsGames = scoreboardService.getScoreboardsByGameId(game.getId());
			for (ScoreBoard otherScoreBoards : sbsGames) {
				if (!otherScoreBoards.getUser().getUsername().equals(username)) {
					allScoreboards.add(otherScoreBoards);
				}
				if (!allGamesId.contains(otherScoreBoards.getGame().getId())) {
					allGamesId.add(otherScoreBoards.getGame().getId());
				}
			}
		}
		List<ScoreBoard> toDelete = new ArrayList<>();
		toDelete.addAll(allScoreboards);
		for (ScoreBoard scoreBoard : toDelete) {
			scoreBoard.setGame(null);
			scoreboardService.save(scoreBoard);
		}
		List<Integer> gamesToDelete = new ArrayList<>();
		gamesToDelete.addAll(allGamesId);
		for (Integer id : gamesToDelete) {
			Game game = gameService.getGameById(id);
			if(game != null) {
				gameService.deleteGameById(game.getId());
			}
		}
		for (ScoreBoard scoreBoard : toDelete) {
			scoreboardService.deleteScoreBoardById(scoreBoard.getId()); 
		}
		userRepository.deleteById(username);
	}
	
}
