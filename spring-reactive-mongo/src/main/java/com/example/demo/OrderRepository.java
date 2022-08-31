package com.example.demo;

import com.example.demo.data.TacoOrder;
import com.example.demo.data.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, String> {
    Flux<TacoOrder> findByUserOrderByPlacedAtDesc(User use, Pageable pageable);

}
