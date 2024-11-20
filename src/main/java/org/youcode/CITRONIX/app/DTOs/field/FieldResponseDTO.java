package org.youcode.CITRONIX.app.DTOs.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.CITRONIX.app.DTOs.farm.NestedFarmDTO;
import org.youcode.CITRONIX.app.DTOs.tree.NestedTreeDTO;

import java.util.List;

public record FieldResponseDTO(Long id , double surface , NestedFarmDTO farm , @JsonInclude(JsonInclude.Include.NON_NULL) List<NestedTreeDTO> trees) {
}
