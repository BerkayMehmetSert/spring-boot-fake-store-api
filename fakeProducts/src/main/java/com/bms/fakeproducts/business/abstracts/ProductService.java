package com.bms.fakeproducts.business.abstracts;


import com.bms.fakeproducts.entities.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto getById(int productId);

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(int productId, ProductDto productDto);

    ProductDto deleteProduct(int productId);
}
