package com.example.demo;

import com.example.demo.Service.TacoOrderAggregateService;
import com.example.demo.data.Taco;
import com.example.demo.data.TacoOrder;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TacoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataR2dbcTest
@DirtiesContext
public class TacoOrderAggregateServiceTests {

    @Autowired
    TacoRepository tacoRepository;

    @Autowired
    OrderRepository repository;

    TacoOrderAggregateService service;

    @BeforeEach
    public void setup() {
        this.service = new TacoOrderAggregateService(tacoRepository, repository);
    }

    @Test
    public void shouldSaveAndFetchOrders() {
        TacoOrder newOrder = new TacoOrder();
        newOrder.setDeliveryName("Test Customer");
        newOrder.setDeliveryStreet("1234 North Street");
        newOrder.setDeliveryCity("Notrees");
        newOrder.setDeliveryState("TX");
        newOrder.setDeliveryZip("79759");
        newOrder.setCcNumber("4111111111111111");
        newOrder.setCcExpiration("12/24");
        newOrder.setCcCVV("123");
        newOrder.addTaco(new Taco("Test Taco One"));
        newOrder.addTaco(new Taco("Test Taco Two"));
        StepVerifier.create(service.save(newOrder))
                .assertNext(this::assertOrder)
                .verifyComplete();
        StepVerifier.create(service.findById(1L))
                .assertNext(this::assertOrder)
                .verifyComplete();
    }

    private void assertOrder(TacoOrder savedOrder) {
        assertThat(savedOrder.getId()).isEqualTo(1L);
        assertThat(savedOrder.getDeliveryName()).isEqualTo("Test Customer");
    }
}
