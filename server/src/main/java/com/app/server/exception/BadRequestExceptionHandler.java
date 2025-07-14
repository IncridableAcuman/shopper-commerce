package com.app.server.exception;

public class BadRequestExceptionHandler extends IllegalArgumentException {
    public BadRequestExceptionHandler(String message){
        super(message);
    }
}
