package org.youcode.CITRONIX.app.DTOs.harvest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateHarvestDTO(@NotNull @Min(2000) @Max(2030) int year , @NotNull String season) {
}
