package org.youcode.CITRONIX.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.CITRONIX.app.DTOs.sale.CreateSaleDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseDTO;
import org.youcode.CITRONIX.app.DTOs.sale.SaleResponseWithOverallPriceDTO;
import org.youcode.CITRONIX.app.DTOs.sale.UpdateSaleDTO;
import org.youcode.CITRONIX.app.services.interfaces.SaleService;
import org.youcode.CITRONIX.shared.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
@AllArgsConstructor
public class SaleWebAdapter {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SuccessDTO<SaleResponseDTO>> createSale(@RequestBody @Valid CreateSaleDTO req){
        SaleResponseDTO res = saleService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sale Created Successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<SaleResponseDTO>> updateSale(@RequestBody @Valid UpdateSaleDTO req , @PathVariable("id") Long id){
        SaleResponseDTO res = saleService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sale updated successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<SaleResponseDTO>>> getAllSales(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<SaleResponseDTO> res = saleService.getAllSales(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sales retrieved successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<SaleResponseDTO>> getSaleById( @PathVariable("id") Long id ){
        SaleResponseDTO res = saleService.getById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sale " + id +" retrieved successfully !" , res) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<SaleResponseDTO>> deleteSale( @PathVariable("id") Long id ){
        SaleResponseDTO res = saleService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sale " + id +" deleted successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("overall-price/{id}")
    public ResponseEntity<SuccessDTO<SaleResponseWithOverallPriceDTO>> getOverallPrice( @PathVariable("id") Long id ){
        SaleResponseWithOverallPriceDTO res = saleService.getSaleWithItsOverallPrice(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Sale " + id +" overall price calculated successfully !" , res) , HttpStatus.OK);
    }
}
