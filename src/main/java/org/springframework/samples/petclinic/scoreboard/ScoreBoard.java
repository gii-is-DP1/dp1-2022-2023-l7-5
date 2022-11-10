package org.springframework.samples.petclinic.scoreboard;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ScoreBoard extends BaseEntity{
	
	@NotNull
	@Size(min = 1, max = 4)
	private Integer order;
	
	@NotNull
	@PositiveOrZero
	private Integer score;

}
