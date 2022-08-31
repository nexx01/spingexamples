package com.example.demo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
//@RestResource(rel = "tacos", path = "tacos")
@Document
public class Taco {
    @Id
    private String id;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    private Date createdAt = new Date();
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
