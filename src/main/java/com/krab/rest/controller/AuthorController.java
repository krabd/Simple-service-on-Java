package com.krab.rest.controller;

import com.krab.rest.entity.Author;
import com.krab.rest.dto.AuthorDto;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(authorsService.addAuthor(firstName, lastName));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody Author author, @PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(authorsService.updateAuthor(id, author.getFirstName(), author.getLastName()));
    }

    @GetMapping("/get")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(authorsService.getAuthors());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAuthor(@PathVariable long id) {
        authorsService.deleteAuthor(id);
        return ResponseEntity.ok(true);
    }
}
