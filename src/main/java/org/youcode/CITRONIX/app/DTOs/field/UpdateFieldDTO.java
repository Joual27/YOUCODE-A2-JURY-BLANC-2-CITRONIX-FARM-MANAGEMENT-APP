package org.youcode.CITRONIX.app.DTOs.field;

import jakarta.validation.constraints.Min;

public record UpdateFieldDTO(@Min(1000) double surface) {
}