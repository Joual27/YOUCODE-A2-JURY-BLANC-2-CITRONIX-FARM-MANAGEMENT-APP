package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

@RestController
@RequestMapping("api/v1/fields")
@AllArgsConstructor
public class FieldWebAdapter {

    private final FieldService fieldService;

    @PostMapping
    public ResponseEntity<SuccessDTO<FieldResponseDTO>> createField(@RequestBody @Valid CreateFieldDTO req){
        FieldResponseDTO res = fieldService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "field created successfully !" , res) , HttpStatus.OK);
    }
}
