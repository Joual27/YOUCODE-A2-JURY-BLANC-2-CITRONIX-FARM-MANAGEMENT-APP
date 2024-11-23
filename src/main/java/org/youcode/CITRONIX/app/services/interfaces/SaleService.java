package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.sale.CreateSaleDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseWithOverallPriceDTO;
import org.youcode.CITRONIX.app.DTOs.sale.UpdateSaleDTO;

public interface SaleService {
    SaleResponseDTO save(CreateSaleDTO data);
    SaleResponseDTO update(UpdateSaleDTO data , Long id);
    Page<SaleResponseDTO> getAllSales(Pageable pageable);
    SaleResponseDTO getById(Long id);
    SaleResponseDTO delete(Long id);
    SaleResponseWithOverallPriceDTO getSaleWithItsOverallPrice(Long id);
}
