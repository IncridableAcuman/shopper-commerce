package com.app.server.exception;

public class ServerErrorExceptionHandler extends IllegalArgumentException{
    public ServerErrorExceptionHandler(String message){
        super(message);
    }
}
