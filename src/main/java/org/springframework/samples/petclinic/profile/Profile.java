package org.springframework.samples.petclinic.profile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends BaseEntity{
	@NotNull
	private Integer playedGames;
	@NotNull
	private Integer matches;
	@NotNull
	private Integer wins; 
	@NotNull
	private Integer steals;

	@OneToOne(optional = false)
	private User user;
	
	@ManyToMany
	private List<Achievement> achievements;
}
