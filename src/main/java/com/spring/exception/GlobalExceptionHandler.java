package com.spring.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception exp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        exp.getMessage()
        );
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handelEntityNotFound(EntityNotFoundException exp){
        return new ResponseEntity<>(
                exp.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgument(MethodArgumentNotValidException exp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        exp.getMessage()
                );
    }
}
