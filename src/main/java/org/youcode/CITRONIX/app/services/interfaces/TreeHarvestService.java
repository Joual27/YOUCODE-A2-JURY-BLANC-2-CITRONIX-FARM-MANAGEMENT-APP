package org.youcode.CITRONIX.app.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.CreateTreeHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.TreeHarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.UpdateTreeHarvestDTO;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.core.entities.TreeHarvest;

public interface TreeHarvestService {
    TreeHarvestResponseDTO save(CreateTreeHarvestDTO data);
    TreeHarvestResponseDTO update(UpdateTreeHarvestDTO data , Long treeId , Long harvestId);
    TreeHarvest getTreeHarvestEntityById(TreeHarvestKey id);
    Page<TreeHarvestResponseDTO> getAll(Pageable pageable);
    TreeHarvestResponseDTO getById(TreeHarvestKey id);
    TreeHarvestResponseDTO delete(TreeHarvestKey id);
}
