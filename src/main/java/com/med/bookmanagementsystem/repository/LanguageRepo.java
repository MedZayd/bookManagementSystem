package com.med.bookmanagementsystem.repository;

import com.med.bookmanagementsystem.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
}
