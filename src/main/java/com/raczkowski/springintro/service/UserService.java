package com.raczkowski.springintro.service;

import com.raczkowski.springintro.dao.UserDao;
import com.raczkowski.springintro.dto.CredentialsDto;
import com.raczkowski.springintro.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    public Optional<User> getUser(int id) {
        return userDao.getUsers().stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public Optional<User> login(CredentialsDto credentialsDto) {
        return userDao.getUsers().stream()
                .filter(user -> user.getName().equals(credentialsDto.getUsername())
                        && user.getPassword().equals(credentialsDto.getPassword()))
                .findAny();
    }

}
