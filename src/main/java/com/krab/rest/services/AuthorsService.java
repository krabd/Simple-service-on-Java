package com.krab.rest.services;

import com.krab.rest.dto.AuthorDto;
import com.krab.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorsService {

    AuthorDto addAuthor(String firstName, String lastName);

    AuthorDto updateAuthor(long id, String firstName, String lastName) throws ResourceNotFoundException;

    List<AuthorDto> getAuthors();

    void deleteAuthor(long id);
}
