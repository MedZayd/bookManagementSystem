package com.med.bookmanagementsystem.service.impl;

import com.med.bookmanagementsystem.dto.AuthorDto;
import com.med.bookmanagementsystem.entity.Author;
import com.med.bookmanagementsystem.exception.AuthorNotFoundException;
import com.med.bookmanagementsystem.repository.AuthorRepo;
import com.med.bookmanagementsystem.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AuthorDto> getAuthors() {
        return authorRepo.findAll().stream().map(author -> mapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        return mapper.map(authorRepo.findById(id).orElseThrow(AuthorNotFoundException::new), AuthorDto.class);
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author author = mapper.map(authorDto, Author.class);
        return mapper.map(authorRepo.save(author), AuthorDto.class);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepo.deleteById(id);
    }
}
