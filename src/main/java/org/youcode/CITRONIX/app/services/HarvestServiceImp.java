package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.harvest.CreateHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.HarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.UpdateHarvestDTO;
import org.youcode.CITRONIX.app.Mappers.harvest.CreateHarvestDTOToHarvestEntityMapper;
import org.youcode.CITRONIX.app.Mappers.harvest.HarvestEntityToHarvestResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.harvest.UpdateHarvestDTOToHarvestEntityResponseDTO;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.FieldAlreadyHarvestedException;
import org.youcode.CITRONIX.app.exceptions.InvalidEnumException;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.app.services.interfaces.HarvestService;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.HarvestPersistenceAdapter;

@Service
@AllArgsConstructor
public class HarvestServiceImp implements HarvestService {
    private final HarvestPersistenceAdapter harvestPersistenceAdapter;
    private final FieldService fieldService;
    private final CreateHarvestDTOToHarvestEntityMapper createHarvestDTOToHarvestEntityMapper;
    private final HarvestEntityToHarvestResponseDTOMapper harvestEntityToHarvestResponseDTOMapper;
    private final UpdateHarvestDTOToHarvestEntityResponseDTO updateHarvestDTOToHarvestEntityResponseDTO;

    @Override
    public HarvestResponseDTO save(CreateHarvestDTO data) {
        Field f = fieldService.getFieldEntityById(data.fieldId());
        if (isValidSeason(data.season())) {
            if (harvestPersistenceAdapter.existsByYearAndSeasonAndField(data.year(), data.season(), data.fieldId())) {
                throw new FieldAlreadyHarvestedException("This field has already been harvested in the current season " + data.season() + " at year: " + data.year());
            }
            Harvest harvestToCreate = createHarvestDTOToHarvestEntityMapper.toEntity(data);
            Harvest createdHarvest = harvestPersistenceAdapter.save(harvestToCreate);
            createdHarvest.setField(f);
            return harvestEntityToHarvestResponseDTOMapper.entityToDto(createdHarvest);
        } else {
            throw new InvalidEnumException("The value given for season isn't valid. Please enter either SUMMER | WINTER | AUTUMN | SPRING");
        }
    }

    @Override
    public HarvestResponseDTO update(UpdateHarvestDTO data , Long id){
       Harvest existingHarvest = harvestPersistenceAdapter.findById(id)
               .orElseThrow(()-> new EntityNotFoundException("No harvest Found with given ID !"));
        if (isValidSeason(data.season())) {
            Harvest harvestToUpdate = updateHarvestDTOToHarvestEntityResponseDTO.toEntity(data);
            harvestToUpdate.setId(id);
            harvestToUpdate.setField(existingHarvest.getField());
            Harvest updatedHarvest = harvestPersistenceAdapter.save(harvestToUpdate);
            return harvestEntityToHarvestResponseDTOMapper.entityToDto(updatedHarvest);
        } else {
            throw new InvalidEnumException("The value given for season isn't valid. Please enter either SUMMER | WINTER | AUTUMN | SPRING");
        }
    }

    @Override
    public Page<HarvestResponseDTO> getAll(Pageable pageable){
        Page<Harvest> harvests = harvestPersistenceAdapter.findAll(pageable);
        return harvests.map(harvestEntityToHarvestResponseDTOMapper::entityToDto);
    }

    @Override
    public HarvestResponseDTO getById(Long id){
        Harvest existingHarvest = harvestPersistenceAdapter.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No harvest Found with given ID !"));
        return harvestEntityToHarvestResponseDTOMapper.entityToDto(existingHarvest);
    }

    @Override
    public HarvestResponseDTO delete(Long id){
        Harvest existingHarvest = harvestPersistenceAdapter.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No harvest Found with given ID !"));
        harvestPersistenceAdapter.deleteById(id);
        return harvestEntityToHarvestResponseDTOMapper.entityToDto(existingHarvest);
    }

    private boolean isValidSeason(String season){
        return season.equalsIgnoreCase("SUMMER") || season.equalsIgnoreCase("WINTER") || season.equalsIgnoreCase("SPRING") || season.equalsIgnoreCase("AUTUMN");
    }


}
