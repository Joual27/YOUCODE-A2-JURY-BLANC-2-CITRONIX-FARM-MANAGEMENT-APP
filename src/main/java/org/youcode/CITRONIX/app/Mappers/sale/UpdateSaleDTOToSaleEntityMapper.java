package org.youcode.CITRONIX.app.Mappers.sale;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.sale.UpdateSaleDTO;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface UpdateSaleDTOToSaleEntityMapper extends BaseMapper<Sale , UpdateSaleDTO> {
}
