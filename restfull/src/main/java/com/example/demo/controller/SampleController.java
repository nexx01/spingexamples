package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/api/sample",produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class SampleController {

    @GetMapping()
    public Iterable<Integer> someStrings() {

        return List.of(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @GetMapping(value = "/order", produces = "application/json")
    public Order getOrder() {

        HashMap<Integer, Product> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, new Product(1, 1, 2));
        objectObjectHashMap.put(2, new Product(2, 2, 2));
        objectObjectHashMap.put(3, new Product(3, 3, 2));
        return new Order("2", 2, objectObjectHashMap);
    }

    @PutMapping(value = "/order", produces = "application/json")
    public Order putOrder(@RequestBody Order order) {

        HashMap<Integer, Product> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, new Product(1, 1, 2));
        objectObjectHashMap.put(2, new Product(2, 2, 2));
        objectObjectHashMap.put(3, new Product(3, 3, 2));
        System.out.println("---->8 put in controller ");
        return new Order("2", 2, objectObjectHashMap);
    }

    @RequestMapping(value = "/order", method = POST)
    public Order createOrder(Order order) {
        System.out.println("---> createOrder");
        return order;
    }

    @PostMapping("/order/security")
    @PreAuthorize("#{hasRole('ADMIN')")
    public Order postOrder(Order order) {
        System.out.println("-->  @PostMapping(\"/order/security/\")\n");
        return order;
    }

    @DeleteMapping("/order/security")
    @PreAuthorize("#{hasRole('ADMIN')}")
    public void deleteOrderSec(Order order) {
        System.out.println("-->  @DeleteMapping(\"/order/security/\")\n");
    }



    @RequestMapping(value = "/ex/foos", method = GET)
    @ResponseBody
    public String getFoosBySimplePath() {

        return "Get some Foos";
    }
    @RequestMapping(value = "/ex/foos",  headers = { "key1=val1" }, method = GET)
    @ResponseBody
    public String getFoosWithHeader() {
        return "Get some Foos with Header";
    }

    @RequestMapping(value = "/ex/foos/{id}", method = GET)
    @ResponseBody
    public String getFoosBySimplePathWithPathVariable(
            @PathVariable("id") long id) {
        return "Get a specific Foo with id=" + id;
    }

    @RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = GET)
    @ResponseBody
    public String getFoosBySimplePathWithPathVariables
            (@PathVariable long fooid, @PathVariable long barid) {
        return "Get a specific Bar with id=" + barid +
                " from a Foo with id=" + fooid;
    }


    @RequestMapping(value = "/ex/bars/{numericId:[\\d]+}", method = GET)
    @ResponseBody
    public String getBarsBySimplePathWithPathVariable(
            @PathVariable long numericId) {
        return "Get a specific Bar with id=" + numericId;
    }

    @RequestMapping(value = "/ex/bars", method = GET)
    @ResponseBody
    public String getBarBySimplePathWithRequestParam(
            @RequestParam("id") long id) {
        return "Get a specific Bar with id=" + id;
    }

    @RequestMapping(
            value = "/ex/bars",
            params = { "id", "second" },
            method = GET)
    @ResponseBody
    public String getBarBySimplePathWithExplicitRequestParams(
            @RequestParam("id") long id) {
        return "Narrow Get a specific Bar with id=" + id;
    }

    @RequestMapping(
            value = { "/ex/advanced/bars", "/ex/advanced/foos" },
            method = GET)
    @ResponseBody
    public String getFoosOrBarsByPath() {
        return "Advanced - Get some Foos or Bars";
    }

    @RequestMapping(
            value = "/ex/foos/multiple",
            method = { RequestMethod.PUT, RequestMethod.POST }
    )
    @ResponseBody
    public String putAndPostFoos() {
        return "Advanced - PUT and POST within single method";
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    @ResponseBody
    public String getFallback() {
        return "Fallback for GET Requests";
    }

    @RequestMapping(
            value = "*",
            method = { RequestMethod.GET, RequestMethod.POST  })
    @ResponseBody
    public String allFallback() {
        return "Fallback for All Requests";
    }


//    @RequestMapping(
//            value = "/ex/foos",
//            method = GET,
//            headers = "Accept=application/json,")
//    @ResponseBody
//    public String getFoosAsJsonFromBrowser() {
//        return "Get some Foos with Header Old";
//    }

    @RequestMapping(
            value = "/ex/foos",
            method = GET,
            produces = { "application/json", "application/xml" }
    )

    @ResponseBody
    public String getFoosAsJsonFromREST() {
        return "Get some Foos with Header New";
    }
    @RequestMapping(value = "/ex/foos",  headers = { "key1=val1", "key2=val2" }, method = GET)
    @ResponseBody
    public String getFoosWithMultiHeader() {
        return "Get some Foos with multiHeader";
    }
    @GetMapping(params = "recent", produces = "application/json")
    public Order getOrderParams() {

        HashMap<Integer, Product> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, new Product(1, 1, 2));
        objectObjectHashMap.put(2, new Product(2, 2, 2));
        objectObjectHashMap.put(3, new Product(3, 3, 2));
        return new Order("2", 2, objectObjectHashMap);
    }

    @DeleteMapping("/order")
    public Order deleteOrder(Order order) {
        System.out.println("--->@DeleteMapping ");
        return order;
    }



    @GetMapping(value = "/order/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Order getOrderXML() {

        HashMap<Integer, Product> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, new Product(1, 1, 2));
        objectObjectHashMap.put(2, new Product(2, 2, 2));
        objectObjectHashMap.put(3, new Product(3, 3, 2));
        return new Order("2", 2, objectObjectHashMap);
    }
}
