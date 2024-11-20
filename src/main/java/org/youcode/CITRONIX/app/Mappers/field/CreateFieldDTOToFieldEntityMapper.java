package org.youcode.CITRONIX.app.Mappers.field;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface CreateFieldDTOToFieldEntityMapper extends BaseMapper<Field , CreateFieldDTO> {

    @Mapping(source = "dto.farmId" , target = "farm.id")
    Field toEntity(CreateFieldDTO dto);
}
