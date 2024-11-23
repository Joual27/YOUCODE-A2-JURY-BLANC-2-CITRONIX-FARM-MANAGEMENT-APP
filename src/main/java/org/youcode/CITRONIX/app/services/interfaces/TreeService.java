package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.DTOs.tree.UpdateTreeDTO;
import org.youcode.CITRONIX.core.entities.Tree;

public interface TreeService {
    TreeResponseDTO save(CreateTreeDTO data);
    TreeResponseDTO update(UpdateTreeDTO data , Long id);
    Page<TreeResponseDTO> getAll(Pageable pageable);
    TreeResponseDTO getById(Long id);
    TreeResponseDTO delete(Long id);
    Tree getEntityById(Long id);
}
