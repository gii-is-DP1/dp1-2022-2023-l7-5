package org.springframework.samples.petclinic.scoreboard;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ScoreBoard extends BaseEntity{
	
	@NotNull
	@Min(1)
	@Max(4)
	private Integer orden;
	
	@NotNull
	@PositiveOrZero
	private Integer score;

}
