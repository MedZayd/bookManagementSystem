package com.med.bookmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private Set<Book> bookSet;

    public String getFullName() {
        return this.firstName.concat(" ").concat(this.lastName);
    }
}
