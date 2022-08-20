package com.gradleboot.gradle.data.product.dto;

import lombok.Data;

@Data
public class ChangeProductNameDto {

    private Long number;

    private String name;

    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }


}
