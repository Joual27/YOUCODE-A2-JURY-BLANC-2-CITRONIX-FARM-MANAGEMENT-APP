package org.youcode.CITRONIX.app.DTOs.tree;

import org.youcode.CITRONIX.app.DTOs.field.NestedFieldDTO;

import java.time.LocalDate;

public record TreeResponseDTO(Long id , LocalDate plantingDate , NestedFieldDTO field) {
}
