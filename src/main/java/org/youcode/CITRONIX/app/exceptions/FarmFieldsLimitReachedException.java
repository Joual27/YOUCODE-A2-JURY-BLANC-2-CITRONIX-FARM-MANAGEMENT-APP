package org.youcode.CITRONIX.app.exceptions;

public class FarmFieldsLimitReachedException extends RuntimeException {
    public FarmFieldsLimitReachedException(String message) {
        super(message);
    }
}
