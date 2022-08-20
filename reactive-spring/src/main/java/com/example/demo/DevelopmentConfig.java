package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(OrderRepo repo){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                saveAnOrder("0","1", "2");
                saveAnOrder("1","1", "2");
                saveAnOrder("2","2", "2");
                saveAnOrder("3","3", "2");
                saveAnOrder("4","4", "2");
                saveAnOrder("5","5", "2");
                saveAnOrder("6","6", "2");
                saveAnOrder("7","7", "2");
                saveAnOrder("8","8", "2");
                saveAnOrder("9","9", "2");
                saveAnOrder("10","10", "2");
                saveAnOrder("11","11", "2");
                saveAnOrder("12","12", "2");
                saveAnOrder("13","13", "2");
                saveAnOrder("14","14", "2");
            }

            private SomeOrder saveAnOrder(String name, String s1, String s) {
                SomeOrder someOrder = new SomeOrder(name, s1, s);
                repo.save(someOrder).subscribe();
                System.out.println("--->"+name);
                repo.findAll().subscribe(s3-> System.out.println(s3.id+"<-----"));
                return
                        someOrder;
            }
        };
    }
}
