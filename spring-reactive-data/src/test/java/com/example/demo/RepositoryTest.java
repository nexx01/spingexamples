//package com.example.demo;
//
//import com.example.demo.Service.OrderService;
//import com.example.demo.data.TacoOrder;
//import com.example.demo.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class RepositoryTest {
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    OrderService orderService;
//
//    @Test
//    void TestPrintAll() throws InterruptedException {
//        TacoOrder tacoOrder = new TacoOrder();
//        tacoOrder.setDeliveryName("11111111111111111111111111111111111111");
//        orderRepository.save(tacoOrder);
//Thread.sleep(1000);
//        Long count = orderRepository.count().block();
//        assertEquals(1, count);
//
//        orderService.printAllDateInRepo();
//
//        Thread.sleep(1000);
//    }
//
//    TacoOrder getOrder() {
//        TacoOrder tacoOrder = new TacoOrder();
//
//
//
//    }
//}
