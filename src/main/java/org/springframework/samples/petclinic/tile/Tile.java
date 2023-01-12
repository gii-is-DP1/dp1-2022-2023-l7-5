package org.springframework.samples.petclinic.tile;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tile extends BaseEntity {
	@NotNull
	private String startingSide;
	@NotNull
	private String filledSide;
	@NotNull
	private String startingSideColor;
	@NotNull
	private String filledSideColor;
}
