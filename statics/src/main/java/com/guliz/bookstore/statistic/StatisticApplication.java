package com.guliz.bookstore.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
@ComponentScan(basePackages = {"com.guliz.bookstore.statistic", "com.guliz.bookstore.security"})
@EnableMongoRepositories({"com.guliz.bookstore.security.data"})
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class, args);
    }


}
