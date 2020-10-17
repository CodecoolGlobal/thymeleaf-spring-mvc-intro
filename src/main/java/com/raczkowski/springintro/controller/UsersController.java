package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.dto.UserDto;
import com.raczkowski.springintro.model.User;
import com.raczkowski.springintro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService usersService;

    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "allUsers";
    }

    @PostMapping
    public String addUser(@ModelAttribute UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getAddress(), "");
        usersService.addUser(user);

        return "redirect:users";
    }

    @GetMapping("/add")
    public String getAddUsersForm(Model model) {
        model.addAttribute("user", new UserDto());

        return "addUsersForm";
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        usersService.deleteUser(parseInt(id));
        response.sendRedirect(request.getContextPath() + "/users");
    }

}
