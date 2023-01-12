package org.springframework.samples.petclinic.achievement;


import static org.mockito.BDDMockito.given;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AchievementController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AchievementControllerTest {
	
	
	private static final Integer TEST_ID = 1;

	@Autowired
	protected AchievementController controller;
	
	@MockBean
	protected AchievementService service;
	
	@Autowired
	protected MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		
		Achievement a = new Achievement();
		a.setName("name");
		a.setDescription("Description");
		a.setBadgeImage("Manue");
		a.setThreshold(1.);
		a.setBlockedImage("Julian");
		
		given(service.getAchievements()).willReturn(Lists.newArrayList(a));
		given(service.getAchievementById(TEST_ID)).willReturn(new Achievement());
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowAchievementList() throws Exception {
		mockMvc.perform(get("/achievements/AchievementsListing")).andExpect(status().isOk()).andExpect(model().attributeExists("achievements"))
		.andExpect(view().name("achievements/AchievementsListing"));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationAchievement() throws Exception {
		
		mockMvc.perform(get("/achievements/new")).andExpect(status().isOk())
			.andExpect(view().name("achievements/createOrUpdateAchievementsForm")).andExpect(model().attributeExists("achievement"));
		
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationAchievementSuccess() throws Exception {
		
		mockMvc.perform(post("/achievements/new").with(csrf()).param("name", "Capi")
		.param("description", "toma ya").param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/achievements/AchievementsListing"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationAchievementHasErrors() throws Exception {
		
		mockMvc.perform(post("/achievements/new").with(csrf())
		.param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(model().attributeHasErrors("achievement"))
		.andExpect(status().isOk()).andExpect(view().name("achievements/createOrUpdateAchievementsForm"));
		
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateAchievement() throws Exception {
		
		mockMvc.perform(get("/achievements/{id}/edit", TEST_ID)).andExpect(status().isOk())
			.andExpect(view().name("achievements/createOrUpdateAchievementsForm")).andExpect(model().attributeExists("achievement"));
		
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateAchievementSuccess() throws Exception {
		
		mockMvc.perform(post("/achievements/{id}/edit", TEST_ID).with(csrf()).param("name", "Capi")
		.param("description", "toma ya").param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(status().isOk())
		.andExpect(view().name("achievements/AchievementsListing"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateAchievementHasErrors() throws Exception {
		
		mockMvc.perform(post("/achievements/{id}/edit", TEST_ID).with(csrf())
		.param("threshold", "2.0").param("badgeImage", "img")
		.param("blockedImage", "img2")).andExpect(model().attributeHasErrors("achievement"))
		.andExpect(status().isOk()).andExpect(view().name("achievements/createOrUpdateAchievementsForm"));
		
	}
	
	
	

}
