package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.DTOs.farm.UpdateFarmDTO;
import org.youcode.CITRONIX.app.Mappers.farm.CreateFarmDTOToFarmEntityResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.farm.FarmEntityToFarmResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.farm.UpdateFarmDTOToFarmEntityMapper;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.UniqueFieldException;
import org.youcode.CITRONIX.app.services.interfaces.FarmService;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.FarmPersistenceAdapter;
import org.youcode.CITRONIX.app.specifications.FarmSpecification;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class FarmServiceImp implements FarmService {
    private final FarmPersistenceAdapter farmPersistenceAdapter;
    private final CreateFarmDTOToFarmEntityResponseDTOMapper createFarmDTOToFarmEntityResponseDTOMapper;
    private final FarmEntityToFarmResponseDTOMapper farmEntityToFarmResponseDTOMapper;
    private final UpdateFarmDTOToFarmEntityMapper updateFarmDTOToFarmEntityMapper;

    @Override
    public FarmResponseDTO save(CreateFarmDTO data){
       Farm farmToCreate = createFarmDTOToFarmEntityResponseDTOMapper.toEntity(data);
       farmToCreate.setCreatedAt(LocalDateTime.now());
       Farm createdFarm = farmPersistenceAdapter.save(farmToCreate);
       return farmEntityToFarmResponseDTOMapper.entityToDto(createdFarm);
    }

    @Override
    public FarmResponseDTO update(UpdateFarmDTO data , Long id){
       Farm f = farmPersistenceAdapter.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("No farm found with given ID !"));
       if (farmPersistenceAdapter.existsByNameNotId(data.name() , id)){
           throw new UniqueFieldException("The Name You've given already exists !");
       }
       Farm farmToUpdate = updateFarmDTOToFarmEntityMapper.toEntity(data);
       farmToUpdate.setId(id);
       farmToUpdate.setCreatedAt(f.getCreatedAt());
       Farm updatedFarm = farmPersistenceAdapter.save(farmToUpdate);
       return farmEntityToFarmResponseDTOMapper.entityToDto(updatedFarm);
    }

    @Override
    public List<FarmResponseDTO> searchFarms(String name , String location , Double minSurface , Double maxSurface){
        Specification<Farm> specification = FarmSpecification.searchFarms(name , location , minSurface , maxSurface );
        List<Farm> farms = farmPersistenceAdapter.findAll(specification);
        return farms.stream()
                .map(farmEntityToFarmResponseDTOMapper::entityToDto)
                .toList();
    }

    @Override
    public Page<FarmResponseDTO> getAllFarms(Pageable pageable){
        Page<Farm> farms = farmPersistenceAdapter.findAll(pageable);
        return farms.map(farmEntityToFarmResponseDTOMapper::entityToDto);
    }

    @Override
    public FarmResponseDTO getById(Long id){
        Farm f = farmPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO farm found with given ID !"));
        return farmEntityToFarmResponseDTOMapper.entityToDto(f);
    }

    @Override
    public FarmResponseDTO delete(Long id){
        Farm f = farmPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO farm found with given ID !"));
        farmPersistenceAdapter.deleteById(id);
        return farmEntityToFarmResponseDTOMapper.entityToDto(f);
    }

}

