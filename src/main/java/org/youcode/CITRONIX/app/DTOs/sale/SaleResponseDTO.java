package org.youcode.CITRONIX.app.DTOs.sale;

import org.youcode.CITRONIX.app.DTOs.harvest.NestedHarvestDTO;

public record SaleResponseDTO(Long id , double quantity , double unitPrice , String clientName , NestedHarvestDTO harvest) {
}
