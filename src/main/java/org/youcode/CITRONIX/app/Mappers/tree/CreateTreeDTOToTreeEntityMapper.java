package org.youcode.CITRONIX.app.Mappers.tree;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface CreateTreeDTOToTreeEntityMapper extends BaseMapper<Tree, CreateTreeDTO> {

    @Mapping(source = "dto.fieldId" , target = "field.id")
    Tree toEntity(CreateTreeDTO dto);
}
