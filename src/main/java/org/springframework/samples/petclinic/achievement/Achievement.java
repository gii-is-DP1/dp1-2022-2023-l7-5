package org.springframework.samples.petclinic.achievement;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Achievement extends NamedEntity{

	@NotNull
	private Double threshold;
	@NotNull
	private String description;
	@NotNull
	private String badgeImage;
}
