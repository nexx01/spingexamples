package com.example.demo.Service;

import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void printAllDateInRepo() {
        orderRepository.findAll()
                .doOnNext(order -> {
                    System.out.println(
                            "Deliver to: " + order.getDeliveryName()
                    );
                }).subscribe();
    }
}
