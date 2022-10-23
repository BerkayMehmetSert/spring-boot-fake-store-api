package com.bms.fakeproducts.api.controllers;

import com.bms.fakeproducts.business.abstracts.CategoryService;
import com.bms.fakeproducts.entities.dtos.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Category API")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get All Categories", description = "Get All Categories", tags = {"Categories"})
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @Operation(summary = "Get Category By Id", description = "Get Category By Id", tags = {"Categories"})
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getById(@Parameter(description = "Category Id", required = true) @PathVariable int categoryId) {
        return ResponseEntity.ok(categoryService.getById(categoryId));
    }

    @Operation(summary = "Add Category", description = "Add Category", tags = {"Categories"})
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Parameter(description = "Category", required = true) @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }

    @Operation(summary = "Update Category", description = "Update Category", tags = {"Categories"})
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Parameter(description = "Category Id", required = true) @PathVariable int categoryId,
                                                      @Parameter(description = "Category", required = true) @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    @Operation(summary = "Delete Category", description = "Delete Category", tags = {"Categories"})
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(@Parameter(description = "Category Id", required = true) @PathVariable int categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
