package com.med.bookmanagementsystem.restcontroller;

import com.med.bookmanagementsystem.dto.BookDto;
import com.med.bookmanagementsystem.dto.BookFormData;
import com.med.bookmanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @GetMapping("/formdata")
    public ResponseEntity<BookFormData> getFormData() {
        return ResponseEntity.ok().body(bookService.getBookFormData());
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.saveBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(name = "id") Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok().body("OK");
    }
}
