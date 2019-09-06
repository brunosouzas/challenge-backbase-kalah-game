package com.brunosouzas.kalah.domain.exception;

public class Exception {
    private String message;

    public Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
