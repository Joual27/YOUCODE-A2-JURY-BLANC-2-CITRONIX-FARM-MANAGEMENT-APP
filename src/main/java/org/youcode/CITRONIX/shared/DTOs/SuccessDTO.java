package org.youcode.CITRONIX.shared.DTOs;

public record SuccessDTO<T>(String status , String message , T data) {
}
