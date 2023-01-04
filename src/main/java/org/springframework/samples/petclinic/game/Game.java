package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.cell.Cell;
//import org.springframework.samples.petclinic.cell.Cell;
import org.springframework.samples.petclinic.model.BaseEntity;
//import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.scoreboard.ScoreBoard;
import org.springframework.samples.petclinic.tile.Tile;

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
	@Column(name = "number_current_players")
	private Integer numberCurrentPlayers;
	
	@NotNull
	@Column(name = "number_max_players")
	private Integer numberOfPlayers;
	
	@NotNull
	@Column(name = "date_of_creation")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@PastOrPresent
	private LocalDate dateOfCreation;
	
	@OneToMany(mappedBy="game")
	private List<ScoreBoard> scoreboards;
	
	@OneToMany
	private List<Cell> cells;
	
	@OneToMany
	private List<Tile> bag;
	
	private Integer turn;
}