package org.springframework.samples.petclinic.profile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.audit.AuditableEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends AuditableEntity {

	@NotNull
	private Integer playedGames;
	@NotNull
	private Integer matches;
	@NotNull
	private Integer wins; 
	@NotNull
	private Integer steals;
	
	private Integer record;

	@OneToOne
	private User user;
	
	@ManyToMany
	private List<Achievement> achievements;
	
}
