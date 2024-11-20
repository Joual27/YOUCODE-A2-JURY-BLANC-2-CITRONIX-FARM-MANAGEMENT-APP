package org.youcode.CITRONIX.app.Mappers.farm;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface CreateFarmDTOToFarmEntityResponseDTOMapper extends BaseMapper<Farm , CreateFarmDTO> {
}
