package com.krab.rest.controller;

import com.krab.rest.dto.RecordDto;
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
    public ResponseEntity<RecordDto> addRecord(@RequestParam String description, @RequestParam long authorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.addRecord(authorId, description));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable long id, @RequestBody RecordDto record) throws ResourceNotFoundException {
        return ResponseEntity.ok(recordsService.updateRecord(id, record.getAuthorId(), record.getDescription()));
    }

    @GetMapping("/getByAuthorId")
    public ResponseEntity<List<RecordDto>> getRecordsByAuthorId(@RequestParam long authorId) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorId(authorId));
    }

    @GetMapping("/getByAuthorFirstName")
    public ResponseEntity<List<RecordDto>> getRecordsByAuthorFirstName(@RequestParam String authorFirstName) {
        return ResponseEntity.ok(recordsService.getRecordsByAuthorFirstName(authorFirstName));
    }
}
