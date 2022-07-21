package com.example.demo.selfClient;

import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SelfClientService {
    @Autowired
    @Qualifier("fromBuilderRestTemplate")
    RestTemplate fromBuilderRestTemplate;

    @Autowired
    @Qualifier("simpleRestTemplate")
    RestTemplate simpleRestTemplate;

    @Autowired
    @Qualifier("simpleRestTemplate2")
    RestTemplate simpleRestTemplate2;

    @Autowired
    @Qualifier("AdminbuilderRestTemplate")
    RestTemplate restTemplateAdmin;

    public Order order(String oderId) {
        return fromBuilderRestTemplate.getForObject("http://localhost:8080/api/sample/order",
                Order.class);
    }

    public Order orderSimple(String oderId) {

        try {
            // request url
            String url = "http://localhost:8080/api/sample/order";

            // create auth credentials
            String authStr = "user:password";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            // get JSON response
            String json = response.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fromBuilderRestTemplate.getForObject("http://localhost:8080/api/sample/order",
                Order.class);
    }

    public ResponseEntity<Order> orderSimple2(String oderId) {
        simpleRestTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("user", "password"));

        return simpleRestTemplate.exchange("http://localhost:8080/api/sample/order",
                HttpMethod.GET,null,
                Order.class);
    }

    public Order getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return simpleRestTemplate.getForObject("http://localhost:8080/api/sample/order",
                Order.class, urlVariables);
    }

    public Order getIngredientById2(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/api/sample/order")
                .build(urlVariables);
        return simpleRestTemplate.getForObject(url, Order.class);
    }

    public String updateOrder(Order order) {
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization, "Basic " + base64Creds);
        headers.add("Accept","application/json");
        //

//        simpleRestTemplate2.put("http://localhost:8080/api/sample/order",
//                order);
        fromBuilderRestTemplate.put("http://localhost:8080/api/sample/order",
                order);
        return "success";
    }


    public void deleteOrder(Order order) {
        fromBuilderRestTemplate.delete("http://localhost:8080/api/sample/order",
                order);
    }

    public Order createOrder(Order order) {
      return    fromBuilderRestTemplate.postForObject(
                "http://localhost:8080/api/sample/order", order,Order.class);
    }

    public java.net.URI createOrder2(Order order) {

        return    fromBuilderRestTemplate.postForLocation(
                "http://localhost:8080/api/sample/order", order);
    }

    public void postOrdersecurity(Order order) {
        restTemplateAdmin.postForObject("http://localhost:8080/api/sample/order/security",
                order, Order.class);
    }

    public void deleteOrdersecurity(Order order) {
        restTemplateAdmin.delete("http://localhost:8080/api/sample/order/security",
                order, Order.class);
    }
}

