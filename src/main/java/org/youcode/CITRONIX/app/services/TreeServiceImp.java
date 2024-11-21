package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.Mappers.tree.CreateTreeDTOToTreeEntityMapper;
import org.youcode.CITRONIX.app.Mappers.tree.TreeEntityToTreeResponseDTOMapper;
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

    private int getMaxAllowedTreesOfField(Field f){
        double res = f.getSurface() / 10000 * 100;
        return (int) res;
    }

    private boolean isValidPlantingDate(LocalDate plantingDate){
        return (plantingDate.getMonth() == Month.MARCH || plantingDate.getMonth() == Month.APRIL || plantingDate.getMonth() == Month.MAY);
    }
}
