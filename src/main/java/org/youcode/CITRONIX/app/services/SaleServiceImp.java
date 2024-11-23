package org.youcode.CITRONIX.app.services;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.sale.CreateSaleDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseWithOverallPriceDTO;
import org.youcode.CITRONIX.app.DTOs.sale.UpdateSaleDTO;
import org.youcode.CITRONIX.app.Mappers.harvest.HarvestEntityToNestedHarvestDTOMapper;
import org.youcode.CITRONIX.app.Mappers.sale.CreateSaleDTOToSaleEntityMapper;
import org.youcode.CITRONIX.app.Mappers.sale.SaleEntityToSaleResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.sale.UpdateSaleDTOToSaleEntityMapper;
import org.youcode.CITRONIX.app.exceptions.AvailableSaleQuantityExceededException;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.services.interfaces.HarvestService;
import org.youcode.CITRONIX.app.services.interfaces.SaleService;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.SalePersistenceAdapter;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceImp implements SaleService {

    private final SalePersistenceAdapter salePersistenceAdapter;
    private final HarvestService harvestService;
    private final CreateSaleDTOToSaleEntityMapper createSaleDTOToSaleEntityMapper;
    private final UpdateSaleDTOToSaleEntityMapper updateSaleDTOToSaleEntityMapper;
    private final SaleEntityToSaleResponseDTOMapper saleEntityToSaleResponseDTOMapper;
    private final HarvestEntityToNestedHarvestDTOMapper harvestEntityToNestedHarvestDTOMapper;

    public SaleResponseDTO save(CreateSaleDTO data){
        Harvest existingHarvest = harvestService.getEntityById(data.harvestId());
        if (data.quantity() > getAvailableQuantityToSellByHarvest(existingHarvest)){
            throw new AvailableSaleQuantityExceededException("The available sale quantity for this harvest is : " + getAvailableQuantityToSellByHarvest(existingHarvest));
        }
        Sale saleToCreate = createSaleDTOToSaleEntityMapper.toEntity(data);
        Sale createdSale = salePersistenceAdapter.save(saleToCreate);
        createdSale.setHarvest(existingHarvest);
        return saleEntityToSaleResponseDTOMapper.entityToDto(saleToCreate);
    }

    @Override
    public SaleResponseDTO update(UpdateSaleDTO data , Long id){
        Sale existingSale = salePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Sale found with given ID !"));

        if (data.quantity() > (getAvailableQuantityToSellByHarvest(existingSale.getHarvest())) + existingSale.getQuantity()){
            throw new AvailableSaleQuantityExceededException("The available sale quantity for this harvest is : " + (getAvailableQuantityToSellByHarvest(existingSale.getHarvest()) + existingSale.getQuantity()));
        }
        Sale saleToUpdate = updateSaleDTOToSaleEntityMapper.toEntity(data);
        saleToUpdate.setId(id);
        saleToUpdate.setHarvest(existingSale.getHarvest());
        Sale updatedSale = salePersistenceAdapter.save(saleToUpdate);
        return saleEntityToSaleResponseDTOMapper.entityToDto(saleToUpdate);
    }

    @Override
    public Page<SaleResponseDTO> getAllSales(Pageable pageable){
        Page<Sale> sales = salePersistenceAdapter.findAll(pageable);
        return sales.map(saleEntityToSaleResponseDTOMapper::entityToDto);
    }


    @Override
    public SaleResponseDTO getById(Long id){
        Sale existingSale = salePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Sale found with given ID !"));
        return saleEntityToSaleResponseDTOMapper.entityToDto(existingSale);
    }

    @Override
    public SaleResponseDTO delete(Long id){
        Sale existingSale = salePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Sale found with given ID !"));
        salePersistenceAdapter.deleteById(id);
        return saleEntityToSaleResponseDTOMapper.entityToDto(existingSale);
    }

    @Override
    public SaleResponseWithOverallPriceDTO getSaleWithItsOverallPrice(Long id){
        Sale existingSale = salePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Sale found with given ID !"));
        double overallPrice = existingSale.getUnitPrice() * existingSale.getQuantity();
        return new SaleResponseWithOverallPriceDTO(existingSale.getId() , existingSale.getQuantity() , existingSale.getUnitPrice() , existingSale.getClientName() , harvestEntityToNestedHarvestDTOMapper.entityToDto(existingSale.getHarvest()) , overallPrice);
    }

    private double getAvailableQuantityToSellByHarvest(Harvest harvest){
        Double soldQuantity = salePersistenceAdapter.getSoldQuantityByHarvest(harvest.getId());
        if (soldQuantity == null){
            soldQuantity = 0.0;
        }
        return harvest.getQuantity() - soldQuantity;
    }


}

