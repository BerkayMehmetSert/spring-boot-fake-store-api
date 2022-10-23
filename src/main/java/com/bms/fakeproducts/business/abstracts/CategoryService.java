package com.bms.fakeproducts.business.abstracts;

import com.bms.fakeproducts.entities.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    CategoryDto getById(int categoryId);

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(int categoryId, CategoryDto categoryDto);

    CategoryDto deleteCategory(int categoryId);
}
