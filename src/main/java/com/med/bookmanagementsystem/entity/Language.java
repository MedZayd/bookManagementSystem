package com.med.bookmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lang")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lang_id")
    private Long id;
    private String name;
    private String isoCode;
}
