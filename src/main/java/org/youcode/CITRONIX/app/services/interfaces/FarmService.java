package org.youcode.CITRONIX.app.services.interfaces;

import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;

public interface FarmService {
    FarmResponseDTO save(CreateFarmDTO data);
}
