package com.med.bookmanagementsystem.service.impl;

import com.med.bookmanagementsystem.dto.AuthorDto;
import com.med.bookmanagementsystem.entity.Author;
import com.med.bookmanagementsystem.exception.AuthorNotFoundException;
import com.med.bookmanagementsystem.repository.AuthorRepo;
import com.med.bookmanagementsystem.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private JavaMailSender emailSender;

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
        author = authorRepo.save(author);
        if (Objects.nonNull(author.getEmail())) {
            notifyAuthor(author.getEmail());
        }
        return mapper.map(author, AuthorDto.class);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepo.deleteById(id);
    }

    public void notifyAuthor(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@book-management-system.com");
        message.setTo(mail);
        message.setSubject("Added to database");
        message.setText("You have been added to our database. Congrats !");
        emailSender.send(message);
    }
}
