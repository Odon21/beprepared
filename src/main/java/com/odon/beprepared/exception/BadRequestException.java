package com.odon.beprepared.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String messagem){
        super(messagem);
    }
}
