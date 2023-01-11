package org.springframework.samples.petclinic.user;

import static org.mockito.BDDMockito.given;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

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

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTest {

	private static final String TEST_ID = "ejemplo";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		User user = new User();
		user.setUsername("ejemplo");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		
//		Authorities auth = new Authorities();
//		auth.setUser(user);
//		auth.setAuthority("player");
		
		given(this.userService.findAllUsers()).willReturn(Lists.newArrayList(user));
		given(this.userService.findUser(TEST_ID).get()).willReturn(new User());
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testShowPlayers() throws Exception {
//		mockMvc.perform(get("/players/PlayersListing")).andExpect(status().isOk()).andExpect(model().attributeExists("players"))
//		.andExpect(view().name("/players/PlayersListing"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationUser() throws Exception {
		
		mockMvc.perform(get("users/new")).andExpect(status().isOk())
			.andExpect(view().name("users/createPlayerForm")).andExpect(model().attributeExists("users"));
		
	}
}
