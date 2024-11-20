package org.youcode.CITRONIX.app.DTOs.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateFieldDTO (@NotNull @Min(1000) double surface , @NotNull Long farmId){
}
