package com.raczkowski.springintro.service;

import com.raczkowski.springintro.UserNotFoundException;
import com.raczkowski.springintro.entity.User;
import com.raczkowski.springintro.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    // dummy methods not used in app, just for testing purposes
    public List<User> getUsersThatNamesStartsFrom(String prefix) {
        List<User> users = (List<User>) userRepository.findAll();
        List<User> matchedUsers = new ArrayList<>();

        for (User user : users) {
            if (user.getName().startsWith(prefix))
                matchedUsers.add(user);
        }

        return matchedUsers;
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }
}
