package com.capstone.Inquizitive.database.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TeamDAOTest {

    @Autowired
    private TeamDAO teamDao;

    @Test
    public void getAllTeamsAndMembersTest() {
        List<Map<String,Object>> list = teamDao.getAllTeamsAndMembers();
        Map<String,Object> staff = list.get(3);

        Assertions.assertEquals(staff.get("team_name"), "Inquizitive Staff");
        Assertions.assertEquals(staff.get("team_pic"), "https://picsum.photos/id/11/400/300.jpg");
        Assertions.assertEquals(staff.get("team_desc"), "A group of developers keen on showing that we know a " +
                "whole lot more than just how to code!");
        Assertions.assertEquals(staff.get("team_members"), "ryankgress, vannaw");


    }

    @Test
    public void getAllTeamsAndMembersOrderedByScoreTest() {
        List<Map<String,Object>> list = teamDao.getAllTeamsAndMembersOrderedByScore();

        int lastScore = (int)list.get(0).get("total_score");

        for(Map<String, Object> team : list) {
            int thisScore = (int)team.get("total_score");
            Assertions.assertTrue(thisScore <= lastScore);
            lastScore = thisScore;
        }


    }

    @Test
    public void getAllTeamsAndMembersOrderedByNameTest() {
        List<Map<String,Object>> list = teamDao.getAllTeamsAndMembersOrderedByName();

        String lastName = (String)list.get(0).get("team_name");

        for(Map<String, Object> team : list) {
            String thisName = (String)team.get("team_name");
            Assertions.assertTrue(thisName.compareTo(lastName) >= 0);
            lastName = thisName;
        }


    }
}
