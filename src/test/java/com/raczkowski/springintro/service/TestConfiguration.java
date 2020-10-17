package com.raczkowski.springintro.service;

import com.raczkowski.springintro.controller.UsersController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class TestConfiguration {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public UsersController usersController() {
        return new UsersController(userService());
    }

}
