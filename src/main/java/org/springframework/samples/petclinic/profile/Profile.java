package org.springframework.samples.petclinic.profile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.achievement.Achievement;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
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

	@OneToOne(optional = false)
	private User user;
	
	@OneToMany
	private List<Achievement> achievements;
}