package com.krab.rest.services;

import com.google.common.collect.Lists;
import com.krab.rest.dto.AuthorDto;
import com.krab.rest.entity.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.mapper.AuthorMapper;
import com.krab.rest.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorsServiceImpl implements AuthorsService {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorsServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto addAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        final Author addedAuthor = authorRepository.save(author);
        return authorMapper.toDto(addedAuthor);
    }

    @Override
    public AuthorDto updateAuthor(long id, String firstName, String lastName) throws ResourceNotFoundException {
        Author updatingAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
        updatingAuthor.setFirstName(firstName);
        updatingAuthor.setLastName(lastName);
        final Author updatedAuthor = authorRepository.save(updatingAuthor);
        return authorMapper.toDto(updatedAuthor);
    }

    @Override
    public List<AuthorDto> getAuthors() {
        return Lists.newArrayList(authorRepository.findAll())
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
