package org.springframework.samples.petclinic.user;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.tile.Tile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	@NotNull
	@NotBlank
	String username;
	
	@NotNull
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	String password;
	
	boolean enabled;
	
	@OneToMany
	private List<Tile> tiles;
	
	@OneToOne
	private Profile profile;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
