package org.youcode.CITRONIX.app.DTOs.farm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record UpdateFarmDTO(@NotNull String name , @NotNull String location , @Min(2000) double surface) {
}
