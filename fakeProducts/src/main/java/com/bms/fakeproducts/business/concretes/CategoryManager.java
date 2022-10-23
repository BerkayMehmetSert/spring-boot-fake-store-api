package com.bms.fakeproducts.business.concretes;

import com.bms.fakeproducts.business.abstracts.CategoryService;
import com.bms.fakeproducts.dataAccess.CategoryDao;
import com.bms.fakeproducts.entities.concretes.Category;
import com.bms.fakeproducts.entities.dtos.CategoryDto;
import com.bms.fakeproducts.exceptions.AllReadyException;
import com.bms.fakeproducts.exceptions.FieldNotEmptyException;
import com.bms.fakeproducts.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryDao categoryDao;
    private ModelMapper modelMapper;

    public CategoryManager(CategoryDao categoryDao, ModelMapper modelMapper) {
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryDao.findAll();

        List<CategoryDto> categoryDtoList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        if (categoryDtoList.isEmpty()) throw new NotFoundException("No category found with the given id");
        return categoryDtoList;
    }

    @Override
    public CategoryDto getById(int categoryId) {
        Optional<Category> category = this.categoryDao.findById(categoryId);

        if (category.isEmpty()) throw new NotFoundException("No category found with the given id");
        return modelMapper.map(category.get(), CategoryDto.class);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if (categoryDao.existsByCategoryName(category.getCategoryName())) {
            throw new AllReadyException("Category already exists");

        } else if (category.getCategoryName().isEmpty()) {
            throw new FieldNotEmptyException("Category name cannot be empty");

        }
        return modelMapper.map(categoryDao.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(int categoryId, CategoryDto categoryDto) {

        Optional<Category> category = this.categoryDao.findById(categoryId);

        if (category.isEmpty() || categoryDao.existsByCategoryName(categoryDto.getCategoryName())) {
            throw new AllReadyException("Category already exists");

        } else if (categoryDto.getCategoryName().isEmpty()) {
            throw new FieldNotEmptyException("Category name cannot be empty");

        }
        category.get().setCategoryName(categoryDto.getCategoryName());
        return modelMapper.map(categoryDao.save(category.get()), CategoryDto.class);
    }

    @Override
    public CategoryDto deleteCategory(int categoryId) {
        Category category = categoryDao.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("No category found with the given id"));
        categoryDao.delete(category);
        return modelMapper.map(category, CategoryDto.class);
    }

}
