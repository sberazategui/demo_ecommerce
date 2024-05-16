package com.ecommerce.demo.exception;

public class FareNotFoundException extends RuntimeException {

    public FareNotFoundException() {
        super();
    }

    public FareNotFoundException(String message) {
        super(message);
    }

    public FareNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FareNotFoundException(Throwable cause) {
        super(cause);
    }
}

