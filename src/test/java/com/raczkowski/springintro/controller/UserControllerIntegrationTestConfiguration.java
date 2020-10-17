package com.raczkowski.springintro.controller;

import com.raczkowski.springintro.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class UserControllerIntegrationTestConfiguration {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public UsersController usersController() {
        return new UsersController(userService());
    }

}
