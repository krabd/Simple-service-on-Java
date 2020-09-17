package com.krab.rest.controller;

import com.krab.rest.entity.Record;
import com.krab.rest.exceptions.ResourceNotFoundException;
import com.krab.rest.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
public class RecordController {

    private final RecordsService recordsService;

    @Autowired
    public RecordController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Record> addRecord(@RequestParam String description, @RequestParam long authorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.addRecord(authorId, description));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Record> updateRecord(@RequestBody Record record, @PathVariable long id, @RequestParam long authorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.updateRecord(id, authorId, record.getDescription()));
    }

    @GetMapping("/getByAuthorId")
    public ResponseEntity<List<Record>> getRecordsByAuthorId(@RequestParam long authorId) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorId(authorId));
    }

    @GetMapping("/getByAuthorFirstName")
    public ResponseEntity<List<Record>> getRecordsByAuthorFirstName(@RequestParam String authorFirstName) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorFirstName(authorFirstName));
    }
}
