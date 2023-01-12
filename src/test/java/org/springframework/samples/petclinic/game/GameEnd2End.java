package org.springframework.samples.petclinic.game;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class GameEnd2End {
	@LocalServerPort
	private int port;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
	mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
	}
	
	@WithMockUser(username = "arf", authorities = {"player"})
	@Test
	public void unsuccesfullAccesToGameListing() throws Exception {
		mockMvc.perform(get("/games/GamesListing"))
		.andExpect(status().is(HttpStatus.FORBIDDEN.value()));
	}
	
	@Test
	@WithMockUser(username = "admin2", authorities = {"admin"})
	public void succesfullAccesToGameListing() throws Exception {
		mockMvc.perform(get("/games/GamesListing"))
		.andExpect(status().is(HttpStatus.OK.value()));
	}
}
