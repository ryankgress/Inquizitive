package com.capstone.Inquizitive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    /**
     * Simple GET mapping for signin page
     * @return signin.jsp
     */
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    /**
     * Simple GET mapping for signin/redirect page. Is called when trying to access pages w/o authentication.
     * @return signinRedirect.jsp
     */
    @GetMapping("/signin/redirect")
    public String signinRedirect() {
        return "signinRedirect";
    }


}
