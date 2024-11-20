package org.youcode.CITRONIX.infra.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.FieldDAO;
import org.youcode.CITRONIX.core.entities.Field;

@Repository
public interface FieldPersistenceAdapter extends FieldDAO, JpaRepository<Field , Long> {
}
