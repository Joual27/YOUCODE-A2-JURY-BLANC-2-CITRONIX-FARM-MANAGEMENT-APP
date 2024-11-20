package org.youcode.CITRONIX.app.services.interfaces;

import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;


public interface FieldService {

    FieldResponseDTO save(CreateFieldDTO data);


}
