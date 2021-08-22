package com.guliz.bookstore.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class,
        scanBasePackages = {"com.guliz.bookstore.stock", "com.guliz.bookstore.security"})
@EnableMongoRepositories({"com.guliz.bookstore.stock.data",
        "com.guliz.bookstore.security.data"})
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }


}
