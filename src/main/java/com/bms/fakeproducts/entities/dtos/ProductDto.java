package com.bms.fakeproducts.entities.dtos;

import lombok.Data;


@Data
public class ProductDto {
    private int productId;
    private String productName;
    private String description;
    private double price;
    private CategoryDto category;
}
