package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.CITRONIX.app.DTOs.farm.CreateFarmDTO;
import org.youcode.CITRONIX.app.DTOs.farm.FarmResponseDTO;
import org.youcode.CITRONIX.app.services.interfaces.FarmService;
import org.youcode.CITRONIX.shared.DTOs.SuccessDTO;

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
}
