package org.youcode.CITRONIX.infra.adapters.outbound.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.CITRONIX.app.ports.outbound.FarmDAO;
import org.youcode.CITRONIX.core.entities.Farm;

@Repository
public interface FarmPersistenceAdapter extends FarmDAO , JpaRepository<Farm , Long> {
}
