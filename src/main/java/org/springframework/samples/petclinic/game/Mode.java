package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

/**
 * Can be SOLO, COMPETITIVE, SURVIVAL
 */

@Entity
@Table(name = "modes")
public class Mode extends NamedEntity {
	@NotNull
	@Min(1)
	@Max(4)
	Integer maxPlayers;
}