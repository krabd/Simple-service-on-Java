package com.krab.rest.controller;

import com.krab.rest.domain.Record;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        recordRepository.save(record);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/filterRecords")
    public ResponseEntity<List<Record>> filter(@RequestParam String description) {
        List<Record> records = recordRepository.findByDescriptionContaining(description);
        return ResponseEntity.ok(records);
    }
}
