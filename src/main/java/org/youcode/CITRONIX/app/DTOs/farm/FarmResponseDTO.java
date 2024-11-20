package org.youcode.CITRONIX.app.DTOs.farm;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.CITRONIX.core.entities.Field;

import java.time.LocalDateTime;
import java.util.List;

public record FarmResponseDTO(Long id , String name , String location , LocalDateTime createdAt , double surface , @JsonInclude(JsonInclude.Include.NON_NULL) List<Field> fields) {
}
