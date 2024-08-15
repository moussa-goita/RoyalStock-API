package com.test.exception;

public class MotifNotFoundException extends RuntimeException {
    public MotifNotFoundException(String message) {
        super(message);
    }
}
