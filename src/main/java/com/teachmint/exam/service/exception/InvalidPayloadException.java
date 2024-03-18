package com.teachmint.exam.service.exception;

public class InvalidPayloadException extends RuntimeException{
    public InvalidPayloadException(String message) {
        super(message);
    }
}
