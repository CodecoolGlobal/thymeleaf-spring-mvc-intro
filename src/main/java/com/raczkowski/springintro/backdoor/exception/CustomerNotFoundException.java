package com.raczkowski.springintro.backdoor.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("User not found for given id: " + id);
    }
}
