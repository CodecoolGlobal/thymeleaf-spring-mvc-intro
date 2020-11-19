package com.raczkowski.springintro.backdoor.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order not found for given id: " + id);
    }
}
