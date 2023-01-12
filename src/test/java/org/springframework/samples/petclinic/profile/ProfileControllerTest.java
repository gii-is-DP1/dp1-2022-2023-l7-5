package org.springframework.samples.petclinic.profile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = ProfileController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProfileControllerTest {

	@Autowired
	protected ProfileController controller;
	
	@MockBean
	protected ProfileService service;
	
	@Autowired
	protected MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		
		User user = new User();
		user.setUsername("username");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		
		Profile p = new Profile();
		p.setPlayedGames(0);
		p.setMatches(0);
		p.setWins(0);
		p.setSteals(0);
		p.setUser(user);
		p.setAchievements(new ArrayList<Achievement>());
		
		given(service.getProfiles()).willReturn(Lists.newArrayList(p));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowProfileList() throws Exception {
		mockMvc.perform(get("/profiles/ProfilesListing")).andExpect(status().isOk()).andExpect(model().attributeExists("profiles"))
		.andExpect(view().name("profiles/ProfilesListing"));
	}
	
	
}
