package com.med.bookmanagementsystem.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private Integer totalPages;
    private Date publishedDate;
    private Long authorId;
    private String authorName;
    private Long photoId;
    private String photoLink;
    private Set<String> categories;
    private Set<Long> categoryIds;
}
