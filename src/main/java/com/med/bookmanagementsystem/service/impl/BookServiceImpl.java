package com.med.bookmanagementsystem.service.impl;

import com.med.bookmanagementsystem.dto.*;
import com.med.bookmanagementsystem.entity.*;
import com.med.bookmanagementsystem.exception.AuthorNotFoundException;
import com.med.bookmanagementsystem.exception.BookNotFoundException;
import com.med.bookmanagementsystem.exception.CategoryNotFoundException;
import com.med.bookmanagementsystem.exception.LanguageNotFoundException;
import com.med.bookmanagementsystem.repository.*;
import com.med.bookmanagementsystem.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    @Override
    public List<BookDto> getBooks() {
        return bookRepo.findAll().stream().map(book -> {
            BookDto bookDto = mapper.map(book, BookDto.class);
            Set<String> categories = new HashSet<>();
            Set<Long> ids = new HashSet<>();
            book.getCategories().forEach(cat -> {
                categories.add(cat.getName());
                ids.add(cat.getId());
            });
            bookDto.setCategoryIds(ids);
            bookDto.setCategories(categories);
            Set<String> languages = new HashSet<>();
            Set<Long> langIds = new HashSet<>();
            book.getLanguages().forEach(lang -> {
                languages.add(lang.getName());
                langIds.add(lang.getId());
            });
            bookDto.setLanguages(languages);
            bookDto.setLanguagesIds(langIds);
            return bookDto;
        }).collect(Collectors.toList());
    }

    @Override
    public BookFormData getBookFormData() {
        List<AuthorDto> authors = authorRepo.findAll().stream().map(a -> mapper.map(a, AuthorDto.class)).collect(Collectors.toList());
        List<LanguageDto> languages = languageRepo.findAll().stream().map(l -> mapper.map(l, LanguageDto.class)).collect(Collectors.toList());
        List<CategoryDto> categories = categoryRepo.findByParentIsNotNull().stream().map(c -> mapper.map(c, CategoryDto.class)).collect(Collectors.toList());;
        BookFormData formData = new BookFormData();
        formData.setAuthors(authors);
        formData.setCategories(categories);
        formData.setLanguages(languages);
        return formData;
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = new Book();
        if (bookDto.getId() != null) {
            book.setId(bookDto.getId());
        }
        book.setTitle(bookDto.getTitle());
        book.setTotalPages(bookDto.getTotalPages());
        book.setPublishedDate(bookDto.getPublishedDate());
        if (!CollectionUtils.isEmpty(bookDto.getCategoryIds())) {
            Set<Category> categories =
                    bookDto.getCategoryIds()
                            .stream()
                            .map(id -> categoryRepo.findById(id).orElseThrow(CategoryNotFoundException::new))
                            .collect(Collectors.toSet());
            book.setCategories(categories);
        }
        if (!CollectionUtils.isEmpty(bookDto.getLanguagesIds())) {
            Set<Language> languages =
                    bookDto.getLanguagesIds()
                            .stream()
                            .map(id -> languageRepo.findById(id).orElseThrow(LanguageNotFoundException::new))
                            .collect(Collectors.toSet());
            book.setLanguages(languages);
        }
        Author author = authorRepo.findById(bookDto.getAuthorId()).orElseThrow(AuthorNotFoundException::new);
        book.setAuthor(author);
        if (Objects.nonNull(bookDto.getPhotoLink())) {
            Photo photo = new Photo();
            photo.setId(bookDto.getPhotoId());
            photo.setLink(bookDto.getPhotoLink());
            book.setPhoto(photo);
        }
        Book savedBook = bookRepo.save(book);
        return mapper.map(savedBook, BookDto.class);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
        if (Objects.nonNull(book.getPhoto())) {
            photoRepo.delete(book.getPhoto());
        }
        bookRepo.delete(book);
    }
}
