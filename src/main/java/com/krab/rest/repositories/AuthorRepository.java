package com.krab.rest.repositories;

import com.krab.rest.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
