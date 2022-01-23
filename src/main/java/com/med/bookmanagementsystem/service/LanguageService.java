package com.med.bookmanagementsystem.service;

import com.med.bookmanagementsystem.dto.LanguageDto;

import java.util.List;

public interface LanguageService {
    List<LanguageDto> getAll();
    LanguageDto save(LanguageDto languageDto);
    void deleteById(Long id);
}
