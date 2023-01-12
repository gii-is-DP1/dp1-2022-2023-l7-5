package org.springframework.samples.petclinic.game;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;


import java.time.LocalDate;

import static org.mockito.BDDMockito.given;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.cell.CellService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.scoreboard.ScoreBoardService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)


public class GameControllerTests {

	private static final int TEST_ID = 1;

	@MockBean
	private GameService gameService;

	@MockBean
	private ScoreBoardService scoreboardService;

	@MockBean
	private UserService userService;

	@MockBean
    private CellService cellSercive;
	
	@MockBean
	private AchievementService achievementService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		Game game = new Game();
		game.setMode("SURVIVAL");
		game.setFinished(false);
		game.setNumberOfPlayers(1);
		game.setNumberCurrentPlayers(1);
		game.setDateOfCreation(LocalDate.now());
		given(gameService.getGames()).willReturn(Lists.newArrayList(game));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testShowPlay() throws Exception {
		
		mockMvc.perform(get("/games"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/play"));
	}

	@WithMockUser(value = "spring")
	@Test
	public void testShowGameListing() throws Exception {

		mockMvc.perform(get("/games/GamesListing")).andExpect(status().isOk()).andExpect(model()
				.attributeExists("games")).andExpect(view().name("games/GamesListing"));
	}

	@WithMockUser(value = "spring")
	@Test
	public void testDeleteGame() throws Exception {
		mockMvc.perform(get("/games/{id}/delete", TEST_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("games/GamesListing"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationGame() throws Exception {

		mockMvc.perform(get("/games/new")).andExpect(status().isOk()).andExpect(view()
				.name("games/createOrUpdateGameForm")).andExpect(model().attributeExists("game"));


	}

	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationGameSucess() throws Exception {

		mockMvc.perform(get("/games/new").with(csrf()).param("mode", "SURVIVAL"))
					.andExpect(status().is2xxSuccessful()).andExpect(view().name("games/createOrUpdateGameForm"));

	}

	@WithMockUser(value = "spring")
	@Test
	public void testInitCreationGameHasErrors() throws Exception {
		
		mockMvc.perform(post("/games/new").with(csrf())).andExpect(model().attributeHasErrors("game"))
					.andExpect(status().isOk()).andExpect(view().name("games/createOrUpdateGameForm"));
		
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	public void testInitUpdateGame() throws Exception {
//		
//		mockMvc.perform(get("/games/{id}/edit", TEST_ID)).andExpect(status().isOk())
//		.andExpect(view().name("games/createOrUpdateGameForm"))
//		.andExpect(model().attributeExists("game"));
//		
//		
//	}

	@WithMockUser(value = "spring")
	@Test
	public void testProcessUpdateGameSucess() throws Exception {

		mockMvc.perform(post("/games/{id}/edit", TEST_ID).with(csrf()).param("mode", "SURVIVAL"))
		.andExpect(status().is2xxSuccessful()).andExpect(view().name("games/createOrUpdateGameForm"));

	}

	@WithMockUser(value = "spring")
	@Test
	public void testProcessUpdateGameHasErrors() throws Exception {

		mockMvc.perform(post("/games/{id}/edit", TEST_ID).with(csrf())).andExpect(model().attributeHasErrors("game"))
					.andExpect(status().isOk()).andExpect(view().name("games/createOrUpdateGameForm"));

	}

	@WithMockUser(value = "spring")
	@Test
	public void testGameDetails() throws Exception {
		
		mockMvc.perform(get("/games/{id}/view", TEST_ID)).andExpect(status().isOk())
			.andExpect(model().attribute("game", hasProperty("mode", is("SURVIVAL"))))
			.andExpect(model().attribute("game", hasProperty("finished", is("false"))))
			.andExpect(model().attribute("game", hasProperty("numberOfPlayers", is("1"))))
			.andExpect(model().attribute("game", hasProperty("numberCurrentPlayers", is("1"))))
			.andExpect(model().attribute("game", hasProperty("dateOfCreation", is(LocalDate.now()))))
			.andExpect(view().name("games/gameDetails"));
	}

}