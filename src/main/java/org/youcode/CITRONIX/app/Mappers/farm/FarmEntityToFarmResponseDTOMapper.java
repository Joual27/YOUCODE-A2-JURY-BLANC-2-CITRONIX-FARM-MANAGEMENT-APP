package org.youcode.CITRONIX.app.Mappers.farm;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.Mappers.field.FieldEntityToNestedFieldDTOMapper;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring" , uses = FieldEntityToNestedFieldDTOMapper.class)
public interface FarmEntityToFarmResponseDTOMapper extends BaseMapper<Farm , FarmResponseDTO> {
}
