package com.guliz.bookstore.stock.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<StockEntity, String> {
    boolean existsByBookId(String email);

    Optional<StockEntity> findByBookName(String bookName);
}