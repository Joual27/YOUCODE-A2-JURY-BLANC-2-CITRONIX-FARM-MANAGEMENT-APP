package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.DTOs.farm.UpdateFarmDTO;
import org.youcode.CITRONIX.core.entities.Farm;

import java.util.List;

public interface FarmService {
    FarmResponseDTO save(CreateFarmDTO data);
    FarmResponseDTO update(UpdateFarmDTO data , Long id);
    List<FarmResponseDTO> searchFarms(String name , String location , Double minSurface , Double maxSurface);
    Page<FarmResponseDTO> getAllFarms(Pageable pageable);
    FarmResponseDTO getById(Long id);
    FarmResponseDTO delete(Long id);
    Farm getFarmEntityById(Long id);
}
