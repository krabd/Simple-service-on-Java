package com.krab.rest.repositories;

import com.krab.rest.domain.Author;
import com.krab.rest.domain.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {

    List<Record> findByAuthor(Author author);

}
