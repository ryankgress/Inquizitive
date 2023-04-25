package com.capstone.Inquizitive.controller;

import com.capstone.Inquizitive.database.dao.*;
import com.capstone.Inquizitive.database.entity.Result;
import com.capstone.Inquizitive.database.entity.Team;
import com.capstone.Inquizitive.database.entity.TriviaDetail;
import com.capstone.Inquizitive.database.entity.User;
import com.capstone.Inquizitive.formbeans.ResultsBean;
import com.capstone.Inquizitive.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ResultsController {
    @Autowired
    private TeamDAO teamDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private TriviaDetailDAO triviaDetailDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private TeamMemberDAO teamMemberDao;

    @Autowired
    private ResultDAO resultDao;

    @RequestMapping(value = "/results/{triviaId}", method = RequestMethod.GET)
    public ModelAndView results(@PathVariable Integer triviaId) {
        log.debug("In the results controller method");
        ModelAndView response = new ModelAndView("results");

        TriviaDetail trivia = triviaDetailDao.findById(triviaId);
        User user = authenticatedUserService.loadCurrentUser();

        List<Map<String, Object>> teams = resultDao.getTeamsByTriviaId(triviaId);
        String[] standingArr = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th"};

        response.addObject("standingArr", standingArr);
        response.addObject("teams", teams);
        response.addObject("user", user);
        response.addObject("trivia", trivia);
        return response;
    }

    @PostMapping(value = "/results/submit")
    public ModelAndView resultsSubmit(@RequestParam Map<String, String> parameters) {
        log.debug("In the results controller method");
        ModelAndView response = new ModelAndView("trivia");    // change?

        // Printing map values
        for(String p : parameters.keySet()) {
            log.debug(p + " = " + parameters.get(p));
        }

        TriviaDetail trivia = triviaDetailDao.findById(Integer.parseInt(parameters.get("triviaId")));

        int score = 300;
        for(String p : parameters.keySet()) {
            if(p.equals("name") || p.equals("triviaId")) {
                continue;
            } else {
                int thisTeamId = Integer.parseInt(parameters.get(p));
                Result thisEntry = resultDao.getResultfromTeamIdAndTriviaId(thisTeamId, trivia.getId());

                thisEntry.setPlacement(p);
                if(score > 0) {
                    thisEntry.setPointsAwarded(score);
                    Team thisTeam = teamDao.findById(thisTeamId);
                    thisTeam.setTotalScore(thisTeam.getTotalScore() + score);
                    teamDao.save(thisTeam);
                    score -= 100;
                }

                resultDao.save(thisEntry);
            }
        }



        // Marks this trivia as complete
        trivia.setActive("false");
        triviaDetailDao.save(trivia);

        response.setViewName("redirect:/trivia");

        return response;
    }
}
