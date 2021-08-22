package com.guliz.bookstore.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class,
        scanBasePackages = {"com.guliz.bookstore.customer", "com.guliz.bookstore.security"})
@EnableMongoRepositories({"com.guliz.bookstore.customer.data",
        "com.guliz.bookstore.security.data"})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }


}
