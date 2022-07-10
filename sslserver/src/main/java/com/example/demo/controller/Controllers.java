package com.example.demo.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/")
//@ConfigurationProperties(prefix = "controller.properies", ignoreInvalidFields = false, ignoreUnknownFields = false)
//@Setter
//@NoArgsConstructor
public class Controllers {
//    private String first;
//    private int second;
//    private long third;
//    private boolean fourth;

//    @PostConstruct
//    public void dffd(){
//        System.out.println("-------------------");
//        System.out.println("It is parametr first with inject value: "+ first);
//    }

    @GetMapping("/test")
    public String getFirst(){
        return "hello from sslServer:" ;
    }

    @PostMapping("/test")
    public String getFirs2t(){
        return "hello from sslServer:" ;
    }
}