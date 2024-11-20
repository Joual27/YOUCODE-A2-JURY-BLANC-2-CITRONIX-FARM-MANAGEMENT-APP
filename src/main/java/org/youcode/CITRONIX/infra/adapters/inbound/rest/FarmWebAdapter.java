package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.DTOs.farm.UpdateFarmDTO;
import org.youcode.CITRONIX.app.services.interfaces.FarmService;
import org.youcode.CITRONIX.core.entities.Farm;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/farms")
@AllArgsConstructor
public class FarmWebAdapter {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<SuccessDTO<FarmResponseDTO>> createFarm(@RequestBody @Valid CreateFarmDTO req){
        FarmResponseDTO res = farmService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Farm created successfully !" , res ) , HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessDTO<FarmResponseDTO>> updateFarm(@RequestBody @Valid UpdateFarmDTO req , @PathVariable("id") Long id){
        FarmResponseDTO res = farmService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Farm "+ id  +" Updated successfully !" , res ) , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessDTO<List<FarmResponseDTO>>> filterFarms(@RequestParam(required = false) String name, @RequestParam(required = false) String location , @RequestParam(required = false) Double minSurface , @RequestParam(required = false) Double maxSurface){
        List<FarmResponseDTO> res = farmService.searchFarms(name , location , minSurface , maxSurface);
        return new ResponseEntity<>(new SuccessDTO<>("success" , res.isEmpty() ? "No Farm found with given search details !" : "Farms Retrieved successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<FarmResponseDTO>>> getAllFarms(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<FarmResponseDTO> res = farmService.getAllFarms(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Farms retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<FarmResponseDTO>> getFarmById(@PathVariable("id") Long id){
        FarmResponseDTO res = farmService.getById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Farm " + id +" retrieved successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<FarmResponseDTO>> delete(@PathVariable("id") Long id){
        FarmResponseDTO res = farmService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Farm " + id +" Deleted successfully !" , res) , HttpStatus.OK);
    }


}
