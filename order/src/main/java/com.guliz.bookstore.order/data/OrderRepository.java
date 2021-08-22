package com.guliz.bookstore.order.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByCreatedAtBetween(Date gt, Date lt);

    Optional<List<OrderEntity>> findByCustomerId(String customerId);
}