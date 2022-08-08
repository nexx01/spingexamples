package com.example.demo.kafka;

import com.example.demo.SomeOrser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inKafka/orders/")
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
public class WithKafkaControllerController {

    @Autowired
    KafkaOrderMessagingService kafkaOrderMessagingService;

    @PostMapping("sendDefault")
    public String sendDefault(@RequestBody SomeOrser someOrser) {
        kafkaOrderMessagingService.sendDefault(someOrser);
        return "sendDefault method";
    }

    @PostMapping("send")
    public String simpleSend(@RequestBody SomeOrser someOrser) {
        kafkaOrderMessagingService.sendOrder(someOrser);
        return "simpleSend";
    }
}
