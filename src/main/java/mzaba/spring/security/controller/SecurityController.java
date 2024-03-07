package mzaba.spring.security.controller;


import mzaba.spring.security.service.LoginAttemptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static mzaba.spring.security.handler.CustomAuthenticationFailureHandler.LAST_USERNAME_KEY;


@Controller
public class SecurityController {

    private LoginAttemptService loginAttemptService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/login")
    public String login(@RequestParam (value = "error", defaultValue = "false") boolean loginError,
                        final ModelMap model, HttpSession session){
        var username = getUsername(session);
        if (loginError && username != null && loginAttemptService.isBlocked(username)) {
            model.addAttribute("accountLocked", Boolean.TRUE);
        }
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "logout";
    }

    final String getUsername(HttpSession session) {
        final String username = (String) session.getAttribute(LAST_USERNAME_KEY);
        if(username != null &&  !username.isEmpty()) {
            session.removeAttribute(LAST_USERNAME_KEY);
        }
        return username;
    }
}
