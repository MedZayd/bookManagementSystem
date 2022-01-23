package com.med.bookmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookFormData {
    private List<AuthorDto> authors;
    private List<CategoryDto> categories;
    private List<LanguageDto> languages;
}
