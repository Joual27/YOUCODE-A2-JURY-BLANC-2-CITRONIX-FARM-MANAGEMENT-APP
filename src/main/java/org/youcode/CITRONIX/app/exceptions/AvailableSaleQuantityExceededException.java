package org.youcode.CITRONIX.app.exceptions;

public class AvailableSaleQuantityExceededException extends RuntimeException {
    public AvailableSaleQuantityExceededException(String message) {
        super(message);
    }
}
