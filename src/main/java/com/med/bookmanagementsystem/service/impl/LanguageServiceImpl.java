package com.med.bookmanagementsystem.service.impl;

import com.med.bookmanagementsystem.dto.LanguageDto;
import com.med.bookmanagementsystem.entity.Language;
import com.med.bookmanagementsystem.exception.LanguageNotFoundException;
import com.med.bookmanagementsystem.repository.LanguageRepo;
import com.med.bookmanagementsystem.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<LanguageDto> getAll() {
        List<Language> languages = languageRepo.findAll();
        return languages.stream().map(language -> mapper.map(language, LanguageDto.class)).collect(Collectors.toList());
    }

    @Override
    public LanguageDto save(LanguageDto languageDto) {
        Language language = mapper.map(languageDto, Language.class);
        language = languageRepo.save(language);
        return mapper.map(language, LanguageDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Language language = languageRepo.findById(id).orElseThrow(LanguageNotFoundException::new);
        languageRepo.delete(language);
    }
}
