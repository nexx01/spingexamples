package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

//    @GetMapping(value = "/student/v3/{id}",produces = MediaType.APPLICATION_ATOM_XML_VALUE)
//    public ResponseEntity<Student> getV3(@PathVariable("id") int id) {
//        return ResponseEntity.ok(new Student(id, "Robert", "Miller", "BB"));
//    }

    @GetMapping(value = "/student/v2/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getV2(@PathVariable("id") int id) {
        return ResponseEntity.ok(new Student(id, "Kevin", "Cruyff", "AA"));
    }


}
