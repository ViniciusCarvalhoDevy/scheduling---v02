package com.beautysalon.scheduling.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.beautysalon.scheduling.model.error.ErrorMessages;
import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.model.exception.ResourceInternalErrorException;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExeceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessages error = new ErrorMessages("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<?> handleResourceBadRequestException(ResourceBadRequestException ex){
        ErrorMessages error = new ErrorMessages("Bad Request", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceInternalErrorException.class)
    public ResponseEntity<?> handleResourceBadRequestException(ResourceInternalErrorException ex){
        ErrorMessages error = new ErrorMessages("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
