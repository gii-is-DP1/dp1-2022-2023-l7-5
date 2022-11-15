package org.springframework.samples.petclinic.scoreboard;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

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

	@ManyToOne(optional = false)
	private User user;
	
	@ManyToMany
	private List<Game> games;
}
