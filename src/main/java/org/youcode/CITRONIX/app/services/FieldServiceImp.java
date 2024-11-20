package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.FieldPersistenceAdapter;

@Service
@AllArgsConstructor
public class FieldServiceImp implements FieldService {
    private final FieldPersistenceAdapter fieldPersistenceAdapter;

    @Override
    public FieldResponseDTO save(CreateFieldDTO data){

    }
}
