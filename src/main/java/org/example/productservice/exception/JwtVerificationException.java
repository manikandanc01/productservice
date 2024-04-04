package org.example.productservice.exception;

public class JwtVerificationException extends Exception {

    public JwtVerificationException(String message) {
        super(message);
    }
}
