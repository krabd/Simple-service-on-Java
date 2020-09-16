package com.krab.rest.controller;

import com.krab.rest.ResourceNotFoundException;
import com.krab.rest.domain.Record;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/getRecordsCount")
    public long getRecordsCount() {
        return recordRepository.count();
    }

    @PostMapping("/addRecord")
    public ResponseEntity<Record> addRecord(@RequestParam String description) {
        Record record = new Record(description);
        final Record updatedRecord = recordRepository.save(record);
        return ResponseEntity.ok(updatedRecord);
    }

    @PutMapping("/updateRecord")
    public ResponseEntity<Record> updateRecord(@RequestParam Long id, @RequestBody String description) throws ResourceNotFoundException {
        Record record = recordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for this id :: " + id));
        record.setDescription(description);
        final Record updatedRecord = recordRepository.save(record);
        return ResponseEntity.ok(updatedRecord);
    }

    @PostMapping("/filterRecords")
    public ResponseEntity<List<Record>> filter(@RequestParam String description) {
        List<Record> records = recordRepository.findByDescriptionContaining(description);
        return ResponseEntity.ok(records);
    }
}
