package org.springframework.samples.petclinic.achievement;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter

public class Achievement extends NamedEntity {

	@NotNull
	private Double threshold;
	
	@NotNull
	private String description;
	
	@NotNull
	private String badgeImage;

	@NotNull
	private String blockedImage;
}