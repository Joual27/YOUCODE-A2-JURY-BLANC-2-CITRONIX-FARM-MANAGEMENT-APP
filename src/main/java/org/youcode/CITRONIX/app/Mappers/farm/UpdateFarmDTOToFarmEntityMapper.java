package org.youcode.CITRONIX.app.Mappers.farm;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.farm.UpdateFarmDTO;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface UpdateFarmDTOToFarmEntityMapper extends BaseMapper<Farm , UpdateFarmDTO> {
}
