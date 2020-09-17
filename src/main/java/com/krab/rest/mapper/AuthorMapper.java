package com.krab.rest.mapper;

import com.krab.rest.dto.AuthorDto;
import com.krab.rest.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDto> {

    @Autowired
    public AuthorMapper() {
        super(Author.class, AuthorDto.class);
    }
}