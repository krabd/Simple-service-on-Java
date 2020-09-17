package com.krab.rest.services;

import com.krab.rest.domain.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface AuthorsService {

    Author addAuthor(String firstName, String lastName);

    Author updateAuthor(long id, String firstName, String lastName) throws ResourceNotFoundException;

    Iterable<Author> getAuthors();

    void deleteAuthor(long id);
}
