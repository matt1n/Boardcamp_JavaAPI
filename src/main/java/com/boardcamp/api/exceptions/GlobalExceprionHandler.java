package com.boardcamp.api.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceprionHandler {
    @ExceptionHandler({GameConflictException.class})
    public ResponseEntity<Object> handleGameConflict(GameConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({CustomerConflictException.class})
    public ResponseEntity<Object> handleCustomerConflict(CustomerConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());     
    }

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<Object> handleGameNotFound(GameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());     
    }

}
