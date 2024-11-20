package org.youcode.CITRONIX.app.Mappers.field;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.field.NestedFieldDTO;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface FieldEntityToNestedFieldDTOMapper extends BaseMapper<Field , NestedFieldDTO> {
}
