package org.youcode.CITRONIX.shared.utils.DTOs;

public record SuccessDTO<T>(String status , String message , T data) {
}
