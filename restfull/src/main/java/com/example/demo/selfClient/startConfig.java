//package com.example.demo.selfClient;
//
//import com.example.demo.entity.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.net.http.HttpHeaders;
//import java.nio.charset.Charset;
//
//@Component
//public class startConfig implements CommandLineRunner {
//
//    @Autowired
//
//    SelfClientService selfClientService;
//
//    @Override
//    public void run(String... args) throws Exception {
////        System.out.println(
////                "--->1: getOrder rest self" +
////                        selfClientService.order("8"));
////
////        System.out.println(
////                "--->2: getOrder rest self" +
////                        selfClientService.orderSimple("8"));
////
////        System.out.println(
////                "--->3: getOrder rest self" +
////                        selfClientService.orderSimple2("8"));
////
////
////        System.out.println(
////                "--->4: getOrder rest self" +
////                        selfClientService.getIngredientById("8"));
////
////        System.out.println(
////                "--->5: getOrder rest self" +
////                        selfClientService.getIngredientById2("8"));
////
////        System.out.println(
////                "--->6: putOrder rest self" +
////                        selfClientService.updateOrder(new Order()));
////
////        selfClientService.deleteOrder(new Order());
////
////        System.out.println();selfClientService.createOrder(new Order());
////
////        System.out.println(
////                "--->7: putOrder rest self" +
////
////                        selfClientService.createOrder2(new Order()));
////
////        selfClientService.postOrdersecurity(new Order());
////        selfClientService.deleteOrdersecurity(new Order());
//    }
//
//
//
////    HttpHeaders createHeaders(String username, String password){
////        return new HttpHeaders() {{
////            String auth = username + ":" + password;
////            byte[] encodedAuth = Base64.encodeBase64(
////                    auth.getBytes(Charset.forName("US-ASCII")) );
////            String authHeader = "Basic " + new String( encodedAuth );
////            set( "Authorization", authHeader );
////        }};
////    }
//}
