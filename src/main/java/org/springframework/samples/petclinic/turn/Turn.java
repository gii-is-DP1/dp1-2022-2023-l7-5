package org.springframework.samples.petclinic.turn;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turns")
public class Turn extends BaseEntity {
	
	@NotNull
	@Column(name = "number_of_players")
	private Integer numberOfPlayers;

	@NotNull
	@Column(name = "date_of_creation")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dateOfCreation;
}