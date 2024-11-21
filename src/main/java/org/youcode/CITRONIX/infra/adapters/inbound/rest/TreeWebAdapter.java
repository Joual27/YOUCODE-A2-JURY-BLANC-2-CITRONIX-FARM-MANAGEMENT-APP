package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.services.interfaces.TreeService;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

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
}
