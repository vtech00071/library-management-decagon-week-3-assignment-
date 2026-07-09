package org.example.exceptions;

//runtime exception is an unchecked exception that means the programmer is not forced to handle it
public class InvalidPasswordRuntimeException extends  RuntimeException {
    public InvalidPasswordRuntimeException(String message){
        super(message);
    }
}
