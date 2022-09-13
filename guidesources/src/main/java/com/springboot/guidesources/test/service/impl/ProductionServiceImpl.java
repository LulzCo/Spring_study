package com.springboot.guidesources.test.service.impl;

import com.springboot.guidesources.test.data.dto.ProductionDto;
import com.springboot.guidesources.test.data.dto.ProductionResponseDto;
import com.springboot.guidesources.test.data.entity.Production;
import com.springboot.guidesources.test.data.repository.ProductionRespository;
import com.springboot.guidesources.test.service.ProductionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductionServiceImpl.class);
    private final ProductionRespository productionRespository;

    @Autowired
    public ProductionServiceImpl(ProductionRespository productionRespository) {
        this.productionRespository = productionRespository;
    }

    @Override
    public ProductionResponseDto getProduction(Long number) {

        LOGGER.info("[getProduction] input number : {}", number);

        Production production = productionRespository.findById(number).get();

        LOGGER.info("[getProduction] production number : {}, production name : {}", production.getNumber(), production.getName());

        ProductionResponseDto productionResponseDto = new ProductionResponseDto();
        productionResponseDto.setNumber(production.getNumber());
        productionResponseDto.setName(production.getName());
        productionResponseDto.setPrice(production.getPrice());
        productionResponseDto.setStock(production.getStock());

        return productionResponseDto;
    }

    @Override
    public ProductionResponseDto saveProduction(ProductionDto productionDto) {
        LOGGER.info("[saveProduction] productionDto : {}", productionDto.toString());

        Production production = new Production();
        production.setName(productionDto.getName());
        production.setStock(productionDto.getStock());
        production.setPrice(productionDto.getPrice());

        Production savedProduction = productionRespository.save(production);
        LOGGER.info("[saveProduction] savedProduction : {}", savedProduction);

        ProductionResponseDto productionResponseDto = new ProductionResponseDto();
        productionResponseDto.setStock(production.getStock());
        productionResponseDto.setName(production.getName());
        productionResponseDto.setPrice(production.getPrice());
        productionResponseDto.setNumber(production.getNumber());

        return productionResponseDto;
    }

    @Override
    public ProductionResponseDto changePorudctionName(String name, Long number) {

        LOGGER.info("[changeProductionName] input name : {}, number : {}", name, number);

        Production production = productionRespository.findById(number).get();
        production.setName(name);

        LOGGER.info("[changeProductionNmae] before production : {}", production);

        Production updatedProduction = productionRespository.save(production);

        LOGGER.info("[changeProductionName] after production : {}", updatedProduction);

        ProductionResponseDto productionResponseDto = new ProductionResponseDto();
        productionResponseDto.setNumber(updatedProduction.getNumber());
        productionResponseDto.setName(updatedProduction.getName());
        productionResponseDto.setStock(updatedProduction.getStock());
        productionResponseDto.setPrice(updatedProduction.getPrice());

        return productionResponseDto;
    }

    @Override
    public void deleteProduction(Long number) {

//        Production production = productionRespository.findById(number).get();
//        productionRespository.delete(production);

        productionRespository.deleteById(number);

    }
}
