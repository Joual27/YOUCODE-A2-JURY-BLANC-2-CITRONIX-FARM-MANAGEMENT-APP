package org.youcode.CITRONIX.app.DTOs.sale;

import jakarta.validation.constraints.NotNull;

public record CreateSaleDTO(@NotNull double quantity , @NotNull Double unitPrice ,@NotNull String clientName , @NotNull Long harvestId) {
}
