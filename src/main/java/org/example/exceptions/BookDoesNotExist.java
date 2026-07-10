package org.example.exceptions;

public class BookDoesNotExist extends RuntimeException{
    public BookDoesNotExist(String message ){
        super(message);
    }
}
