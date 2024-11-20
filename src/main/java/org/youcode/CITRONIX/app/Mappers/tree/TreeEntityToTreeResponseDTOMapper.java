package org.youcode.CITRONIX.app.Mappers.tree;

import org.mapstruct.Mapper;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.shared.interfaces.BaseMapper;

@Mapper(componentModel = "spring")
public interface TreeEntityToTreeResponseDTOMapper extends BaseMapper<Tree , TreeResponseDTO> {
}
