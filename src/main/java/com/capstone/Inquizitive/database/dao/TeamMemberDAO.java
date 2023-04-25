package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.Team;
import com.capstone.Inquizitive.database.entity.TeamMember;
import com.capstone.Inquizitive.database.entity.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface TeamMemberDAO extends JpaRepository<TeamMember, Long> {
    @Query("SELECT tm FROM TeamMember tm WHERE tm.id = :id ")
    TeamMember findById(int id);

    @Query("SELECT u FROM User u, TeamMember tm, Team t " +
            "WHERE u.id = tm.userId AND tm.teamId = t.id AND t.teamName = :teamName ")
    List<User> getUsersByTeamName(String teamName);

    @Query(value = "SELECT t.* FROM teams t, team_members tm WHERE t.id = tm.team_id AND tm.user_id = :id ;", nativeQuery = true)
    List<Map<String,Object>> getTeamsByUserId(Integer id);

    @Query(value =
            "SELECT SUM(t.total_score) AS user_total " +
            "FROM users u LEFT JOIN team_members tm ON u.id = tm.user_id LEFT JOIN teams t ON t.id = tm.team_id " +
            "WHERE u.id = :id " +
            "GROUP BY u.id ;", nativeQuery = true)
    Integer getUserTotalById(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM TeamMember WHERE userId = :userId AND teamId = :teamId ")
    void leaveTeam(Integer userId, Integer teamId);

}
