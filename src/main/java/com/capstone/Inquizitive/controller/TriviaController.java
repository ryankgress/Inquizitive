package com.capstone.Inquizitive.controller;

import com.capstone.Inquizitive.database.dao.*;
import com.capstone.Inquizitive.database.entity.*;
import com.capstone.Inquizitive.formbeans.RsvpBean;
import com.capstone.Inquizitive.formbeans.TriviaBean;
import com.capstone.Inquizitive.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TriviaController {
    @Autowired
    private UserDAO userDao;

    @Autowired
    private TeamMemberDAO teamMemberDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private TriviaDetailDAO triviaDetailDao;

    @Autowired
    private ResultDAO resultDao;

    @Autowired
    private TeamDAO teamDao;

    @RequestMapping(value = "/trivia", method = RequestMethod.GET)
    public ModelAndView trivialist() {
        log.debug("In the trivialist controller method");
        ModelAndView response = new ModelAndView("trivia");

        List<TriviaDetail> activeTriviaDetailList = triviaDetailDao.getAllActiveRecords();
        List<TriviaDetail> inactiveTriviaDetailList = triviaDetailDao.getAllInactiveRecords();
        List<Map<String, Object>> allStandings = resultDao.getAllResultsOrdered();

        User u = authenticatedUserService.loadCurrentUser();
        List<Map<String,Object>> myTeams = teamMemberDao.getTeamsByUserId(u.getId());

        List<UserRole> userRoles = userRoleDao.findByUserId(u.getId());
        boolean isHost = false;
        for(UserRole role : userRoles) {
            if(role.getRoleName().equals("HOST")) {
                isHost = true;
            }
        }



        response.addObject("myTeams", myTeams);
        response.addObject("isHost", isHost);
        response.addObject("activeTriviaDetailList", activeTriviaDetailList);
        response.addObject("inactiveTriviaDetailList", inactiveTriviaDetailList);
        response.addObject("allStandings", allStandings);
        return response;
    }

    @PostMapping(value = "/triviaRegister")
    public ModelAndView triviaRegister(RsvpBean form) {
        ModelAndView response = new ModelAndView("trivia");
        log.debug("In the trivia register controller method");

        Result result = new Result();
        result.setTriviaId(form.getTriviaId());
        result.setTeamId(form.getTeamId());
        result.setTriviaDetail(triviaDetailDao.findById(form.getTriviaId()));
        result.setTeam(teamDao.findById(form.getTeamId()));

        resultDao.save(result);
        log.debug(result.toString());

        response.setViewName("redirect:/trivia");

        return response;
    }

    @RequestMapping(value = "/newTrivia", method = RequestMethod.POST)
    public ModelAndView newTrivia(@Valid TriviaBean form, BindingResult bindingResult, HttpSession httpSession) throws IOException, ParseException {
        log.debug("In the register controller registerSubmit method");
        ModelAndView response = new ModelAndView("trivia");

        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field : " + error.getField() + " with message : " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            return response;
        }

        TriviaDetail triviaDetail = new TriviaDetail();

        triviaDetail.setTriviaName(form.getTriviaName());
        triviaDetail.setLocationName(form.getLocationName());
        triviaDetail.setAddress1(form.getAddress1());
        triviaDetail.setAddress2(form.getAddress2());
        triviaDetail.setCity(form.getCity());
        triviaDetail.setZip(form.getZip());
        triviaDetail.setState(form.getState());
        triviaDetail.setActive("true");

        // Date/Time Stuff
        String dateTime = form.getDate() + " " + form.getTime();
        log.debug(dateTime);
        // This will be 04/19/2023 11:58 am (verify)

        // Verify this
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = sdf.parse(dateTime);

        triviaDetail.setDateTime(date);

        User user = authenticatedUserService.loadCurrentUser();
        triviaDetail.setHostId(user.getId());
        triviaDetail.setHost(user);

        response.addObject("triviaDetail",triviaDetail);

        log.debug(triviaDetail.toString());

        triviaDetailDao.save(triviaDetail);

        // If successful, redirect to trivia homepage
        response.setViewName("redirect:/trivia");

        return response;
    }
}
