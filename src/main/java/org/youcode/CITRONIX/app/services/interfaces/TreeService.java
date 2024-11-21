package org.youcode.CITRONIX.app.services.interfaces;

import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;

public interface TreeService {
    TreeResponseDTO save(CreateTreeDTO data);
}
