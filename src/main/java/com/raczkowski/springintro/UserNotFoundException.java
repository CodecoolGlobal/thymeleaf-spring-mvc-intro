package com.raczkowski.springintro;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User nor found for given id: " + id);
    }
}
