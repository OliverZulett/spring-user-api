package com.milankas.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidParamException extends Exception{

    public InvalidParamException(String errorMessage) {
        super(errorMessage);
    }

}
