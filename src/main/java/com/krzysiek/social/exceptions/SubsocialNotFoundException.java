package com.krzysiek.social.exceptions;

public class SubsocialNotFoundException extends RuntimeException {
    public SubsocialNotFoundException(String message) {
        super(message);
    }
}