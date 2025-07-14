package com.app.server.exception;

public class NotFoundExceptionHandler extends IllegalArgumentException{

    public  NotFoundExceptionHandler (String message){
        super(message);
    }
}
