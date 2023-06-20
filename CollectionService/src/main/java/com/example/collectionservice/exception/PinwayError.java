package com.example.collectionservice.exception;

public class PinwayError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PinwayError(String msg) {
        super(msg);
    }
}