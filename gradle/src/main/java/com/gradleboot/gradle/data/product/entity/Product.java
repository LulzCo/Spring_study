package com.gradleboot.gradle.data.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
/*
* @Getter
* @Setter
* @ToString
* @NoArgsConstructor
* @AllArgsContstructor
* @EqualsAndHashCode
* @RequiredArgsConstructor      final, nonNull 을 제외해서 */
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
