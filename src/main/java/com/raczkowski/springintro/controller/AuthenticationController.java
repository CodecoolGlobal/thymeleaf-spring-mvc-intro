package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.dto.CredentialsDto;
import com.raczkowski.springintro.model.User;
import com.raczkowski.springintro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void processLoginAttempt(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @ModelAttribute("credentialsDto") CredentialsDto credentialsDto) throws IOException {
        Optional<User> maybeUser = userService.login(credentialsDto);
        if (maybeUser.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", maybeUser.get().getName());
            response.sendRedirect(request.getContextPath() + "/users");
        } else {
            response.sendRedirect(request.getContextPath() + "/loginForm");
        }
    }

    @GetMapping("/loginForm")
    public String getLoginForm(Model model) {
        model.addAttribute("credentialsForm", new CredentialsDto());
        return "login";
    }

    @GetMapping("/logout")
    public void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/loginForm");
    }

}
