package org.youcode.CITRONIX.app.Mappers.harvest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.CITRONIX.app.DTOs.harvest.CreateHarvestDTO;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface CreateHarvestDTOToHarvestEntityMapper extends BaseMapper<Harvest , CreateHarvestDTO> {

    @Mapping(target = "field.id" , source = "fieldId")
    Harvest toEntity(CreateHarvestDTO dto);
}
