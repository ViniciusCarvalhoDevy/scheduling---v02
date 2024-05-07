package com.beautysalon.scheduling.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceInternalErrorException extends RuntimeException {
    public ResourceInternalErrorException(String message){
        super(message);
    }
}
