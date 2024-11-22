package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.CITRONIX.app.ports.outbound.HarvestDAO;
import org.youcode.CITRONIX.core.entities.Harvest;

public interface HarvestPersistenceAdapter extends HarvestDAO , JpaRepository<Harvest , Long> {
}
