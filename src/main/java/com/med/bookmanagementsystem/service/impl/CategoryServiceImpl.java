package com.med.bookmanagementsystem.service.impl;

import com.med.bookmanagementsystem.dto.CategoryDto;
import com.med.bookmanagementsystem.entity.Category;
import com.med.bookmanagementsystem.exception.CategoryNotFoundException;
import com.med.bookmanagementsystem.repository.CategoryRepo;
import com.med.bookmanagementsystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepo.findAll().stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getParentCategories() {
        return categoryRepo.findByParentIsNull().stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getSubCategories() {
        return categoryRepo.findByParentIsNotNull().stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        if (Objects.nonNull(categoryDto.getParentId())) {
            Category parentCategory = categoryRepo.findById(categoryDto.getParentId()).orElseThrow(CategoryNotFoundException::new);
            category.setParent(parentCategory);
        }
        Category updateCat = categoryRepo.save(category);
        return mapper.map(updateCat, CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (Objects.isNull(category.getParent())) {
            categoryRepo.deleteAll(categoryRepo.findByParent(category));
        }
        categoryRepo.deleteById(id);
    }
}
