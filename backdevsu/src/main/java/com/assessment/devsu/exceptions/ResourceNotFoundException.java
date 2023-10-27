package com.assessment.devsu.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
