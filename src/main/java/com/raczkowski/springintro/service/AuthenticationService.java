package com.raczkowski.springintro.service;

import com.raczkowski.springintro.dao.UserDao;
import com.raczkowski.springintro.dto.CredentialsDto;
import com.raczkowski.springintro.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> login(CredentialsDto credentialsDto) {
        return userDao.getUsers().stream()
                .filter(user -> user.getName().equals(credentialsDto.getUsername())
                        && user.getPassword().equals(credentialsDto.getPassword()))
                .findAny();
    }
}
