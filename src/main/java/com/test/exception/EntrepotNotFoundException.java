package com.test.exception;

public class EntrepotNotFoundException extends RuntimeException{
    public EntrepotNotFoundException(String message){
        super(message);
    }
}
