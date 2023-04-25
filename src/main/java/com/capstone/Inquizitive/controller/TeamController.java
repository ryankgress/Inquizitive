package com.capstone.Inquizitive.controller;

import com.capstone.Inquizitive.database.dao.TeamDAO;
import com.capstone.Inquizitive.database.dao.TeamMemberDAO;
import com.capstone.Inquizitive.database.dao.UserDAO;
import com.capstone.Inquizitive.database.entity.Team;
import com.capstone.Inquizitive.database.entity.TeamMember;
import com.capstone.Inquizitive.database.entity.User;
import com.capstone.Inquizitive.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TeamController {
    @Autowired
    private TeamMemberDAO teamMemberDao;

    @Autowired
    private TeamDAO teamDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public ModelAndView teams() {
        log.debug("In the teams controller method");
        ModelAndView response = new ModelAndView("teams");

        List<Map<String,Object>> memberList = teamDao.getAllTeamsAndMembers();
        User user = authenticatedUserService.loadCurrentUser();

        response.addObject("user", user);
        response.addObject("memberList", memberList);
        return response;
    }

    @RequestMapping(value = "/teams/score", method = RequestMethod.GET)
    public ModelAndView teamsOrderedByScore() {
        log.debug("In the teams controller method");
        ModelAndView response = new ModelAndView("teams");

        List<Map<String,Object>> memberList = teamDao.getAllTeamsAndMembersOrderedByScore();
        User user = authenticatedUserService.loadCurrentUser();

        response.addObject("user", user);
        response.addObject("memberList", memberList);
        return response;
    }

    @RequestMapping(value = "/teams/name", method = RequestMethod.GET)
    public ModelAndView teamsOrderedByName() {
        log.debug("In the teams controller method");
        ModelAndView response = new ModelAndView("teams");

        List<Map<String,Object>> memberList = teamDao.getAllTeamsAndMembersOrderedByName();
        User user = authenticatedUserService.loadCurrentUser();

        response.addObject("user", user);
        response.addObject("memberList", memberList);
        return response;
    }

    @RequestMapping(value = "/teams/join/{teamId}", method = RequestMethod.GET)
    public ModelAndView teamsJoin(@PathVariable Integer teamId) {
        log.debug("In the teams controller method");
        ModelAndView response = new ModelAndView("teams");

        String thisName = authenticatedUserService.getCurrentUsername();
        log.debug(thisName);

        User user = authenticatedUserService.loadCurrentUser();

        TeamMember teamMember = new TeamMember();
        teamMember.setUserId(user.getId());
        teamMember.setTeamId(teamId);
        teamMember.setTeam(teamDao.findById(teamId));
        teamMember.setUser(userDao.findById(user.getId()));

        log.debug("User ID: " + teamMember.getUserId() + " | Team ID: " + teamMember.getTeamId());

        teamMemberDao.save(teamMember);
        List<Map<String,Object>> memberList = teamDao.getAllTeamsAndMembers();

        response.addObject("user", user);

        response.addObject("memberList", memberList);

        return response;
    }
}
