package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.TreeHarvestDAO;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.core.entities.TreeHarvest;

@Repository
public interface TreeHarvestPersistenceAdapter extends TreeHarvestDAO , JpaRepository<TreeHarvest , TreeHarvestKey> {

    @Query("SELECT COUNT(*) > 0 FROM TreeHarvest th WHERE th.harvest = :harvest AND th.tree = :tree")
    boolean treeAlreadyHarvested(@Param("harvest") Harvest harvest ,@Param("tree") Tree tree);
}
