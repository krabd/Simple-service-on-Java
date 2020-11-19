package com.krab.rest.controller;

import com.krab.rest.services.DmnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dmn")
@Slf4j
public class DmnController {

    private final DmnService dmnService;

    @Autowired
    public DmnController(DmnService dmnService) {
        this.dmnService = dmnService;
    }

    @GetMapping("/get")
    public String validate(@RequestParam String season, @RequestParam int guestsCount) {
        return dmnService.validateCafe(season, guestsCount);
    }
}
