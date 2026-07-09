package org.example.exceptions;

public class GenreNotFoundRuntimeException extends RuntimeException {
    public GenreNotFoundRuntimeException(String message) {
        //this is the constructor that you inherited from the child class thats when you use this super keyword
        super(message);
    }
}
