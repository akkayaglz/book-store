package com.guliz.bookstore.stock.data;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "stocks")
@Data
@NoArgsConstructor
public class StockEntity {

    @Id
    private String bookId;
    private String bookName;
    private String author;
    private int stockCount;
    private Double price;
    private Date updatedAt;

}