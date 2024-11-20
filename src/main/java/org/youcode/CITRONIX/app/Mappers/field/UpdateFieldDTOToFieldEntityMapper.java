package org.youcode.CITRONIX.app.Mappers.field;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.field.UpdateFieldDTO;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface UpdateFieldDTOToFieldEntityMapper extends BaseMapper<Field , UpdateFieldDTO> {
}
