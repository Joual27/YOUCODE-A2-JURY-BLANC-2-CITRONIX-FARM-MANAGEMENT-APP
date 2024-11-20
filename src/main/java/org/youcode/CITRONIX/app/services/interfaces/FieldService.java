package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.DTOs.field.UpdateFieldDTO;


public interface FieldService {
    FieldResponseDTO save(CreateFieldDTO data);
    FieldResponseDTO update (UpdateFieldDTO data , Long id);
    Page<FieldResponseDTO> getAllFields(Pageable pageable);
    FieldResponseDTO getById(Long id);
    FieldResponseDTO delete(Long id);
}
