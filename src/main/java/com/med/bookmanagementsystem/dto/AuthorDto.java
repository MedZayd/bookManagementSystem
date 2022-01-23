package com.med.bookmanagementsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
}
