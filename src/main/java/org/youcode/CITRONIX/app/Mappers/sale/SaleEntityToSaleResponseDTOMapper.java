package org.youcode.CITRONIX.app.Mappers.sale;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseDTO;
import org.youcode.CITRONIX.app.Mappers.harvest.HarvestEntityToNestedHarvestDTOMapper;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring" , uses = HarvestEntityToNestedHarvestDTOMapper.class)
public interface SaleEntityToSaleResponseDTOMapper extends BaseMapper<Sale , SaleResponseDTO> {
}
