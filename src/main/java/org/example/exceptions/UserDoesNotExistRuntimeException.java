package org.example.exceptions;

public class UserDoesNotExistRuntimeException extends RuntimeException{
    public UserDoesNotExistRuntimeException(String message){
        super(message);
    }
}
