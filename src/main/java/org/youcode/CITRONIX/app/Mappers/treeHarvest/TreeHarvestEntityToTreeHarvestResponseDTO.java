package org.youcode.CITRONIX.app.Mappers.treeHarvest;


import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.TreeHarvestResponseDTO;
import org.youcode.CITRONIX.core.entities.TreeHarvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface TreeHarvestEntityToTreeHarvestResponseDTO extends BaseMapper<TreeHarvest , TreeHarvestResponseDTO> {
}
