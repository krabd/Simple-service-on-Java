package com.krab.rest.controller;

import com.krab.rest.dto.AuthorDto;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.services.AuthorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Slf4j
public class AuthorController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto author) {
        log.info("Add author Value = " + author);
        return ResponseEntity.ok(authorsService.addAuthor(author.getFirstName(), author.getLastName()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody AuthorDto author, @PathVariable long id) throws ResourceNotFoundException {
        log.info("Update author Id = " + id + "Value = " + author);
        return ResponseEntity.ok(authorsService.updateAuthor(id, author.getFirstName(), author.getLastName()));
    }

    @GetMapping("/get")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        log.info("Get authors");
        return ResponseEntity.ok(authorsService.getAuthors());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAuthor(@PathVariable long id) {
        log.info("Delete author Id = " + id);
        authorsService.deleteAuthor(id);
        return ResponseEntity.ok(true);
    }
}
