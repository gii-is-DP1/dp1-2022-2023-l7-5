package org.springframework.samples.petclinic.scoreboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ScoreBoardRepository extends CrudRepository<ScoreBoard, Integer> {
	List<ScoreBoard> findAll();
	@Query("SELECT sb FROM ScoreBoard sb WHERE sb.game.id = :id")
	List<ScoreBoard> getScoreboardsByGameId(@Param("id") Integer id);
	@Query("SELECT sb FROM ScoreBoard sb WHERE sb.user.username = :username")
	List<ScoreBoard> getScoreboardsByUser(@Param("username") String username);
	@Query("SELECT sb FROM ScoreBoard sb WHERE sb.game.id = :id AND sb.user.username = :username")
	ScoreBoard getScoreboardByGameIdByUser(@Param("username") String username, @Param("id") Integer id);
}

