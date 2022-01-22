package com.med.bookmanagementsystem.service;

import com.med.bookmanagementsystem.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAuthors();
    AuthorDto getAuthorById(Long id);
    AuthorDto saveAuthor(AuthorDto author);
    void deleteAuthorById(Long id);
}
