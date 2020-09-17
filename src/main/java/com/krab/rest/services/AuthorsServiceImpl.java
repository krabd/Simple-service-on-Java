package com.krab.rest.services;

import com.krab.rest.domain.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorsServiceImpl implements AuthorsService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorsServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        final Author addedAuthor = authorRepository.save(author);
        return addedAuthor;
    }

    @Override
    public Author updateAuthor(long id, String firstName, String lastName) throws ResourceNotFoundException {
        Author updatingAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
        updatingAuthor.setFirstName(firstName);
        updatingAuthor.setLastName(lastName);
        final Author updatedAuthor = authorRepository.save(updatingAuthor);
        return updatedAuthor;
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
