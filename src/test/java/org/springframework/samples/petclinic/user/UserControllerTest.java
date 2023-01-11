package org.springframework.samples.petclinic.user;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.assertj.core.util.Lists;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTest {

	private static final Integer PAGE = 0;
	private static final String USERNAME = "angel";

	@Autowired
	private UserController controller;

	@MockBean
	private UserService service;

	@MockBean
	private AuthoritiesService authoritiesService;

	@MockBean
	private AchievementService achService;

	@MockBean
	private ProfileService profileService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		User user = new User();
		user.setUsername("username");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		this.service.saveUser(user);

		given(this.service.findAllUsers()).willReturn(Lists.newArrayList(user));
		
		//NoSuchElementException
		//given(this.service.findUser(USERNAME).get()).willReturn(new User());


	}

	@WithMockUser(value = "spring")
	@Test
	void testShowUserList() throws Exception {
		mockMvc.perform(get("/players/PlayersListing")).andExpect(status().isOk())
				.andExpect(model().attributeExists("players")).andExpect(view().name("/players/PlayersListing"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationPlayer() throws Exception {

		mockMvc.perform(get("/users/new")).andExpect(status().isOk()).andExpect(view().name("users/createPlayerForm"))
				.andExpect(model().attributeExists("user"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationUserSuccess() throws Exception {

		mockMvc.perform(post("/users/new").with(csrf()).param("username", "capi").param("email", "capi@gmail.com")
				.param("password", "2.0").param("enabled", "true")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationUserHasErrors() throws Exception {

		mockMvc.perform(post("/users/new").with(csrf()).param("email", "gmailcom").param("password", "2.0")
				.param("enabled", "true")).andExpect(view().name("exception"));

	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateUser() throws Exception {
		
		mockMvc.perform(get("/player/{username}/edit", USERNAME)).andExpect(status().isOk())
			.andExpect(view().name("/player/{username}/edit")).andExpect(model().attributeExists("player"));
		
	}
	
	
	/*@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateUserSuccess() throws Exception {
		
		mockMvc.perform(post("/achievements/{id}/edit", TEST_ID).with(csrf()).param("name", "Capi")
		.param("description", "toma ya").param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(status().isOk())
		.andExpect(view().name("achievements/AchievementsListing"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateUserHasErrors() throws Exception {
		
		mockMvc.perform(post("/achievements/{id}/edit", TEST_ID).with(csrf())
		.param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(model().attributeHasErrors("achievement"))
		.andExpect(status().isOk()).andExpect(view().name("achievements/createOrUpdateAchievementsForm"));
		
	}*/

}
