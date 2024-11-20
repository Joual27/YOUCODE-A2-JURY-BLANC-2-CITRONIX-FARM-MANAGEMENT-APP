package org.youcode.CITRONIX.app.DTOs.farm;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.utils.validators.interfaces.Unique;

public record CreateFarmDTO(@NotNull @Unique(entity = Farm.class , field = "name") String name , @NotNull String location , @Min(2000) double surface) {
}
