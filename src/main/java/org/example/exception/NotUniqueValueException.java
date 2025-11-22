package org.example.exception;

public class NotUniqueValueException extends DBException {
    public NotUniqueValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
