package org.youcode.CITRONIX.app.Mappers.farm;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.farm.NestedFarmDTO;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface FarmEntityToNestedFarmDTOMapper extends BaseMapper<Farm , NestedFarmDTO> {
}
