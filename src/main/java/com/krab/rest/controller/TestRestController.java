package com.krab.rest.controller;

import com.krab.rest.exceptions.ResourceNotFoundException;
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
        final Record addedRecord = recordRepository.save(record);
        return ResponseEntity.ok(addedRecord);
    }

    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<Record> updateRecord(@RequestBody Record record, @PathVariable long id) throws ResourceNotFoundException {
        Record updatingRecord = recordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found for this id :: " + id));
        updatingRecord.setDescription(record.getDescription());
        final Record updatedRecord = recordRepository.save(updatingRecord);
        return ResponseEntity.ok(updatedRecord);
    }

    @PostMapping("/filterRecords")
    public ResponseEntity<List<Record>> filter(@RequestParam String description) {
        List<Record> records = recordRepository.findByDescriptionContaining(description);
        return ResponseEntity.ok(records);
    }
}
