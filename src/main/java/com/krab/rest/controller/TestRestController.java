package com.krab.rest.controller;

import com.krab.rest.domain.Record;
import com.krab.rest.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/getRecordsCount")
    public long getRecordsCount() {
        return recordRepository.count();
    }

    @PostMapping("/addRecord")
    public String addRecord(@RequestParam String description) {
        Record record = new Record(description);
        recordRepository.save(record);
        return "Ok";
    }
}
