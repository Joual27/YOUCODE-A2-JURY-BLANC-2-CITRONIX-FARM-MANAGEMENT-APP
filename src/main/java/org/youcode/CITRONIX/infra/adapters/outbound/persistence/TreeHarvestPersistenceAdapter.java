package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.TreeHarvestDAO;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.core.entities.TreeHarvest;

@Repository
public interface TreeHarvestPersistenceAdapter extends TreeHarvestDAO , JpaRepository<TreeHarvest , TreeHarvestKey> {
}
