package org.youcode.CITRONIX.app.exceptions;

public class FarmFieldsLimitReached extends RuntimeException {
    public FarmFieldsLimitReached(String message) {
        super(message);
    }
}
