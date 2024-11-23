package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.CreateTreeHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.TreeHarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.treeHarvest.UpdateTreeHarvestDTO;
import org.youcode.CITRONIX.app.services.interfaces.TreeHarvestService;
import org.youcode.CITRONIX.core.embeddebales.TreeHarvestKey;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/tree-harvests")
@AllArgsConstructor
public class TreeHarvestWebAdapter {

    private final TreeHarvestService treeHarvestService;

    @PostMapping
    public ResponseEntity<SuccessDTO<TreeHarvestResponseDTO>> createTreeHarvest(@RequestBody @Valid CreateTreeHarvestDTO req){
        TreeHarvestResponseDTO res = treeHarvestService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Harvest created successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO<TreeHarvestResponseDTO>> updateTreeHarvest(@RequestBody @Valid UpdateTreeHarvestDTO req , @PathVariable("harvestId") Long harvestId, @PathVariable("treeId") Long treeId ){
        TreeHarvestResponseDTO res = treeHarvestService.update(req , treeId , harvestId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Harvest Updated successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<TreeHarvestResponseDTO>>> getAll(@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<TreeHarvestResponseDTO> res = treeHarvestService.getAll(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Harvests Retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO<TreeHarvestResponseDTO>> getTreeHarvestById(@PathVariable("harvestId") Long harvestId, @PathVariable("treeId") Long treeId ){
        TreeHarvestKey id = new TreeHarvestKey(harvestId , treeId);
        TreeHarvestResponseDTO res = treeHarvestService.getById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Harvest Retrieved successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO<TreeHarvestResponseDTO>> deleteTreeHarvest(@PathVariable("harvestId") Long harvestId, @PathVariable("treeId") Long treeId ){
        TreeHarvestKey id = new TreeHarvestKey(harvestId , treeId);
        TreeHarvestResponseDTO res = treeHarvestService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Harvest Deleted successfully !" , res) , HttpStatus.OK);
    }


}
