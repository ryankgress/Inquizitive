package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(int id);

    User findByUsername(String username);

    @Query(value = "select td.id, td.trivia_name, r.placement " +
            "from results r, trivia_details td, team_members tm, teams t " +
            "where r.team_id = t.id and tm.team_id = t.id and r.trivia_id = td.id and tm.user_id = :id and td.active = 'false' " +
            "order by td.date_time " +
            "limit 4;", nativeQuery = true)
    List<Map<String, Object>> getRecentResults(Integer id);



}
