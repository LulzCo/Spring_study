package com.springboot.guidesources.test.controller;

import com.springboot.guidesources.test.data.dto.ChangeProductionNameDto;
import com.springboot.guidesources.test.data.dto.ProductionDto;
import com.springboot.guidesources.test.data.dto.ProductionResponseDto;
import com.springboot.guidesources.test.data.entity.Production;
import com.springboot.guidesources.test.service.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/production")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public ResponseEntity<ProductionResponseDto> getProduction(Long number) {
        ProductionResponseDto productionResponseDto = productionService.getProduction(number);

        return ResponseEntity.status(HttpStatus.OK).body(productionResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProductionResponseDto> saveProduction(@RequestBody ProductionDto productionDto) {
        ProductionResponseDto productionResponseDto = productionService.saveProduction(productionDto);

        return ResponseEntity.status(HttpStatus.OK).body(productionResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductionResponseDto> changeProductionName(@RequestBody ChangeProductionNameDto changeProductionNameDto) throws Exception {
        ProductionResponseDto productionResponseDto = productionService.changePorudctionName(changeProductionNameDto.getName(), changeProductionNameDto.getNumber());

        return ResponseEntity.status(HttpStatus.OK).body(productionResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduction(Long number) throws Exception {
        productionService.deleteProduction(number);

        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }
}
