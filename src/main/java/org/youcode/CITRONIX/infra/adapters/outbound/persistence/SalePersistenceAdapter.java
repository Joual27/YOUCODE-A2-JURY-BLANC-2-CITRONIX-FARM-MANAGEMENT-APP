package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.SaleDAO;
import org.youcode.CITRONIX.core.entities.Sale;

@Repository
public interface SalePersistenceAdapter extends SaleDAO , JpaRepository<Sale , Long> {
}
