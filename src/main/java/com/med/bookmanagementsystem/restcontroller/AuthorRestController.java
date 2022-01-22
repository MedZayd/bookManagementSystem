package com.med.bookmanagementsystem.restcontroller;

import com.med.bookmanagementsystem.dto.AuthorDto;
import com.med.bookmanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok().body(authorService.getAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable(name = "id") Long authorId) {
        return ResponseEntity.ok().body(authorService.getAuthorById(authorId));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity.ok().body(authorService.saveAuthor(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable(name = "id") Long authorId) {
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok().body("OK");
    }
}
