package com.med.bookmanagementsystem.repository;

import com.med.bookmanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull();
    List<Category> findByParentIsNotNull();
    List<Category> findByParent(Category category);
}
