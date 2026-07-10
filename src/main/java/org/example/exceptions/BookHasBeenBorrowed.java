package org.example.exceptions;

public class BookHasBeenBorrowed extends RuntimeException {
    public BookHasBeenBorrowed(String message) {
        super(message);
    }
}
