package org.youcode.CITRONIX.app.Mappers.field;


import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.Mappers.farm.FarmEntityToNestedFarmDTOMapper;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring" , uses = FarmEntityToNestedFarmDTOMapper.class)
public interface FieldEntityToFieldResponseDTOMapper extends BaseMapper<Field , FieldResponseDTO> {
}
