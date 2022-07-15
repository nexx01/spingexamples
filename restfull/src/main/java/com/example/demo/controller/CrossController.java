package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class CrossController {

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String retrieve(@PathVariable Long id) {
        return "return id: " + id;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/del/{id}")
    public void remove(@PathVariable Long id) {
        System.out.println("---->");
    }

    @RequestMapping(method = RequestMethod.GET,path = "/time/{id}")
    @CrossOrigin(origins = "http://example.com",maxAge = 2)
    public String retriveByTime(@PathVariable Long id) {
        return "retriveByTime return id: " + id;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/del/time/{id}")
    @CrossOrigin(origins = "http://example.com",maxAge = 2)
    public void delByTime(@PathVariable Long id) {
        System.out.println("--->delByTime return id: " + id);
    }
}
