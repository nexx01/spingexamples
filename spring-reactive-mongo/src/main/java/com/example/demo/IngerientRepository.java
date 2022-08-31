package com.example.demo;

import com.example.demo.data.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
public interface IngerientRepository extends ReactiveCrudRepository<Ingredient, String> {

}
