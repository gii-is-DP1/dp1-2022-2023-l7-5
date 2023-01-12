package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTests {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected AuthoritiesService authoService;
	
	@Test
	@Transactional
	public void shoulhInsertUser() {
		int found = userService.findAllUsers().size();
		
		User user = new User();
		user.setUsername("manuelEjemplo");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
				
		Authorities autho = new Authorities();
		autho.setUser(user);
		autho.setAuthority("44444");
		authoService.saveAuthorities(autho);
		
		assertThat(user.getUsername()).isNotNull();
		assertThat(userService.findAllUsers().size()).isEqualTo(found+1);
		
	}
	
	@Test
	void shouldFindUser() {
		
		User user = new User();
		user.setUsername("manuelEjemplo2");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
				
		String username = user.getUsername();
		
		assertThat(userService.findUser(username).get().getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	void shouldDeleteUser() {
		int found = userService.findAllUsers().size();
		
		User user = new User();
		user.setUsername("manuelEjemplo3");
		user.setEmail("manuel.ejemplo@gmail.com");
		user.setPassword("password");
		user.setEnabled(true);
		userService.saveUser(user);
	
		String username = user.getUsername();
		
		assertThat(userService.findAllUsers().size()).isEqualTo(found+1);
		
		userService.deleteUser(username);
		assertThat(userService.findAllUsers().size()).isEqualTo(found);
		assertThat(userService.findUser(username)).isEqualTo(Optional.empty());
		
	}
}
