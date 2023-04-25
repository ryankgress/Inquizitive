package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.Result;
import com.capstone.Inquizitive.database.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ResultDAO extends JpaRepository<Result, Long> {
    Result findById(int id);

    @Query(value =
            "SELECT t.* FROM results r JOIN teams t ON(t.id = r.team_id) " +
            "WHERE r.trivia_id = :triviaId ;", nativeQuery = true)
    List<Map<String,Object>> getTeamsByTriviaId(Integer triviaId);

    @Query(value = "SELECT * FROM results r WHERE r.team_id = :teamId AND r.trivia_id = :triviaId ;", nativeQuery = true)
    Result getResultfromTeamIdAndTriviaId(Integer teamId, Integer triviaId);

    @Query(value = "select td.id, r.points_awarded, r.placement, t.team_name\n" +
            "from trivia_details td join results r on(td.id = r.trivia_id) join teams t on(t.id = r.team_id)\n" +
            "where td.active = 'false'\n" +
            "order by td.id, r.placement ;", nativeQuery = true)
    List<Map<String, Object>> getAllResultsOrdered();
}
