package org.youcode.CITRONIX.app.Mappers.sale;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.CITRONIX.app.DTOs.sale.CreateSaleDTO;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface CreateSaleDTOToSaleEntityMapper extends BaseMapper<Sale , CreateSaleDTO> {

    @Override
    @Mapping(target = "harvest.id" , source = "harvestId")
    Sale toEntity(CreateSaleDTO createSaleDTO);
}
