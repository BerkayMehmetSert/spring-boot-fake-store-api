package com.bms.fakeproducts.api.controllers;

import com.bms.fakeproducts.business.abstracts.ProductService;
import com.bms.fakeproducts.entities.dtos.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product API")

public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get All Products", description = "Get All Products", tags = {"Products"})
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @Operation(summary = "Get Product By Id", description = "Get Product By Id", tags = {"Products"})
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getById(@Parameter(description = "Product Id") @PathVariable int productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @Operation(summary = "Add Product", description = "Add Product", tags = {"Products"})
    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Parameter(description = "Product")
                                                     @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.addProduct(productDto));
    }

    @Operation(summary = "Update Product", description = "Update Product", tags = {"Products"})
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Parameter(description = "Product Id") @PathVariable int productId,
                                                    @Parameter(description = "Product") @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @Operation(summary = "Delete Product", description = "Delete Product", tags = {"Products"})
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@Parameter(description = "Product Id") @PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

}
