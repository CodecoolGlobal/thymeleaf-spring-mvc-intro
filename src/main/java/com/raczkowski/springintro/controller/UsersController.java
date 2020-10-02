package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.dto.UserDto;
import com.raczkowski.springintro.model.User;
import com.raczkowski.springintro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public String addUser(@ModelAttribute UserDto userDto, Model model) {
//        User user = new User(userDto.getName(), userDto.getAddress());
//        usersService.addUser(user);
//
//        return "redirect:users";
//    }

    @GetMapping("/add")
    public String getAddUsersForm(Model model) {
        model.addAttribute("user", new UserDto());

        return "addUsersForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        return "redirect:users";
    }

}
