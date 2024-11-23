package org.youcode.CITRONIX.app.exceptions;

public class TreeDoesntBelongToFieldException extends RuntimeException {
    public TreeDoesntBelongToFieldException(String message) {
        super(message);
    }
}
