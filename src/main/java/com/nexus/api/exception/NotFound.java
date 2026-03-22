package com.nexus.api.exception;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
