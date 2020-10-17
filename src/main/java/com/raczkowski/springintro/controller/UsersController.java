package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.dto.UserDto;
import com.raczkowski.springintro.entity.User;
import com.raczkowski.springintro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;


@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService usersService;

    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users",
                usersService.getAllUsers().stream()
                        .map(this::convertToUserDto)
                        .collect(toList()));

        return "allUsers";
    }

    @PostMapping
    public String addUser(@ModelAttribute UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getAddress());
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
        usersService.deleteUser(parseLong(id));
        response.sendRedirect(request.getContextPath() + "/users");
    }

    private UserDto convertToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getAddress());
    }

}
