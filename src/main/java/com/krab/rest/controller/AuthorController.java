package com.krab.rest.controller;

import com.krab.rest.domain.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author(firstName, lastName);
        final Author addedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(addedAuthor);
    }

    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable long id) throws ResourceNotFoundException {
        Author updatingAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
        updatingAuthor.setFirstName(author.getFirstName());
        updatingAuthor.setLastName(author.getLastName());
        final Author updatedAuthor = authorRepository.save(updatingAuthor);
        return ResponseEntity.ok(updatedAuthor);
    }

    @GetMapping("/getAuthors")
    public ResponseEntity<Iterable<Author>> getAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        return ResponseEntity.ok(authors);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity deleteAuthor(@PathVariable long id) throws ResourceNotFoundException {
        //Author deletingAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
        authorRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
