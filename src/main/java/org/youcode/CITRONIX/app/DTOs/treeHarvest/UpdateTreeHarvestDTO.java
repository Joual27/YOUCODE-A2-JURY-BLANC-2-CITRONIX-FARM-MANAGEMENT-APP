package org.youcode.CITRONIX.app.DTOs.treeHarvest;

import jakarta.validation.constraints.NotNull;

public record UpdateTreeHarvestDTO(@NotNull double quantity) {
}
