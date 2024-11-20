package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.Mappers.farm.CreateFarmDTOToFarmEntityResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.farm.FarmEntityToFarmResponseDTOMapper;
import org.youcode.CITRONIX.app.services.interfaces.FarmService;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.FarmPersistenceAdapter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class FarmServiceImp implements FarmService {
    private final FarmPersistenceAdapter farmPersistenceAdapter;
    private final CreateFarmDTOToFarmEntityResponseDTOMapper createFarmDTOToFarmEntityResponseDTOMapper;
    private final FarmEntityToFarmResponseDTOMapper farmEntityToFarmResponseDTOMapper;

    @Override
    public FarmResponseDTO save(CreateFarmDTO data){
       Farm farmToCreate = createFarmDTOToFarmEntityResponseDTOMapper.toEntity(data);
       farmToCreate.setCreatedAt(LocalDateTime.now());
       Farm createdFarm = farmPersistenceAdapter.save(farmToCreate);
       return farmEntityToFarmResponseDTOMapper.entityToDto(createdFarm);
    }

}
