package com.assessment.devsu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnprocessableMovimiento extends RuntimeException{
    public UnprocessableMovimiento(String message) {
        super(message);
    }
}
