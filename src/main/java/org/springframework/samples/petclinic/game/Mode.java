package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

/**
 * Can be SOLO, COMPETITIVE, SURVIVAL
 */

@Entity
@Table(name = "modes")
public class Mode extends NamedEntity {
	
}