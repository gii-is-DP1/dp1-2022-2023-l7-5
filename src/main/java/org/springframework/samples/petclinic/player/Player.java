package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

//Entidad
@Entity
@Getter
@Setter
public class Player extends BaseEntity {
	
	@NotNull
	private String username;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	

}
