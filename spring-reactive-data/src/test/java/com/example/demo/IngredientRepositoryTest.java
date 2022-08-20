package com.example.demo;

import com.example.demo.data.Ingredient;
import com.example.demo.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataR2dbcTest
@EnableAutoConfiguration
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepo;

    @BeforeEach
    public void setup() {
        Flux<Ingredient> deleteAndInsert = ingredientRepo.deleteAll()
                .thenMany(ingredientRepo.saveAll(
                        Flux.just(
                                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                                new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE)
                        )));

        StepVerifier.create(deleteAndInsert)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldSaveAndFetchIngredients() {
        StepVerifier.create(ingredientRepo.findAll())
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(ingrediens -> {
                    assertThat(ingrediens.size()).isEqualTo(3);
                    assertTrue(ingrediens.contains(
                            new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN)));
                    assertTrue(ingrediens.contains(
                            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)
                    ));

                    assertTrue(ingrediens.contains(
                            new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE)
                    ));
                }).verifyComplete();

        StepVerifier.create(
                        ingredientRepo.findBySlug("FLTO"))
                .assertNext(ingredient -> {
                    ingredient.equals(
                            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                });
    }
}
