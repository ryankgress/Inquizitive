package com.capstone.Inquizitive.controller;

import com.capstone.Inquizitive.database.dao.*;
import com.capstone.Inquizitive.database.entity.*;
import com.capstone.Inquizitive.formbeans.TriviaBean;
import com.capstone.Inquizitive.formbeans.UserBean;
import com.capstone.Inquizitive.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MainController {

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

    @RequestMapping(value = {"/index", "/", "/index.html"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        log.debug("In the profile controller method");
        ModelAndView response = new ModelAndView("profile");

        User user = authenticatedUserService.loadCurrentUser();

        List<Map<String,Object>> teams = teamMemberDao.getTeamsByUserId(user.getId());

        Integer totScore = teamMemberDao.getUserTotalById(user.getId());

        List<TriviaDetail> myTrivias = triviaDetailDao.getActiveTriviaByHostId(user.getId());

        List<Map<String,Object>> recentStandings = userDao.getRecentResults(user.getId());

        response.addObject("teams", teams);
        response.addObject("user", user);
        response.addObject("totScore", totScore);
        response.addObject("myTrivias", myTrivias);
        response.addObject("recentStandings", recentStandings);
        return response;
    }

    @RequestMapping(value = "/profile/{teamId}", method = RequestMethod.GET)
    public ModelAndView profileRemoveTeam(@PathVariable Integer teamId) {
        log.debug("In the profile controller method");
        ModelAndView response = new ModelAndView("profile");

        Team removeTeam = teamDao.findById(teamId);
        User user = authenticatedUserService.loadCurrentUser();


        teamMemberDao.leaveTeam(user.getId(), teamId);

        List<Map<String,Object>> teams = teamMemberDao.getTeamsByUserId(user.getId());

        Integer totScore = teamMemberDao.getUserTotalById(user.getId());

        List<TriviaDetail> myTrivias = triviaDetailDao.getActiveTriviaByHostId(user.getId());


        response.addObject("teams", teams);
        response.addObject("user", user);
        response.addObject("totScore", totScore);
        response.addObject("myTrivias", myTrivias);

        return response;
    }


    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView response = new ModelAndView("editProfile");

        User user = authenticatedUserService.loadCurrentUser();
        UserBean form = new UserBean();

        form.setId(user.getId());
        form.setName(user.getName());
        form.setUsername(user.getUsername());
        form.setEmail(user.getEmail());
        form.setPassword(user.getPassword());
        form.setProfilePic(user.getProfilePic());

        response.addObject("form", form);

        Integer userScore = teamMemberDao.getUserTotalById(user.getId());
        response.addObject("userScore", userScore);

        log.debug("In edit profile controller method");

        return response;
    }

    @RequestMapping(value = "/editProfileSubmit", method = RequestMethod.POST)
    public ModelAndView editProfileSubmit(UserBean form, HttpSession httpSession, BindingResult bindingResult) throws IOException {
        log.debug("In the editProfile controller submit method");
        ModelAndView response = new ModelAndView("editProfile");


        User user = userDao.findById(form.getId());
        if(!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            bindingResult.rejectValue("password", "error.password", "Please enter a valid password");
            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);

            return response;
        }


        File target;

        if(!form.getProfilePicture().isEmpty()) {
            target = new File("./src/main/webapp/pub/images/" + form.getProfilePicture().getOriginalFilename());
            log.debug("Target path: " + target.getAbsolutePath());
            FileUtils.copyInputStreamToFile(form.getProfilePicture().getInputStream(), target);
            user.setProfilePic("/pub/images/" + form.getProfilePicture().getOriginalFilename());
        } else {
            user.setProfilePic(form.getProfilePic());
        }

        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());

        response.addObject("user", user);
        log.debug(user.toString());

        userDao.save(user);

        authenticatedUserService.changeLoggedInUsername(httpSession, form.getUsername(), form.getPassword());

        response.setViewName("editLanding");
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        log.debug("In the register controller method");
        ModelAndView response = new ModelAndView("register");

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@Valid UserBean form, BindingResult bindingResult, HttpSession httpSession) throws IOException {
        log.debug("In the register controller registerSubmit method");
        ModelAndView response = new ModelAndView("register");

        if (!StringUtils.equals(form.getPassword(), form.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        if(bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field : " + error.getField() + " with message : " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            return response;
        }

        File target;
        User user = new User();

        if(!form.getProfilePicture().isEmpty()) {
            target = new File("./src/main/webapp/pub/images/" + form.getProfilePicture().getOriginalFilename());
            log.debug("Target path: " + target.getAbsolutePath());
            FileUtils.copyInputStreamToFile(form.getProfilePicture().getInputStream(), target);
            user.setProfilePic("/pub/images/" + form.getProfilePicture().getOriginalFilename());
        } else {
            user.setProfilePic("/pub/images/default-pfp.png");
        }

        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());

        String encPass = passwordEncoder.encode(form.getPassword());
        user.setPassword(encPass);

        response.addObject("user", user);
        log.debug(user.toString());

        userDao.save(user);

        if(form.getHost() != null) {
            UserRole userRoleHost = new UserRole();
            userRoleHost.setRoleName("HOST");
            userRoleHost.setUserId(user.getId());
            userRoleDao.save(userRoleHost);
        }

        // User Role Assignment
        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        userRole.setUserId(user.getId());
        userRoleDao.save(userRole);

        authenticatedUserService.changeLoggedInUsername(httpSession, form.getUsername(), form.getPassword());

        // If successful, redirect to index
        response.setViewName("redirect:/index");

        return response;
    }



}
