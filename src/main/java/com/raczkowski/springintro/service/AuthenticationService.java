package com.raczkowski.springintro.service;

import com.raczkowski.springintro.dto.CredentialsDto;
import com.raczkowski.springintro.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    public Optional<User> login(CredentialsDto credentialsDto) {
        return Optional.empty();
    }
}
