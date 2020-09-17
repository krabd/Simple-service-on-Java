package com.krab.rest.controller;

import com.krab.rest.domain.Author;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.domain.Record;
import com.krab.rest.repositories.AuthorRepository;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/getRecordsCount")
    public long getRecordsCount() {
        return recordRepository.count();
    }

    @PostMapping("/addRecord")
    public ResponseEntity<Record> addRecord(@RequestParam String description, @RequestParam long authorId) throws ResourceNotFoundException {
        Record record = new Record(description);
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        record.setAuthor(author);
        final Record addedRecord = recordRepository.save(record);
        return ResponseEntity.ok(addedRecord);
    }

    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<Record> updateRecord(@RequestBody Record record, @PathVariable long id, @RequestParam long authorId) throws ResourceNotFoundException {
        Record updatingRecord = recordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for this id :: " + id));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + authorId));
        updatingRecord.setAuthor(author);
        updatingRecord.setDescription(record.getDescription());
        final Record updatedRecord = recordRepository.save(updatingRecord);
        return ResponseEntity.ok(updatedRecord);
    }

    @GetMapping("/getRecordsByAuthorId")
    public ResponseEntity<List<Record>> getRecordByAuthorId(@RequestParam long authorId) {
        List<Record> records = recordRepository.findByAuthorId(authorId);
        return ResponseEntity.ok(records);
    }
}
