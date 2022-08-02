package com.example.demo;

import com.example.demo.rabbitMq.RabbitOrderMessagingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {
    private OrderMessagingService messageService;

    private final RabbitOrderMessagingService rabbitOrderMessagingService;

    public OrderApiController(
            OrderMessagingService messageService, RabbitOrderMessagingService rabbitOrderMessagingService) {
        this.messageService = messageService;
        this.rabbitOrderMessagingService = rabbitOrderMessagingService;

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String postOrder(@RequestBody SomeOrser order) {
        messageService.sendOrder(order);
        return "repo.save(order);";
    }

    @PostMapping(consumes = "application/json", path = "convertAndSendJms")
    @ResponseStatus(HttpStatus.CREATED)
    public String convertAndSendOrder(@RequestBody SomeOrser order) {
        messageService.sendOrder(order);
        return "repo.save(order);";
    }

    @PostMapping(consumes = "application/json", path = "sendWithAdditionalMessage")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendWithAdditionalMessage(@RequestBody SomeOrser order) {
        messageService.sendWithAdditionalMessage(order);
        return "repo.save(order);";
    }

    @PostMapping(consumes = "application/json", path = "convertandsendWithAdditionalMessage")
    @ResponseStatus(HttpStatus.CREATED)
    public String convertandsendWithAdditionalMessage(@RequestBody OrderNotSerializable order) {
        messageService.convertandsendWithAdditionalMessage(order);
        return "repo.save(order);";
    }

    @PostMapping(consumes = "application/json", path = "convertAndSendJmsNotSerializableEntity")
    @ResponseStatus(HttpStatus.CREATED)
    public String convertAndSendJmsNotSerializableEntity(@RequestBody OrderNotSerializable orderNotSerializable) {
       messageService.convertAndSendOrderNotSerialisable(orderNotSerializable);
        return "convertAndSendJmsNotSerializableEntity";
    }

    @RequestMapping(consumes = "application/json", path = "sendToRabbit",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public String sendToRabbit( SomeOrser someOrser) {

        rabbitOrderMessagingService.sendOrderInRabbit(someOrser);
        return "success Send to rabbitMq";
    }
}