package org.youcode.CITRONIX.app.DTOs.tree;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTreeDTO(@NotNull LocalDate plantingDate , @NotNull Long fieldId) {
}
