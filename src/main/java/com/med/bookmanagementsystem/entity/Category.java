package com.med.bookmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id", nullable=false)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;
}
