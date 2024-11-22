package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.harvest.CreateHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.HarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.UpdateHarvestDTO;

public interface HarvestService {
    HarvestResponseDTO save(CreateHarvestDTO data);
    HarvestResponseDTO update(UpdateHarvestDTO data , Long id);
    Page<HarvestResponseDTO> getAll(Pageable pageable);
    HarvestResponseDTO getById(Long id);
    HarvestResponseDTO delete(Long id);
}
