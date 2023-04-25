package springexamples.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login/loginPage")
    public ModelAndView login() {
        log.debug("In the login controller method");
        ModelAndView response = new ModelAndView("login/login");         // Return value from signup.jsp
        return response;
    }
}
