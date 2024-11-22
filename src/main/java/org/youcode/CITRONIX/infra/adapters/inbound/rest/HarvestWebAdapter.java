package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.CreateHarvestDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.HarvestResponseDTO;
import org.youcode.CITRONIX.app.DTOs.harvest.UpdateHarvestDTO;
import org.youcode.CITRONIX.app.services.interfaces.HarvestService;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/harvests")
@AllArgsConstructor
public class HarvestWebAdapter {
    private final HarvestService harvestService;

    @PostMapping
    public ResponseEntity<SuccessDTO<HarvestResponseDTO>> createHarvest(@RequestBody @Valid CreateHarvestDTO req){
        HarvestResponseDTO res = harvestService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Harvest created successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<HarvestResponseDTO>> updateHarvest(@RequestBody @Valid UpdateHarvestDTO req , @PathVariable("id") Long id){
        HarvestResponseDTO res = harvestService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Harvest updated successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<HarvestResponseDTO>>> getAllHarvests(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<HarvestResponseDTO> res = harvestService.getAll(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Harvests retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<HarvestResponseDTO>> getHarvestById( @PathVariable("id") Long id ){
        HarvestResponseDTO res = harvestService.getById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Harvest " + id +" retrieved successfully !" , res) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<HarvestResponseDTO>> deleteHarvest( @PathVariable("id") Long id ){
        HarvestResponseDTO res = harvestService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Harvest " + id +" deleted successfully !" , res) , HttpStatus.OK);
    }
}
