package org.youcode.CITRONIX.app.ports.outbound;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.shared.interfaces.GenericDao;

public interface FieldDAO extends GenericDao<Field , Long> {

    @Query("SELECT COUNT(*) FROM Field f where f.farm = :farm")
    int getNumberOfFieldsPerFarm(@Param("farm") Farm farm);

    @Query("SELECT SUM(f.surface) FROM Field f WHERE f.farm = :farm")
    Double getOverallFieldsSurfacePerFarm(@Param("farm") Farm farm);
}
