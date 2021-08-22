package com.guliz.bookstore.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
@ComponentScan(basePackages = {"com.guliz.bookstore.order", "com.guliz.bookstore.security"})
@EnableMongoRepositories({"com.guliz.bookstore.order.data", "com.guliz.bookstore.customer.data",
        "com.guliz.bookstore.security.data"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


}
