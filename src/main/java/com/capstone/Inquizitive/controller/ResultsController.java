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

    /**
     * GET mapping to navigate to the update trivia results form for the given Trivia ID. Passes the host user, teams
     * signed up for the trivia, an array of Strings for database update, and the current trivia info to the jsp. Will
     * forward to resultsSubmit on form submission
     * @param triviaId id of the trivia to be updated, grabbed from the user's profile page
     * @return results.jsp
     */
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

    /**
     * POST mapping to handle form submission and updating the database. Assigns values to both 'placement' and 'total_score'
     * based on standings. Also changes the active status of the trivia to 'false' when trivia is reported as completed.
     * @param parameters holds the order of the teams' reported standings. Iterated through to assign points and standings.
     * @return trivia.jsp
     */
    @PostMapping(value = "/results/submit")
    public ModelAndView resultsSubmit(@RequestParam Map<String, String> parameters) {
        log.debug("In the results controller method");
        ModelAndView response = new ModelAndView("trivia");    // change?

        // Printing map values
        for(String p : parameters.keySet()) {
            log.debug(p + " = " + parameters.get(p));
        }

        TriviaDetail trivia = triviaDetailDao.findById(Integer.parseInt(parameters.get("triviaId")));

        // 1st: 300, 2nd: 200, 3rd: 100. Everyone else, 0.
        int score = 300;
        for(String p : parameters.keySet()) {
            if(p.equals("name") || p.equals("triviaId")) {
                continue;
            } else {
                int thisTeamId = Integer.parseInt(parameters.get(p));
                Result thisEntry = resultDao.getResultFromTeamIdAndTriviaId(thisTeamId, trivia.getId());

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
