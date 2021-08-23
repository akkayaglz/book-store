package com.guliz.bookstore.stock.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse extends BaseResponse {

    private static final long serialVersionUID = 4306457570118321475L;

    private String bookId;
    private String bookName;
    private String author;
    private int stockCount;
    private Double price;

}
