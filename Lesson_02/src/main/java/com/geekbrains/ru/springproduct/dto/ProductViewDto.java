package com.geekbrains.ru.springproduct.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {

    private Long id;

    private String name;

    private Double price;

}
