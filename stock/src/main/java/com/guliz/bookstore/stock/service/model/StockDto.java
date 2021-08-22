package com.guliz.bookstore.stock.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDto {

    private String bookId;
    private String bookName;
    private String author;
    private int stockCount;
    private Double price;

}
