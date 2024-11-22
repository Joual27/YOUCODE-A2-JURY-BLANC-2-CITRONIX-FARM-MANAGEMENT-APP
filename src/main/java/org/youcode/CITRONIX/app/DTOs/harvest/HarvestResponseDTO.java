package org.youcode.CITRONIX.app.DTOs.harvest;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.youcode.CITRONIX.app.DTOs.field.NestedFieldDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.NestedTreeHarvestDTO;
import org.youcode.CITRONIX.core.enums.Season;
import java.util.List;

public record HarvestResponseDTO(Long id , int year , Season season , NestedFieldDTO field , @JsonInclude(JsonInclude.Include.NON_NULL) List<NestedTreeHarvestDTO> treeHarvests) {
}
