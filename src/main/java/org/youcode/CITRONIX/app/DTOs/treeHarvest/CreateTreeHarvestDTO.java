package org.youcode.CITRONIX.app.DTOs.treeHarvest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTreeHarvestDTO(@NotNull Long treeId , @NotNull Long harvestId ,@NotNull @Positive double quantity){
}
