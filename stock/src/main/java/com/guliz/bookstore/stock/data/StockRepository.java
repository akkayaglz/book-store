package com.guliz.bookstore.stock.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<StockEntity, String> {
    boolean existsByBookId(String email);
}