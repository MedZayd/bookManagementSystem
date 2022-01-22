package com.med.bookmanagementsystem.service;

import com.med.bookmanagementsystem.dto.BookDto;
import com.med.bookmanagementsystem.dto.BookFormData;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks();
    BookFormData getBookFormData();
    BookDto saveBook(BookDto author);
    void deleteBookById(Long id);
}
