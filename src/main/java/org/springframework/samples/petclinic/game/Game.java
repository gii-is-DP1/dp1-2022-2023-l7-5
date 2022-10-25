package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {
	
	@NotNull
	@Column(name = "mode")
	private String mode;
	
	@NotNull
	@Column(name = "finished")
	private Boolean finished;
	
	@NotNull
	@Column(name = "number_of_players")
	private Integer numberOfPlayers;
	
	@NotNull
	@Column(name = "date_of_creation")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dateOfCreation;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.EAGER)
	private Set<Cell> cells;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.EAGER)
	private Set<Player> players;
}