package org.youcode.CITRONIX.app.DTOs.harvest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.youcode.CITRONIX.core.enums.Season;
import org.youcode.CITRONIX.shared.utils.validators.interfaces.IsValidEnum;

public record CreateHarvestDTO(@NotNull @Min(2000) @Max(2030) int year , @NotNull @IsValidEnum(enumClass = Season.class) Season season ,@NotNull Long fieldId) {
}
