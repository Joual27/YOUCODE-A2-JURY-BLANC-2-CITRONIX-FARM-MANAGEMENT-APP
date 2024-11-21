package org.youcode.CITRONIX.app.DTOs.harvest;

import org.youcode.CITRONIX.app.DTOs.field.NestedFieldDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.NestedTreeHarvestDTO;
import org.youcode.CITRONIX.core.enums.Season;
import java.util.List;

public record HarvestResponseDTO(Long id , int year , Season season , NestedFieldDTO field , List<NestedTreeHarvestDTO> treeHarvests) {
}
