package com.springboot.guidesources.test.service;

import com.springboot.guidesources.jpa.data.dto.ProductDto;
import com.springboot.guidesources.test.data.dto.ProductionDto;
import com.springboot.guidesources.test.data.dto.ProductionResponseDto;

public interface ProductionService {

    ProductionResponseDto getProduction(Long number);

    ProductionResponseDto saveProduction(ProductionDto productionDto);

    ProductionResponseDto changePorudctionName(String name, Long number);

    void deleteProduction(Long number);

}
