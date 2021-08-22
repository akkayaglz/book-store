package com.guliz.bookstore.order.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderStockDto {

    private String bookId;
    private String bookName;
    private String author;
    private int stockCount;
    private Double price;
}
