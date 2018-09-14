package com.swarup.tameofthrones.exceptions;

public class InvalidMessageException extends RuntimeException {
    public InvalidMessageException() {
        super();
    }

    public InvalidMessageException(String message) {
        super(message);
    }
}
