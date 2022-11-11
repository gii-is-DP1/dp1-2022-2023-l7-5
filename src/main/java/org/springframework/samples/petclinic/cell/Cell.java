package org.springframework.samples.petclinic.cell;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cell extends BaseEntity{
	
	@NotNull
	private Integer position;
	@NotNull
	private Boolean isFlipped;
	@NotNull
	private Boolean isBlocked;
	
	@OneToMany
	private List<Cell> adjacents;
}
