package com.xavier.mozprops_api.service.exception;

public class PropertyTypeAlreadyExistsException extends RuntimeException {
    public PropertyTypeAlreadyExistsException(String message) {
        super(message);
    }

}
