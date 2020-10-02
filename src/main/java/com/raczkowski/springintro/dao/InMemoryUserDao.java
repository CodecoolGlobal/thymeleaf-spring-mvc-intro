package com.raczkowski.springintro.dao;

import com.raczkowski.springintro.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryUserDao implements UserDao {
    private final List<User> users = Stream.of(
            new User(1, "praczkowski@gmail.com", "Kraków, ul. Kijowska 54", "1234"),
            new User(2, "tomasz.hajto@herta.de", "Berlin, Herta StraBe", "chrysler"))
            .collect(Collectors.toList());

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
