package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.TreeDAO;
import org.youcode.CITRONIX.core.entities.Tree;

@Repository
public interface TreePersistenceAdapter extends TreeDAO , JpaRepository<Tree, Long> {
}
