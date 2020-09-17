package com.krab.rest.controller;

import com.krab.rest.domain.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping("/api/addAuthor")
    public ResponseEntity<Author> addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(authorsService.addAuthor(firstName, lastName));
    }

    @PutMapping("/api/updateAuthor/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(authorsService.updateAuthor(id, author.getFirstName(), author.getLastName()));
    }

    @GetMapping("/api/getAuthors")
    public ResponseEntity<Iterable<Author>> getAuthors() {
        return ResponseEntity.ok(authorsService.getAuthors());
    }

    @DeleteMapping("/api/deleteAuthor/{id}")
    public ResponseEntity deleteAuthor(@PathVariable long id) {
        authorsService.deleteAuthor(id);
        return ResponseEntity.ok(true);
    }
}
