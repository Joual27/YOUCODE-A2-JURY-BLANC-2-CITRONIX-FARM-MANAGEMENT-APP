package org.youcode.CITRONIX.app.ports.outbound;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.interfaces.GenericDao;

public interface FarmDAO extends GenericDao<Farm , Long> , JpaSpecificationExecutor<Farm> {
    @Query("SELECT CASE WHEN COUNT(F) > 0 THEN TRUE ELSE FALSE END FROM Farm F WHERE F.name = :name AND F.id != :id")
    public boolean existsByNameNotId(@Param("name") String name , @Param("id") Long id);
}
