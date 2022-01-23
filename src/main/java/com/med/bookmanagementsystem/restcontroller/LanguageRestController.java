package com.med.bookmanagementsystem.restcontroller;

import com.med.bookmanagementsystem.dto.LanguageDto;
import com.med.bookmanagementsystem.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageRestController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getAll() {
        return ResponseEntity.ok().body(languageService.getAll());
    }

    @PostMapping
    public ResponseEntity<LanguageDto> save(@RequestBody LanguageDto languageDto) {
        return ResponseEntity.ok().body(languageService.save(languageDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable(name = "id") Long id) {
        languageService.deleteById(id);
        return ResponseEntity.ok().body("OK");
    }
}
