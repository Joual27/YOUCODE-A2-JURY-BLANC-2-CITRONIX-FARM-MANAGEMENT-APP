package org.youcode.CITRONIX.app.ports.outbound;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.CITRONIX.core.entities.Sale;
import org.youcode.CITRONIX.shared.interfaces.GenericDao;

public interface SaleDAO extends GenericDao<Sale , Long> {

    @Query("SELECT SUM(s.quantity) FROM Sale s WHERE s.harvest.id = :harvestId ")
    Double getSoldQuantityByHarvest(@Param("harvestId") Long harvestId);
}
