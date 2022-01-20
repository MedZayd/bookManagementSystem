package com.med.bookmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;

    @OneToOne(mappedBy = "photo")
    private Book book;
}
