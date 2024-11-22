package org.youcode.CITRONIX.app.DTOs.treeHarvest;

import org.youcode.CITRONIX.app.DTOs.harvest.NestedHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.tree.NestedTreeDTO;

public record TreeHarvestResponseDTO(NestedTreeDTO tree , NestedHarvestDTO harvest , double quantity) {
}
