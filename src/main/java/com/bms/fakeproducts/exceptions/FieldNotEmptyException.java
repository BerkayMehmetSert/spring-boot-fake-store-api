package com.bms.fakeproducts.exceptions;

public class FieldNotEmptyException extends RuntimeException {
    public FieldNotEmptyException(String message) {
        super(message);
    }
}
