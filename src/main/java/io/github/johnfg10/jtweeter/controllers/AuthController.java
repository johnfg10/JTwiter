package io.github.johnfg10.jtweeter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsManager userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){
        return "/auth/register";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam("username") String username, @RequestParam("password") String password){
        userDetailsService.createUser(User.builder().username(username).password(passwordEncoder.encode(password)).roles("USER").build());
        return "redirect:/login";
    }

    //todo remove login stuff from here and stick it in auth
    @RequestMapping({"/login", "/login.html"})
    public String loginHtml() {
        return "/auth/login.html";
    }

    @RequestMapping({"/logout"})
    public String logoutHtml() {
        return "/auth/logout";
    }

    @RequestMapping({"/login-error", "/login-error.html"})
    public String loginHtmlError(Model model) {
        model.addAttribute("loginError", true);
        return "/auth/login.html";
    }
}
