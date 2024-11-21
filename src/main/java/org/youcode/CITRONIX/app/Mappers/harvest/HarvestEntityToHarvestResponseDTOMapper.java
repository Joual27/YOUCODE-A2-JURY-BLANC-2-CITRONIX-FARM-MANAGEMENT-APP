package org.youcode.CITRONIX.app.Mappers.harvest;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.harvest.HarvestResponseDTO;
import org.youcode.CITRONIX.app.Mappers.field.FieldEntityToNestedFieldDTOMapper;
import org.youcode.CITRONIX.app.Mappers.treeHarvest.TreeHarvestEntityToNestedTreeHarvestDTOMapper;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring" , uses = {FieldEntityToNestedFieldDTOMapper.class , TreeHarvestEntityToNestedTreeHarvestDTOMapper.class})
public interface HarvestEntityToHarvestResponseDTOMapper extends BaseMapper<Harvest , HarvestResponseDTO> {
}
