package com.teachmint.exam.app.handler;

import com.teachmint.exam.domain.dto.response.ResponseDto;
import com.teachmint.exam.service.exception.EntityNotFoundException;
import com.teachmint.exam.service.exception.InvalidPayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle(EntityNotFoundException exc){
        return new ResponseEntity(
                ResponseDto.builder()
                        .message(exc.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity handle(InvalidPayloadException exc){
        return new ResponseEntity(
                ResponseDto.builder()
                        .message(exc.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
