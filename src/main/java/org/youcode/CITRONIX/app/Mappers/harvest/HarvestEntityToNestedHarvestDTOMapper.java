package org.youcode.CITRONIX.app.Mappers.harvest;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.harvest.NestedHarvestDTO;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface HarvestEntityToNestedHarvestDTOMapper extends BaseMapper<Harvest , NestedHarvestDTO> {
}
