package com.krab.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @RequestMapping("/getRecordsCount")
    public Integer getRecordsCount() {
        return 0;
    }
}
