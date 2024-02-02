package com.boardcamp.api.exceptions;

public class GameBadRequestException extends RuntimeException{
    public GameBadRequestException(String message) {
        super(message);
    }
}
