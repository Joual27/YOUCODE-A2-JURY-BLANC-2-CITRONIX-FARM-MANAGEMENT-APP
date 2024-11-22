package org.youcode.CITRONIX.app.ports.outbound;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.CITRONIX.core.entities.Harvest;

import org.youcode.CITRONIX.core.enums.Season;
import org.youcode.CITRONIX.shared.interfaces.GenericDao;

public interface HarvestDAO extends GenericDao<Harvest , Long> {

    @Query("SELECT COUNT(*) > 0 FROM Harvest h WHERE h.year = :year AND h.field.id = :fieldId AND h.season = :season")
    boolean existsByYearAndSeasonAndField(@Param("year") int year, @Param("season") String season, @Param("fieldId") Long fieldId);
}
