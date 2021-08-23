package com.guliz.bookstore.stock.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String bookId;
    private String bookName;
    private String author;
    private int stockCount;
    private Double price;
}
