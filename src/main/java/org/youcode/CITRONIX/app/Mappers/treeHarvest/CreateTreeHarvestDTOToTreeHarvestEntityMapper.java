package org.youcode.CITRONIX.app.Mappers.treeHarvest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.CreateTreeHarvestDTO;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.core.entities.TreeHarvest;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public abstract class CreateTreeHarvestDTOToTreeHarvestEntityMapper implements BaseMapper<TreeHarvest , CreateTreeHarvestDTO> {

    @Mapping(target = "tree.id" , source = "treeId")
    @Mapping(target = "harvest.id" , source = "harvestId")
    @Mapping(target = "id" , expression = "java(generateKey(dto.treeId() , dto.harvestId()))")
    public abstract TreeHarvest toEntity(CreateTreeHarvestDTO dto);


    protected TreeHarvestKey generateKey(Long treeId , Long harvestId){
        return new TreeHarvestKey(treeId , harvestId);
    }


}
