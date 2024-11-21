package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.DTOs.tree.UpdateTreeDTO;
import org.youcode.CITRONIX.app.Mappers.tree.CreateTreeDTOToTreeEntityMapper;
import org.youcode.CITRONIX.app.Mappers.tree.TreeEntityToTreeResponseDTOMapper;
import org.youcode.CITRONIX.app.Mappers.tree.UpdateTreeDTOToTreeEntityMapper;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.FieldMaxDensityReachedException;
import org.youcode.CITRONIX.app.exceptions.InvalidPlantingDateException;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.app.services.interfaces.TreeService;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.TreePersistenceAdapter;

import java.time.LocalDate;
import java.time.Month;

@Service
@AllArgsConstructor
public class TreeServiceImp implements TreeService {
    private final TreePersistenceAdapter treePersistenceAdapter;
    private final FieldService fieldService;
    private final CreateTreeDTOToTreeEntityMapper createTreeDTOToTreeEntityMapper;
    private final TreeEntityToTreeResponseDTOMapper treeEntityToTreeResponseDTOMapper;
    private final UpdateTreeDTOToTreeEntityMapper updateTreeDTOToTreeEntityMapper;

    @Override
    public TreeResponseDTO save(CreateTreeDTO data){
        Field f = fieldService.getFieldEntityById(data.fieldId());
        if (f.getTrees().size() == getMaxAllowedTreesOfField(f)){
            throw  new FieldMaxDensityReachedException("This field already reached the limit of planting !");
        }
        if (!isValidPlantingDate(data.plantingDate())){
            throw new InvalidPlantingDateException("Trees can only be planted between March and Mai");
        }
        Tree treeToCreate = createTreeDTOToTreeEntityMapper.toEntity(data);
        Tree createdTree = treePersistenceAdapter.save(treeToCreate);
        createdTree.setField(f);
        return treeEntityToTreeResponseDTOMapper.entityToDto(createdTree);
    }

    @Override
    public TreeResponseDTO update(UpdateTreeDTO data , Long id){
        Field f = fieldService.getFieldEntityById(id);
        Tree t = treePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO TREE FOUND WITH GIVEN ID !"));
        if (!isValidPlantingDate(data.plantingDate())){
            throw new InvalidPlantingDateException("Trees can only be planted between March and Mai");
        }
        Tree treeToUpdate = updateTreeDTOToTreeEntityMapper.toEntity(data);
        treeToUpdate.setId(id);
        treeToUpdate.setField(f);
        Tree updatedTree = treePersistenceAdapter.save(treeToUpdate);
        return treeEntityToTreeResponseDTOMapper.entityToDto(updatedTree);
    }

    @Override
    public Page<TreeResponseDTO> getAll(Pageable pageable){
        Page<Tree> trees = treePersistenceAdapter.findAll(pageable);
        return trees.map(treeEntityToTreeResponseDTOMapper::entityToDto);
    }

    @Override
    public TreeResponseDTO getById(Long id){
       Tree treeToFetch = treePersistenceAdapter.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("No tree was found with given ID !"));
       return treeEntityToTreeResponseDTOMapper.entityToDto(treeToFetch);
    }
    @Override
    public TreeResponseDTO delete(Long id){
        Tree treeToDelete = treePersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No tree was found with given ID !"));
        treePersistenceAdapter.deleteById(id);
        return treeEntityToTreeResponseDTOMapper.entityToDto(treeToDelete);
    }

    private int getMaxAllowedTreesOfField(Field f){
        double res = f.getSurface() / 10000 * 100;
        return (int) res;
    }

    private boolean isValidPlantingDate(LocalDate plantingDate){
        return (plantingDate.getMonth() == Month.MARCH || plantingDate.getMonth() == Month.APRIL || plantingDate.getMonth() == Month.MAY);
    }
}
