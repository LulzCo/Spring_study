package com.springboot.guidesources.test.data.dto;

import lombok.Data;

@Data
public class ProductionResponseDto {

    private Long number;

    private String name;

    private int price;

    private int stock;
}
