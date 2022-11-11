package org.springframework.samples.petclinic.profile;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

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
}
