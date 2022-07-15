package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/sample",produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class SampleController {

    @GetMapping()
    public Iterable<Integer> someStrings() {

        return List.of(1, 2, 3, 4, 5, 6, 7, 8);
    }
}
