package org.youcode.CITRONIX.app.Mappers.treeHarvest;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.NestedTreeHarvestDTO;
import org.youcode.CITRONIX.core.entities.TreeHarvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface TreeHarvestEntityToNestedTreeHarvestDTOMapper extends BaseMapper<TreeHarvest , NestedTreeHarvestDTO> {
}
