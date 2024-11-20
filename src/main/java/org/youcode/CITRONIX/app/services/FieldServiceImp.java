package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.Mappers.field.CreateFieldDTOToFieldEntityMapper;
import org.youcode.CITRONIX.app.Mappers.field.FieldEntityToFieldResponseDTOMapper;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.FarmFieldsLimitReached;
import org.youcode.CITRONIX.app.exceptions.FarmSurfaceExceeded;
import org.youcode.CITRONIX.app.exceptions.FieldMaxSurfaceExceeded;
import org.youcode.CITRONIX.app.services.interfaces.FarmService;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.FieldPersistenceAdapter;

@Service
@AllArgsConstructor
public class FieldServiceImp implements FieldService {
    private final FieldPersistenceAdapter fieldPersistenceAdapter;
    private final FarmService farmService;
    private final CreateFieldDTOToFieldEntityMapper createFieldDTOToFieldEntityMapper;
    private final FieldEntityToFieldResponseDTOMapper fieldEntityToFieldResponseDTOMapper;

    @Override
    public FieldResponseDTO save(CreateFieldDTO data){
        Farm f = farmService.getFarmEntityById(data.farmId());
        if (data.surface() > f.getSurface() / 2){
            throw new FieldMaxSurfaceExceeded("The field surface cant be more than half of the farm's surface which is :" + f.getSurface() + "Max value accepted is " +  f.getSurface()/2);
        }
        if (calculateNumberOfFieldsPerFarm(f) == 10){
            throw new FarmFieldsLimitReached("This farm already has 10 fields which is the limit ! ");
        }
        if (data.surface() > getOverallAvailableSpaceInFarm(f)){
            throw new FarmSurfaceExceeded("U have exceeded the overall surface of the farm :" + f.getName());
        }
        Field fieldToCreate = createFieldDTOToFieldEntityMapper.toEntity(data);
        Field createdField = fieldPersistenceAdapter.save(fieldToCreate);
        createdField.setFarm(f);
        return fieldEntityToFieldResponseDTOMapper.entityToDto(createdField);
    }

    private int calculateNumberOfFieldsPerFarm(Farm farm){
        return fieldPersistenceAdapter.getNumberOfFieldsPerFarm(farm);
    }

    private Double getOverallAvailableSpaceInFarm(Farm f){
        Double takenArea = fieldPersistenceAdapter.getOverallFieldsSurfacePerFarm(f) == null ? 0 : fieldPersistenceAdapter.getOverallFieldsSurfacePerFarm(f);
        return f.getSurface() - takenArea;
    }


}
