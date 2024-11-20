package org.youcode.CITRONIX.app.services.interfaces;

import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.DTOs.field.UpdateFieldDTO;


public interface FieldService {
    FieldResponseDTO save(CreateFieldDTO data);
    FieldResponseDTO update (UpdateFieldDTO data , Long id);
}
