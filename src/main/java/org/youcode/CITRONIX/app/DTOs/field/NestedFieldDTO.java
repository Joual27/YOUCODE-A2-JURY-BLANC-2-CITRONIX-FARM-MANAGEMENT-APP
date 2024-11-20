package org.youcode.CITRONIX.app.DTOs.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.CITRONIX.app.DTOs.tree.NestedTreeDTO;

import java.util.List;

public record NestedFieldDTO(double surface , @JsonInclude(JsonInclude.Include.NON_NULL) List<NestedTreeDTO> trees) {
}
