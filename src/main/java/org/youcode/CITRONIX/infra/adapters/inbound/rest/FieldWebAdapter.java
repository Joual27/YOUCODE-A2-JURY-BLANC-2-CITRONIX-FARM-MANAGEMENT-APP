package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.field.CreateFieldDTO;
import org.youcode.CITRONIX.app.DTOs.field.FieldResponseDTO;
import org.youcode.CITRONIX.app.DTOs.field.UpdateFieldDTO;
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

    @PutMapping("{id}")
    public ResponseEntity<SuccessDTO<FieldResponseDTO>> updateField(@RequestBody @Valid UpdateFieldDTO req , @PathVariable("id") Long id ){
        FieldResponseDTO res = fieldService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Field " + id +" updated successfully !" , res) , HttpStatus.OK);
    }
}
