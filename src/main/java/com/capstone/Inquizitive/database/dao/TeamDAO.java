package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TeamDAO extends JpaRepository<Team, Long> {

    Team findById(int id);
    Team findByTeamName(String teamName);

    @Query(value="select t.*, GROUP_CONCAT(u.username SEPARATOR ', ') as 'team_members' " +
            "from teams t left join team_members tm on t.id = tm.team_id left join users u on u.id = tm.user_id " +
            "group by t.id ;", nativeQuery=true)
    List<Map<String,Object>> getAllTeamsAndMembers();

    @Query(value="select t.*, GROUP_CONCAT(u.username SEPARATOR ', ') as 'team_members' " +
            "from teams t left join team_members tm on t.id = tm.team_id left join users u on u.id = tm.user_id " +
            "group by t.id order by t.total_score desc;", nativeQuery=true)
    List<Map<String,Object>> getAllTeamsAndMembersOrderedByScore();

    @Query(value="select t.*, GROUP_CONCAT(u.username SEPARATOR ', ') as 'team_members' " +
            "from teams t left join team_members tm on t.id = tm.team_id left join users u on u.id = tm.user_id " +
            "group by t.id order by t.team_name;", nativeQuery=true)
    List<Map<String,Object>> getAllTeamsAndMembersOrderedByName();

    @Query(value="SELECT * FROM teams", nativeQuery = true)
    List<Team> getAllTeams();
}
