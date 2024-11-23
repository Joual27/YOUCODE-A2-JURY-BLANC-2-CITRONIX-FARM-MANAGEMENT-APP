package org.youcode.CITRONIX.app.Mappers.sale;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.sale.NestedSaleDTO;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;


@Mapper(componentModel = "spring")
public interface SaleEntityToNestedSaleDTOMapper extends BaseMapper<Sale, NestedSaleDTO> {
}
