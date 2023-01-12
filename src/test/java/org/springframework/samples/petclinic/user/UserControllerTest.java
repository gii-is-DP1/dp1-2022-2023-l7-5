package org.springframework.samples.petclinic.user;

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
import org.springframework.samples.petclinic.achievement.AchievementService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.profile.ProfileService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTest {

	private static final String TEST_ID = "manuel";
	
	@Autowired
	protected UserController userController;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private AuthoritiesService authoritiesService;
	
	@MockBean
	private AchievementService achievementService;
	
	@MockBean
	private ProfileService profileService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		User user = new User();
		user.setUsername("manuel");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
//		user.setEnabled(true);
//		
//		Authorities auth = new Authorities();
//		auth.setUser(user);
//		auth.setAuthority("admin");
		
		given(userService.findAllUsers()).willReturn(Lists.newArrayList(user));
//		given(userService.findUser(TEST_ID).get()).willReturn(user);
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testShowPlayers() throws Exception {
//		mockMvc.perform(get("/users/page/{page}", "2")).andExpect(status().isOk()).andExpect(model().attributeExists("page"))
//		.andExpect(view().name("/users/page/2"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationUser() throws Exception {
		
		mockMvc.perform(get("/users/new")).andExpect(status().isOk())
			.andExpect(view().name("users/createPlayerForm")).andExpect(model().attributeExists("user"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationUserSucess() throws Exception {
		
		mockMvc.perform(post("/users/new").with(csrf()).param("username", "Capi")
		.param("email", "test@gmail.com").param("password", "contrasena").param("enabled", "true"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationUsersHasErrors() throws Exception {
		
		mockMvc.perform(post("/users/new").with(csrf())
		.param("username", "ejemplo").param("email", "gmailcom").param("password", "contrasena").param("enabled", "true"))
		.andExpect(view().name("exception"));
		
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testInitUpdateUser() throws Exception {
//		
//		mockMvc.perform(get("/player/{username}/edit", TEST_ID)).andExpect(status().isOk())
//			.andExpect(view().name("/player/ejemplo/edit")).andExpect(model().attributeExists("user"));
//		
//	}
//	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateUserSuccess() throws Exception {
//		
//		mockMvc.perform(post("/player/{username}/edit", TEST_ID).with(csrf()).param("name", "Capi")
//		.param("email", "test@gmail.com").param("password", "contrasena").param("enabled", "true"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("player/PlayersListing"));
//		
//	}
//	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateUserHasErrors() throws Exception {
//		
//		mockMvc.perform(post("/player/{username}/edit", TEST_ID).with(csrf())
//		.param("email", "test@gmail.com").param("password", "contrasena").param("enabled", "true"))
//		.andExpect(model().attributeHasErrors("user"))
//		.andExpect(status().isOk()).andExpect(view().name("users/createPlayerForm"));
//		
//	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testShowUser() throws Exception {
//		mockMvc.perform(get("/player/{username}", TEST_ID)).andExpect(status().isOk())
//		.andExpect(model().attributeExists("user"))
//		.andExpect(view().name("player/manuel"));
//	}
}

