package com.guliz.bookstore.order.data;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orders")
@Data
@NoArgsConstructor
public class OrderEntity {

    @Id
    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Double totalPrice;
    private Date createdAt;
}