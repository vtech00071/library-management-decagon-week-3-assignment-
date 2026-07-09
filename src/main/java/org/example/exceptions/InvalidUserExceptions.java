package org.example.exceptions;
//the reason why we use exceptions because of response so that the user can see it
public class InvalidUserExceptions extends RuntimeException {
    public InvalidUserExceptions(String message) {
        super( message);
    }
}
