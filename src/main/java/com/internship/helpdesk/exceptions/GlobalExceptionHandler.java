package com.internship.helpdesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.internship.helpdesk.exception.UserNotFoundException.class)
    public ResponseEntity<com.internship.helpdesk.exception.ErrorResponse> handleUserNotFoundException(
            com.internship.helpdesk.exception.UserNotFoundException ex) {

        com.internship.helpdesk.exception.ErrorResponse error = new com.internship.helpdesk.exception.ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.internship.helpdesk.exception.DepartmentNotFoundException.class)
    public ResponseEntity<com.internship.helpdesk.exception.ErrorResponse> handleDepartmentNotFoundException(
            com.internship.helpdesk.exception.DepartmentNotFoundException ex) {

        com.internship.helpdesk.exception.ErrorResponse error = new com.internship.helpdesk.exception.ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.internship.helpdesk.exception.EmailAlreadyExistsException.class)
    public ResponseEntity<com.internship.helpdesk.exception.ErrorResponse> handleEmailAlreadyExistsException(
            com.internship.helpdesk.exception.EmailAlreadyExistsException ex) {

        com.internship.helpdesk.exception.ErrorResponse error = new com.internship.helpdesk.exception.ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}