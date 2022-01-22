package com.med.bookmanagementsystem.service;

import com.med.bookmanagementsystem.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();
    List<CategoryDto> getParentCategories();
    List<CategoryDto> getSubCategories();
    CategoryDto saveCategory(CategoryDto author);
    void deleteCategoryById(Long id);
}
