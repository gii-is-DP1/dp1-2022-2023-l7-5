package org.springframework.samples.petclinic.cell;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.assertj.core.util.Lists;

import static org.mockito.BDDMockito.given;


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

@WebMvcTest(controllers = CellController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class CellControllerTests {

	@MockBean
	private CellService cellService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Cell cell1 = new Cell();
		cell1.setId(1);
		cell1.setIsBlocked(false);
		cell1.setIsFlipped(false);
		cell1.setPosition(1);
		Cell cell2 = new Cell();
		cell2.setId(0);
		cell2.setIsBlocked(false);
		cell2.setIsFlipped(false);
		cell2.setPosition(2);
		given(this.cellService.getCells()).willReturn(Lists.newArrayList(cell1, cell2));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowCellList() throws Exception {
		mockMvc.perform(get("/cells/CellsListing")).andExpect(status().isOk()).andExpect(model().attributeExists("cells"))
		.andExpect(view().name("cells/CellsListing"));
	}
	
	
}
