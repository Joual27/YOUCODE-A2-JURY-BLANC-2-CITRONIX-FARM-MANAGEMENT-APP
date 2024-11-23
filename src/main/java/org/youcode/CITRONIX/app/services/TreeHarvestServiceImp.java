package org.youcode.CITRONIX.app.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.CreateTreeHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.TreeHarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.UpdateTreeHarvestDTO;
import org.youcode.CITRONIX.app.Mappers.treeHarvest.CreateTreeHarvestDTOToTreeHarvestEntityMapper;
import org.youcode.CITRONIX.app.Mappers.treeHarvest.TreeHarvestEntityToTreeHarvestResponseDTO;
import org.youcode.CITRONIX.app.Mappers.treeHarvest.UpdateTreeHarvestDTOToTreeHarvestEntityMapper;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.TreeAlreadyHarvestedException;
import org.youcode.CITRONIX.app.exceptions.TreeDoesntBelongToFieldException;
import org.youcode.CITRONIX.app.services.interfaces.HarvestService;
import org.youcode.CITRONIX.app.services.interfaces.TreeHarvestService;
import org.youcode.CITRONIX.app.services.interfaces.TreeService;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.core.entities.Harvest;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.core.entities.TreeHarvest;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.TreeHarvestPersistenceAdapter;


import java.util.Objects;

@Service
@AllArgsConstructor
public class TreeHarvestServiceImp implements TreeHarvestService {
    private final TreeHarvestPersistenceAdapter treeHarvestPersistenceAdapter;
    private final CreateTreeHarvestDTOToTreeHarvestEntityMapper createTreeHarvestDTOToTreeHarvestEntityMapper;
    private final TreeHarvestEntityToTreeHarvestResponseDTO treeHarvestEntityToTreeHarvestResponseDTO;
    private final UpdateTreeHarvestDTOToTreeHarvestEntityMapper updateTreeHarvestDTOToTreeHarvestEntityMapper;
    private final TreeService treeService;
    private final HarvestService harvestService;

    @Override
    public TreeHarvestResponseDTO save(CreateTreeHarvestDTO data){
        Tree existingTree = treeService.getEntityById(data.treeId());
        Harvest existingHarvest = harvestService.getEntityById(data.harvestId());
        if (!Objects.equals(existingTree.getField().getId(), existingHarvest.getField().getId())){
            throw new TreeDoesntBelongToFieldException("The tree " + existingTree.getId() + " doesn't belong To Field :" + existingHarvest.getField().getId() + " , It belongs to field : " +  existingTree.getField().getId());
        }
        if (treeHarvestPersistenceAdapter.treeAlreadyHarvested(existingHarvest , existingTree)){
            throw new TreeAlreadyHarvestedException("This Tree Was Already Harvested during this season !");
        }
        TreeHarvest treeHarvestToCreate = createTreeHarvestDTOToTreeHarvestEntityMapper.toEntity(data);
        TreeHarvest createdTreeHarvest = treeHarvestPersistenceAdapter.save(treeHarvestToCreate);
        createdTreeHarvest.setTree(existingTree);
        createdTreeHarvest.setHarvest(existingHarvest);
        return treeHarvestEntityToTreeHarvestResponseDTO.entityToDto(createdTreeHarvest);
    }

    @Override
    public TreeHarvestResponseDTO update(UpdateTreeHarvestDTO data , Long treeId , Long harvestId){
        TreeHarvestKey id = new TreeHarvestKey(harvestId , treeId);
        TreeHarvest existingTreeHarvest = getTreeHarvestEntityById(id);
        TreeHarvest treeHarvestToUpdate = updateTreeHarvestDTOToTreeHarvestEntityMapper.toEntity(data);
        treeHarvestToUpdate.setId(id);
        treeHarvestToUpdate.setHarvest(existingTreeHarvest.getHarvest());
        treeHarvestToUpdate.setTree(existingTreeHarvest.getTree());
        TreeHarvest updatedTreeHarvest = treeHarvestPersistenceAdapter.save(treeHarvestToUpdate);
        return treeHarvestEntityToTreeHarvestResponseDTO.entityToDto(updatedTreeHarvest);
    }

    @Override
    public TreeHarvest getTreeHarvestEntityById(TreeHarvestKey id){
        return treeHarvestPersistenceAdapter.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Such tree harvest with given ID !"));
    }

    @Override
    public Page<TreeHarvestResponseDTO> getAll(Pageable pageable){
        Page<TreeHarvest> treeHarvests = treeHarvestPersistenceAdapter.findAll(pageable);
        return treeHarvests.map(treeHarvestEntityToTreeHarvestResponseDTO::entityToDto);
    }

    @Override
    public TreeHarvestResponseDTO getById(TreeHarvestKey id){
        TreeHarvest existingTreeHarvest = getTreeHarvestEntityById(id);
        return treeHarvestEntityToTreeHarvestResponseDTO.entityToDto(existingTreeHarvest);
    }
    @Override
    public TreeHarvestResponseDTO delete(TreeHarvestKey id){
        TreeHarvest existingTreeHarvest = getTreeHarvestEntityById(id);
        treeHarvestPersistenceAdapter.deleteById(id);
        return treeHarvestEntityToTreeHarvestResponseDTO.entityToDto(existingTreeHarvest);
    }

}
