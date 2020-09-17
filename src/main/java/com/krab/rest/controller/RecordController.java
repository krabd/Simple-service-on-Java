package com.krab.rest.controller;

import com.krab.rest.domain.Record;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    private RecordsService recordsService;

    @Autowired
    public RecordController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @PostMapping("/api/addRecord")
    public ResponseEntity<Record> addRecord(@RequestParam String description, @RequestParam long authorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.addRecord(authorId, description));
    }

    @PutMapping("/api/updateRecord/{id}")
    public ResponseEntity<Record> updateRecord(@RequestBody Record record, @PathVariable long id, @RequestParam long authorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.updateRecord(id, authorId, record.getDescription()));
    }

    @GetMapping("/api/getRecordsByAuthorId")
    public ResponseEntity<List<Record>> getRecordByAuthorId(@RequestParam long authorId) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorId(authorId));
    }

    @GetMapping("/api/getRecordsByAuthorFirstName")
    public ResponseEntity<List<Record>> getByAuthorFirstName(@RequestParam String authorFirstName) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorFirstName(authorFirstName));
    }
}
