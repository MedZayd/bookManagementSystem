package com.med.bookmanagementsystem.restcontroller;

import com.med.bookmanagementsystem.dto.CategoryDto;
import com.med.bookmanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping("/parents")
    public ResponseEntity<List<CategoryDto>> getParentCategories() {
        return ResponseEntity.ok().body(categoryService.getParentCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.saveCategory(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().body("OK");
    }
}
