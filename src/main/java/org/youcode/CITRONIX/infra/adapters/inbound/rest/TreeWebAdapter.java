package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.DTOs.tree.UpdateTreeDTO;
import org.youcode.CITRONIX.app.services.interfaces.TreeService;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/trees")
@AllArgsConstructor
public class TreeWebAdapter {

    private final TreeService treeService;

    @PostMapping
    public ResponseEntity<SuccessDTO<TreeResponseDTO>> createTree(@RequestBody @Valid CreateTreeDTO req){
        TreeResponseDTO res = treeService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree created successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessDTO<TreeResponseDTO>> updateTree(@RequestBody @Valid UpdateTreeDTO req , @PathVariable("id") Long id) {
        TreeResponseDTO res = treeService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Updated successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<TreeResponseDTO>>> getAllTrees(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<TreeResponseDTO> res = treeService.getAll(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Trees Retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessDTO<TreeResponseDTO>> getTreeById(@PathVariable("id") Long id) {
        TreeResponseDTO res = treeService.getById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Retrieved successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessDTO<TreeResponseDTO>> deleteTree(@PathVariable("id") Long id) {
        TreeResponseDTO res = treeService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Tree Deleted successfully !" , res) , HttpStatus.OK);
    }
}
