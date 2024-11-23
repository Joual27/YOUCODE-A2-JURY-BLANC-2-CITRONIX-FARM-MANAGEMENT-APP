package org.youcode.CITRONIX.app.DTOs.sale;

import org.youcode.CITRONIX.app.DTOs.harvest.NestedHarvestDTO;

public record SaleResponseWithOverallPriceDTO(Long id , double quantity , Double unitPrice , String clientName , NestedHarvestDTO harvest , double overallPrice) {
}
