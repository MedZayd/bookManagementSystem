package com.med.bookmanagementsystem.config;

import com.med.bookmanagementsystem.dto.BookDto;
import com.med.bookmanagementsystem.dto.CategoryDto;
import com.med.bookmanagementsystem.entity.Book;
import com.med.bookmanagementsystem.entity.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Category.class, CategoryDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getParent().getId(), CategoryDto::setParentId);
            mapper.map(src -> src.getParent().getName(), CategoryDto::setParentName);
        });
        modelMapper.typeMap(Book.class, BookDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getAuthor().getId(), BookDto::setAuthorId);
            mapper.map(src -> src.getAuthor().getFullName(), BookDto::setAuthorName);
            mapper.map(src -> src.getPhoto().getId(), BookDto::setPhotoId);
            mapper.map(src -> src.getPhoto().getLink(), BookDto::setPhotoLink);
        });
        return modelMapper;
    }
}
