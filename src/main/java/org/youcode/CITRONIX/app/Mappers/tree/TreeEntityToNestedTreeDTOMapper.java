package org.youcode.CITRONIX.app.Mappers.tree;


import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.tree.NestedTreeDTO;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface TreeEntityToNestedTreeDTOMapper extends BaseMapper<Tree , NestedTreeDTO> {
}
